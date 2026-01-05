package pl.edu.pja.gden.tpopros30182.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.gden.tpopros30182.Entities.PriceHistory;

import java.util.List;

public interface PriceHistoryRepository extends CrudRepository<PriceHistory, Long> {
    List<PriceHistory> findAllByFigure_Id(Long figureId);
    List<PriceHistory> findByFigureId(Long figureId);
}
