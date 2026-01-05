package pl.edu.pja.gden.tpopros30182.Auth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pja.gden.tpopros30182.DTOS.HistoryDTO;
import pl.edu.pja.gden.tpopros30182.DTOS.SubmissionDTO;
import pl.edu.pja.gden.tpopros30182.Entities.Figure;
import pl.edu.pja.gden.tpopros30182.Entities.PriceHistory;
import pl.edu.pja.gden.tpopros30182.Entities.PublicCatalogSubmission;
import pl.edu.pja.gden.tpopros30182.Mapper.Mapper;
import pl.edu.pja.gden.tpopros30182.Repository.FigureRepository;
import pl.edu.pja.gden.tpopros30182.Repository.PriceHistoryRepository;
import pl.edu.pja.gden.tpopros30182.Repository.PublicCatalogueRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PublicCatalogueRepository publicCatalogueRepository;
    public final PriceHistoryRepository priceHistoryRepository;
    public final FigureRepository figureRepository;

    public AdminService(UserRepository userRepository, UserRoleRepository userRoleRepository, PublicCatalogueRepository publicCatalogueRepository, PriceHistoryRepository priceHistoryRepository, FigureRepository figureRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.publicCatalogueRepository = publicCatalogueRepository;
        this.priceHistoryRepository = priceHistoryRepository;
        this.figureRepository = figureRepository;
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(UserDTOMapper::map).toList();
    }

    public List<SubmissionDTO> getSubmissions() {
        List<SubmissionDTO> submissionDTOs = new ArrayList<>();
        for(PublicCatalogSubmission submission : publicCatalogueRepository.findAll()) {
            submissionDTOs.add(Mapper.mapToSubDTO(submission));
        }
        return submissionDTOs;
    }

    @Transactional
    public void updateSubmissionStatus(Long id, String status) {
        PublicCatalogSubmission submission = publicCatalogueRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Submission not found"));
        submission.setStatus(status);
        publicCatalogueRepository.save(submission);
    }

    @Transactional
    public void deleteSubmission(Long id) {
        publicCatalogueRepository.deleteById(id);
    }

    @Transactional
    public void deleteUserById(Long user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Step 2: Load and delete all submissions for this user
        List<PublicCatalogSubmission> submissions = publicCatalogueRepository.findBySubmitter(user);
        publicCatalogueRepository.deleteAll(submissions);

        // Step 3: Now safely delete the user
        userRepository.delete(user);
    }

    @Transactional
    public void addRoleToUser(Long user_id, String role_name) {
        Optional<UserRole> role = userRoleRepository.findByName(role_name);
        User user = userRepository.findById(user_id).get();
        if (role.isPresent()) {
            UserRole userRole = role.get();
            Set<UserRole> userRoles = user.getRoles();
            userRoles.add(userRole);
            user.setRoles(userRoles);
        }

        userRepository.save(user);
    }
    @Transactional
    public void deleteRoleFromUser(Long user_id, String role_name) {
        Optional<UserRole> role = userRoleRepository.findByName(role_name);
        User user = userRepository.findById(user_id).get();
        if (role.isPresent()) {
            UserRole userRole = role.get();
            Set<UserRole> userRoles = user.getRoles();
            userRoles.remove(userRole);
            user.setRoles(userRoles);
        }

        userRepository.save(user);
    }

    public List<HistoryDTO> getPriceHistoryForFigure(Long figureId) {
        return priceHistoryRepository.findByFigureId(figureId).stream()
                .map(ph -> {
                    HistoryDTO dto = new HistoryDTO();
                    dto.setId(ph.getId());
                    dto.setEstimatedValue(ph.getEstimatedValue());
                    dto.setRecordedAt(ph.getRecordedAt());
                    dto.setFigureId(figureId);
                    return dto;
                }).toList();
    }

    @Transactional
    public void addPriceHistoryEntry(HistoryDTO dto) {
        Figure figure = figureRepository.findById(dto.getFigureId()).orElseThrow();
        PriceHistory history = new PriceHistory();
        history.setFigure(figure);
        history.setEstimatedValue(dto.getEstimatedValue());
        history.setRecordedAt(dto.getRecordedAt());
        priceHistoryRepository.save(history);
    }

    @Transactional
    public void updatePriceHistoryEntry(Long id, Float value, LocalDateTime date) {
        PriceHistory history = priceHistoryRepository.findById(id).orElseThrow();
        history.setEstimatedValue(value);
        history.setRecordedAt(date);
        priceHistoryRepository.save(history);
    }

}
