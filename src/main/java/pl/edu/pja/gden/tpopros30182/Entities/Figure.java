package pl.edu.pja.gden.tpopros30182.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Figure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "CharacterType")
    private String characterType;

    @Column(name = "Series")
    private String series;

    @Column(name = "ReleaseYear")
    private int releaseYear;

    @Column(name = "Condition")
    private String condition;

    @Column(name = "ImagePath")
    private String imagePath;

    @Column(name = "EstimatedValue")
    private Float estimatedValue;

    @OneToMany(mappedBy = "figure", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PriceHistory> prices = new ArrayList<>();

    @OneToMany(mappedBy = "figure", cascade = CascadeType.ALL)
    private List<PersonalCollectionEntry> personalCollectionEntries = new ArrayList<>();

    @OneToMany(mappedBy = "figure", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PublicCatalogSubmission> publicCatalogSubmissions = new ArrayList<>();

    public Figure() {
    }

    public List<PersonalCollectionEntry> getPersonalCollectionEntries() {
        return personalCollectionEntries;
    }

    public void setPersonalCollectionEntries(List<PersonalCollectionEntry> personalCollectionEntries) {
        this.personalCollectionEntries = personalCollectionEntries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterType() {
        return characterType;
    }

    public void setCharacterType(String characterType) {
        this.characterType = characterType;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Float getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(Float estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public List<PriceHistory> getPrices() {
        return prices;
    }

    public void setPrices(List<PriceHistory> prices) {
        this.prices = prices;
    }


    public List<PublicCatalogSubmission> getPublicCatalogSubmissions() {
        return publicCatalogSubmissions;
    }

    public void setPublicCatalogSubmissions(List<PublicCatalogSubmission> publicCatalogSubmissions) {
        this.publicCatalogSubmissions = publicCatalogSubmissions;
    }
}
