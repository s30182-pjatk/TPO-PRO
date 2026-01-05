package pl.edu.pja.gden.tpopros30182.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.gden.tpopros30182.Auth.User;
import pl.edu.pja.gden.tpopros30182.Entities.Figure;
import pl.edu.pja.gden.tpopros30182.Entities.PublicCatalogSubmission;

import java.util.List;
import java.util.Optional;

public interface PublicCatalogueRepository extends CrudRepository<PublicCatalogSubmission, Long> {
    List<PublicCatalogSubmission> findAllByStatus(String status);
    Optional<PublicCatalogSubmission> findByFigure_Id(Long figureId);
    List<PublicCatalogSubmission> findBySubmitter_Id(Long ownerId);

    List<PublicCatalogSubmission> findBySubmitter(User submitter);

    void deleteById(Long id);
    }
