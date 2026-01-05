package pl.edu.pja.gden.tpopros30182.Service;

import org.springframework.stereotype.Service;
import pl.edu.pja.gden.tpopros30182.DTOS.GetTableDTO;
import pl.edu.pja.gden.tpopros30182.DTOS.HistoryEntryDTO;
import pl.edu.pja.gden.tpopros30182.Entities.PriceHistory;
import pl.edu.pja.gden.tpopros30182.Repository.FigureRepository;
import pl.edu.pja.gden.tpopros30182.Repository.PriceHistoryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceHistoryService {
    private final PriceHistoryRepository priceHistoryRepository;

    public PriceHistoryService(PriceHistoryRepository priceHistoryRepository, FigureRepository figureRepository) {
        this.priceHistoryRepository = priceHistoryRepository;
    }

    public GetTableDTO getTable(Long figureId) {
        GetTableDTO getTableDTO = null;

        List<PriceHistory> historyList;
        historyList = priceHistoryRepository.findAllByFigure_Id(figureId)
                .stream()
                .sorted(Comparator.comparing(PriceHistory::getRecordedAt)) // sort by time
                .collect(Collectors.toList());

        List<HistoryEntryDTO> priceHistoryDTOs = new ArrayList<>();

        for (PriceHistory priceHistory : historyList) {
            if (getTableDTO == null) {
                getTableDTO = new GetTableDTO();
                getTableDTO.setFigureId(priceHistory.getFigure().getId());
                getTableDTO.setCondition(priceHistory.getFigure().getCondition());
                getTableDTO.setEstimatedValue(priceHistory.getEstimatedValue());
                getTableDTO.setCharType(priceHistory.getFigure().getCharacterType());
                getTableDTO.setFigureName(priceHistory.getFigure().getName());
                getTableDTO.setSeries(priceHistory.getFigure().getSeries());
                getTableDTO.setReleaseYear(priceHistory.getFigure().getReleaseYear());
                getTableDTO.setImageUrl(priceHistory.getFigure().getImagePath());
            }

            priceHistoryDTOs.add(new HistoryEntryDTO(
                    priceHistory.getEstimatedValue(),
                    priceHistory.getRecordedAt()
            ));
        }

        getTableDTO.setPriceHistory(priceHistoryDTOs);

        // === Projected Prices Logic ===
        List<HistoryEntryDTO> projected = generateProjectedPrices(priceHistoryDTOs, 3);
        getTableDTO.setProjectedPriceHistory(projected);

        return getTableDTO;
    }

    private List<HistoryEntryDTO> generateProjectedPrices(List<HistoryEntryDTO> history, int monthsToProject) {
        List<HistoryEntryDTO> projections = new ArrayList<>();
        if (history.size() < 2) return projections;

        // Use the last two entries for trend calculation
        HistoryEntryDTO last = history.get(history.size() - 1);
        HistoryEntryDTO secondLast = history.get(history.size() - 2);

        // Add the last actual point to the start of projections
        projections.add(new HistoryEntryDTO(last.getEstimatedValue(), last.getRecordedAt()));

        // Calculate simple daily slope
        float valueDiff = last.getEstimatedValue() - secondLast.getEstimatedValue();
        long daysBetween = java.time.Duration.between(secondLast.getRecordedAt(), last.getRecordedAt()).toDays();
        float dailyTrend = daysBetween > 0 ? valueDiff / daysBetween : 0;

        // Project from the last known point
        LocalDateTime currentDate = last.getRecordedAt();
        float currentValue = last.getEstimatedValue();

        for (int i = 1; i <= monthsToProject; i++) {
            currentDate = currentDate.plusMonths(1);

            // Trend with a slight sine fluctuation
            float trendComponent = currentValue + dailyTrend * 30 * i;
            float wave = (float) (Math.sin(i * Math.PI / 2) * 0.015 * trendComponent);

            projections.add(new HistoryEntryDTO(trendComponent + wave, currentDate));
        }

        return projections;
    }



}
