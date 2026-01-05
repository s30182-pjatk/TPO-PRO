package pl.edu.pja.gden.tpopros30182.Entities;

import jakarta.persistence.*;
import pl.edu.pja.gden.tpopros30182.Auth.User;

import java.time.LocalDateTime;

@Entity
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = {"submitter_id", "figure_id"})}
)
public class PublicCatalogSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "submitter_id", nullable = false)
    private User submitter;

    @ManyToOne
    @JoinColumn(name = "figure_id", nullable = false)
    private Figure figure;

    @Column(name = "Status")
    private String status;

    @Column(name = "SubmissionDate")
    private LocalDateTime submissionDate;

    public PublicCatalogSubmission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSubmitter() {
        return submitter;
    }

    public void setSubmitter(User submitter) {
        this.submitter = submitter;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }
}
