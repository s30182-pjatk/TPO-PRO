package pl.edu.pja.gden.tpopros30182.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.pja.gden.tpopros30182.DTOS.FigureDTO;
import pl.edu.pja.gden.tpopros30182.Service.FigureService;
import pl.edu.pja.gden.tpopros30182.Service.PublicCatalogueService;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final PublicCatalogueService publicCatalogueService;
    private final FigureService figureService;

    public HomeController(PublicCatalogueService publicCatalogueService, FigureService figureService) {
        this.publicCatalogueService = publicCatalogueService;
        this.figureService = figureService;
    }

    @GetMapping("/catalogue")
    public String catalogue(Model model) {
        List<FigureDTO> figures = publicCatalogueService.getAllApproved();
        for (FigureDTO f : figures) {
            System.out.println("Figure: " + f.getFigureName() + ", ID: " + f.getId());
        }
        model.addAttribute("figures", figures);
        return "catalogue";
    }

    @GetMapping("/figure/{figure_id}")
    public String figure(Model model, @PathVariable Long figure_id) {
        FigureDTO figureDTO = figureService.getFigureById(figure_id);
        model.addAttribute("figure", figureDTO);
        return "figure";
    }
}
