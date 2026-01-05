package pl.edu.pja.gden.tpopros30182.DTOS;

import java.time.LocalDateTime;

public class HistoryEntryDTO {
    private Float estimatedValue;
    private LocalDateTime recordedAt;

    public HistoryEntryDTO() {
    }

    public HistoryEntryDTO(Float estimatedValue, LocalDateTime recordedAt) {
        this.estimatedValue = estimatedValue;
        this.recordedAt = recordedAt;
    }

    public Float getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(Float estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
}
