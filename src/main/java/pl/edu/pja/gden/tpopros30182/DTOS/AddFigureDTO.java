package pl.edu.pja.gden.tpopros30182.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.pja.gden.tpopros30182.Validation.ImageURL;

public class AddFigureDTO {
    private Long userId;
    private String figureName;
    private String charType;
    private String series;
    private Integer releaseYear;
    private String condition;
    @ImageURL
    private String imageUrl;
    @Positive
    private Float estimatedValue;

    public AddFigureDTO() {
    }

    public String getFigureName() {
        return figureName;
    }

    public void setFigureName(String figureName) {
        this.figureName = figureName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
