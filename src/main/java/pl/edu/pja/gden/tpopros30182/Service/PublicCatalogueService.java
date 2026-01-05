package pl.edu.pja.gden.tpopros30182.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pja.gden.tpopros30182.Auth.UserRepository;
import pl.edu.pja.gden.tpopros30182.DTOS.FigureDTO;
import pl.edu.pja.gden.tpopros30182.Entities.Figure;
import pl.edu.pja.gden.tpopros30182.Entities.PublicCatalogSubmission;
import pl.edu.pja.gden.tpopros30182.Mapper.Mapper;
import pl.edu.pja.gden.tpopros30182.Repository.FigureRepository;
import pl.edu.pja.gden.tpopros30182.Repository.PublicCatalogueRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicCatalogueService {
    private final PublicCatalogueRepository publicCatalogueRepository;
    private final FigureRepository figureRepository;
    private final UserRepository userRepository;

    public PublicCatalogueService(PublicCatalogueRepository publicCatalogueRepository, FigureRepository figureRepository, UserRepository userRepository) {
        this.publicCatalogueRepository = publicCatalogueRepository;
        this.figureRepository = figureRepository;
        this.userRepository = userRepository;
    }

    public List<FigureDTO> getAllApproved() {
        return publicCatalogueRepository.findAllByStatus("APPROVED").stream().map(Mapper::map).collect(Collectors.toList());
    }

    public Optional<FigureDTO> getFigureById(Long figureId) {
        return publicCatalogueRepository.findByFigure_Id(figureId).map(Mapper::map);
    }

    @Transactional
    public void createSubmission(Long figureId, Long userId) {

        PublicCatalogSubmission submission = new PublicCatalogSubmission();
        submission.setFigure(figureRepository.findFigureById(figureId).orElseThrow());
        submission.setSubmitter(userRepository.findById(userId).orElseThrow());
        submission.setStatus("PENDING");
        submission.setSubmissionDate(LocalDateTime.now());
        publicCatalogueRepository.save(submission);
    }

    public boolean submissionExists(Long figureId) {
        return publicCatalogueRepository.findByFigure_Id(figureId).isPresent();
    }

    public double calculateSimilarity(Long id1, Long id2) {
        double score = 0.0;
        Figure a = publicCatalogueRepository.findByFigure_Id(id1).orElseThrow().getFigure();
        Figure b = publicCatalogueRepository.findByFigure_Id(id2).orElseThrow().getFigure();


        if (a.getSeries().equalsIgnoreCase(b.getSeries())) score += 0.25;
        if (a.getCharacterType().equalsIgnoreCase(b.getCharacterType())) score += 0.25;
        if (a.getCondition().equalsIgnoreCase(b.getCondition())) score += 0.25;
        if (Math.abs(a.getEstimatedValue() - b.getEstimatedValue()) <= 0.1 * a.getEstimatedValue()) score += 0.25;
        return score * 100;
    }

}
