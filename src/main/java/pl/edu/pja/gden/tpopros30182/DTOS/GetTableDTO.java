package pl.edu.pja.gden.tpopros30182.DTOS;

import java.util.List;

public class GetTableDTO {
    private Long figureId;
    private String figureName;
    private String charType;
    private String series;
    private Integer releaseYear;
    private String condition;
    private String imageUrl;
    private Float estimatedValue;
    private List<HistoryEntryDTO> priceHistory;
    private List<HistoryEntryDTO> projectedPriceHistory;

    public List<HistoryEntryDTO> getProjectedPriceHistory() {
        return projectedPriceHistory;
    }

    public void setProjectedPriceHistory(List<HistoryEntryDTO> projectedPriceHistory) {
        this.projectedPriceHistory = projectedPriceHistory;
    }

    public GetTableDTO() {
    }

    public Long getFigureId() {
        return figureId;
    }

    public void setFigureId(Long figureId) {
        this.figureId = figureId;
    }

    public String getFigureName() {
        return figureName;
    }

    public void setFigureName(String figureName) {
        this.figureName = figureName;
    }

    public String getCharType() {
        return charType;
    }

    public void setCharType(String charType) {
        this.charType = charType;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Float getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(Float estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public List<HistoryEntryDTO> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(List<HistoryEntryDTO> priceHistory) {
        this.priceHistory = priceHistory;
    }
}
