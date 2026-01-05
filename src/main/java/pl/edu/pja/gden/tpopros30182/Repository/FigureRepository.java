package pl.edu.pja.gden.tpopros30182.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.gden.tpopros30182.Entities.Figure;

import java.util.List;
import java.util.Optional;

public interface FigureRepository extends CrudRepository<Figure, Long> {
    Optional<Figure> findFigureById(Long id);
}
