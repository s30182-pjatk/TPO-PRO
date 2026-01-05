package pl.edu.pja.gden.tpopros30182.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.gden.tpopros30182.Entities.PersonalCollectionEntry;

import java.util.List;
import java.util.Optional;

public interface PersonalCollectionRepository extends CrudRepository<PersonalCollectionEntry, Long> {
    List<PersonalCollectionEntry> findAllByOwnerUser_Id(Long id);
    Optional<PersonalCollectionEntry> findByOwnerUser_IdAndFigure_Id(Long ownerUserId, Long figureId);
}
