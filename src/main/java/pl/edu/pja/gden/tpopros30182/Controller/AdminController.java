package pl.edu.pja.gden.tpopros30182.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.gden.tpopros30182.Auth.AdminService;
import pl.edu.pja.gden.tpopros30182.Auth.UserDTO;
import pl.edu.pja.gden.tpopros30182.DTOS.AddFigureDTO;
import pl.edu.pja.gden.tpopros30182.DTOS.FigureDTO;
import pl.edu.pja.gden.tpopros30182.DTOS.HistoryDTO;
import pl.edu.pja.gden.tpopros30182.DTOS.SubmissionDTO;
import pl.edu.pja.gden.tpopros30182.Service.FigureService;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final FigureService figureService;
    private final AdminService adminService;

    public AdminController(FigureService figureService, AdminService adminService) {
        this.figureService = figureService;
        this.adminService = adminService;
    }

    @GetMapping
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/figures")
    public String figures(Model model) {
        List<FigureDTO> figures = figureService.getAllFigures();
        model.addAttribute("figures", figures);
        return "admin-figures";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDTO> users = adminService.getUsers();
        model.addAttribute("users", users);
        return "admin-users";
    }

    
    @PostMapping("/update-figure")
    public String updateFigure(@ModelAttribute @Valid AddFigureDTO addFigureDTO, BindingResult result) {
        if (result.hasErrors()) {
            
            return "redirect:/admin/figures?error=validation";
        }
        figureService.save(addFigureDTO);
        return "redirect:/admin/figures";
    }

    
    @PostMapping("/delete-figure")
    public String deleteFigure(@RequestParam("id") Long figureId) {
        figureService.delete(figureId);
        return "redirect:/admin/figures";
    }

    
    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam("id") Long userId) {
        adminService.deleteUserById(userId);
        return "redirect:/admin/users";
    }

    
    @PostMapping("/update-user-role")
    public String updateUserRole(@RequestParam("id") Long userId,
                                 @RequestParam("role") String role,
                                 @RequestParam("action") String action) {
        if ("add".equalsIgnoreCase(action)) {
            adminService.addRoleToUser(userId, role);
        } else if ("remove".equalsIgnoreCase(action)) {
            adminService.deleteRoleFromUser(userId, role);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/submissions")
    public String submissions(Model model) {
        List<SubmissionDTO> submissions = adminService.getSubmissions();
        model.addAttribute("submissions", submissions);
        return "admin-submissions";
    }

    @PostMapping("/update-submission-status")
    public String updateSubmissionStatus(@RequestParam("id") Long submissionId,
                                         @RequestParam("status") String status) {
        adminService.updateSubmissionStatus(submissionId, status);
        return "redirect:/admin/submissions";
    }

    @PostMapping("/delete-submission")
    public String deleteSubmission(@RequestParam("id") Long submissionId) {
        adminService.deleteSubmission(submissionId);
        System.out.println(submissionId);
        return "redirect:/admin/submissions";
    }

    @GetMapping("/price-history")
    public String viewPriceHistory(@RequestParam("figureId") Long figureId, Model model) {
        FigureDTO figure = figureService.getFigureById(figureId);
        List<HistoryDTO> priceHistory = adminService.getPriceHistoryForFigure(figureId);
        model.addAttribute("figure", figure);
        model.addAttribute("priceHistory", priceHistory);
        model.addAttribute("newEntry", new HistoryDTO());
        return "admin-price-history";
    }

    @PostMapping("/add-price-entry")
    public String addPriceEntry(@ModelAttribute HistoryDTO newEntry) {
        adminService.addPriceHistoryEntry(newEntry);
        return "redirect:/admin/price-history?figureId=" + newEntry.getFigureId();
    }

    @PostMapping("/update-price-entry")
    public String updatePriceEntry(@RequestParam("entryId") Long entryId,
                                   @RequestParam("estimatedValue") Float value,
                                   @RequestParam("recordedAt") String dateTime,
                                   @RequestParam("figureId") Long figureId) {
        adminService.updatePriceHistoryEntry(entryId, value, LocalDateTime.parse(dateTime));
        return "redirect:/admin/price-history?figureId=" + figureId;
    }
}
