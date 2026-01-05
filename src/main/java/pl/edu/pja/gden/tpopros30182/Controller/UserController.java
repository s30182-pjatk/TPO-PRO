package pl.edu.pja.gden.tpopros30182.Controller;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.edu.pja.gden.tpopros30182.DTOS.*;
import pl.edu.pja.gden.tpopros30182.DTOS.CompareDTO;
import pl.edu.pja.gden.tpopros30182.Service.PersonalCollectionService;
import pl.edu.pja.gden.tpopros30182.Service.PriceHistoryService;
import pl.edu.pja.gden.tpopros30182.Service.PublicCatalogueService;
import pl.edu.pja.gden.tpopros30182.Service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final PersonalCollectionService personalCollectionService;
    private final UserService userService;
    private final PublicCatalogueService publicCatalogueService;
    private final PriceHistoryService priceHistoryService;

    public UserController(PersonalCollectionService personalCollectionService, UserService userService, PublicCatalogueService publicCatalogueService, PriceHistoryService priceHistoryService) {
        this.personalCollectionService = personalCollectionService;
        this.userService = userService;
        this.publicCatalogueService = publicCatalogueService;
        this.priceHistoryService = priceHistoryService;
    }

    private Long getUserId(UserDetails user) {
        return userService.getUserIdByEmail(user.getUsername()).orElseThrow();
    }

    @GetMapping("/compare")
    public String compare(Model model) {
        CompareDTO compareDTO = new CompareDTO();
        List<FigureDTO> figures = publicCatalogueService.getAllApproved();
        model.addAttribute("compareDTO", compareDTO);
        model.addAttribute("figures", figures);
        return "compare-form";
    }

    @PostMapping("/compare")
    public String compare(@ModelAttribute CompareDTO compareDTO, RedirectAttributes redirectAttributes) {
        Optional<FigureDTO> figureDTO1 = publicCatalogueService.getFigureById(compareDTO.getFigureIdOne());
        Optional<FigureDTO> figureDTO2 = publicCatalogueService.getFigureById(compareDTO.getFigureIdTwo());

//        if (figureDTO1.isEmpty() || figureDTO2.isEmpty()) {
//            return "redirect:/home/catalogue";
//        }

        double similarity = publicCatalogueService.calculateSimilarity(
                compareDTO.getFigureIdOne(),
                compareDTO.getFigureIdTwo()
        );

        CompareResultDTO compareResultDTO = new CompareResultDTO();
        compareResultDTO.setFigureOne(figureDTO1.get());
        compareResultDTO.setFigureTwo(figureDTO2.get());
        compareResultDTO.setScore(similarity);

        redirectAttributes.addFlashAttribute("compareResult", compareResultDTO);
        return "redirect:/user/comparison-result";
    }

    @GetMapping("/comparison-result")
    public String comparisonResult(@ModelAttribute("compareResult") CompareResultDTO compareResult, Model model) {
        model.addAttribute("compareResult", compareResult);
        return "compare-result";
    }



    @GetMapping("/collection")
    public String collection(Model model, @AuthenticationPrincipal UserDetails user) {
        if(user != null){
            Long userId = getUserId(user);
            List<PersonalCollectionDTO> collection = personalCollectionService.getPersonalCollections(userId);
            model.addAttribute("collection", collection);
            model.addAttribute("userId", userId);
            return "user-collection";
        }else{
            return "redirect:/home/catalogue";
        }
    }

    @PostMapping("/collection/change-status/{figureID}")
    public String changeStatus(@PathVariable Long figureID, @AuthenticationPrincipal UserDetails user){
        Long userId = getUserId(user);
        personalCollectionService.changeFigureStatus(userId, figureID);
        return "redirect:/user/collection";
    }

    @GetMapping("/collection/add-figure")
    public String AddFigureToCollection(Model model, @AuthenticationPrincipal UserDetails user) {

        AddFigureDTO addFigureDTO = new AddFigureDTO();
        AddExistingDTO addExistingDTO = new AddExistingDTO();
        List<FigureDTO> approvedfigures = publicCatalogueService.getAllApproved();
        Long userId = getUserId(user);
        addFigureDTO.setUserId(userId);
        model.addAttribute("addFigureDTO", addFigureDTO);
        model.addAttribute("addExistingDTO", addExistingDTO);
        model.addAttribute("approvedfigures", approvedfigures);
        model.addAttribute("userId", userId);
        return "user-add-figure-form";
    }

    @PostMapping("/collection/add-figure")
    public String ProcessAddFigure(@Valid @ModelAttribute("addFigureDTO") AddFigureDTO addFigureDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-add-figure-form";
        }
        personalCollectionService.CreateAndAddFigureToPersonalCollection(addFigureDTO);
        return "redirect:/user/collection";
    }

    @PostMapping("/collection/add-figure-existing")
    public String ProcessAddFigure(AddExistingDTO addExistingDTO){
        if(personalCollectionService.FigureInCollection(addExistingDTO.getFigureId(), addExistingDTO.getUserId())) {
            return "redirect:/user/collection";
        }
        personalCollectionService.AddFromPublicCatalogue(addExistingDTO);
        return "redirect:/user/collection";
    }

    @GetMapping("/table/{figure_id}")
    public String figureTable(Model model, @PathVariable Long figure_id) {
        GetTableDTO getTableDTO = priceHistoryService.getTable(figure_id);
        model.addAttribute("getTableDTO", getTableDTO);
        return "user-table-figure-show";
    }

    @PostMapping("/submit-to-catalogue")
    public String submitToCatalogue(@RequestParam Long figure_id, @RequestParam Long user_id) {
        if(publicCatalogueService.submissionExists(figure_id)) {
            return "redirect:/user/collection";
        }
        publicCatalogueService.createSubmission(figure_id, user_id);
        return "redirect:/user/submit-to-catalogue-success";
    }

    @GetMapping("/submit-to-catalogue-success")
    public String submitToCatalogueSuccess(Model model) {
        return "catalogue-submit-success";
    }
}
