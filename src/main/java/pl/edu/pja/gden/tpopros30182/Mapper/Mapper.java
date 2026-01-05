package pl.edu.pja.gden.tpopros30182.Mapper;

import pl.edu.pja.gden.tpopros30182.Auth.User;
import pl.edu.pja.gden.tpopros30182.DTOS.FigureDTO;
import pl.edu.pja.gden.tpopros30182.DTOS.PersonalCollectionDTO;
import pl.edu.pja.gden.tpopros30182.DTOS.SubmissionDTO;
import pl.edu.pja.gden.tpopros30182.Entities.Figure;
import pl.edu.pja.gden.tpopros30182.Entities.PersonalCollectionEntry;
import pl.edu.pja.gden.tpopros30182.Entities.PublicCatalogSubmission;

public class Mapper {
    public static FigureDTO map(PublicCatalogSubmission publicCatalogSubmission) {
        FigureDTO figureDTO = new FigureDTO();
        Figure figure = publicCatalogSubmission.getFigure();
        figureDTO.setId(figure.getId());
        figureDTO.setFigureName(figure.getName());
        figureDTO.setSeries(figure.getSeries());
        figureDTO.setEstimatedValue(figure.getEstimatedValue());
        figureDTO.setCondition(figure.getCondition());
        figureDTO.setReleaseYear(figure.getReleaseYear());
        figureDTO.setCharType(figure.getCharacterType());
        figureDTO.setImageUrl(figure.getImagePath());
        return figureDTO;
    }

    public static SubmissionDTO mapToSubDTO(PublicCatalogSubmission publicCatalogSubmission){
        SubmissionDTO submissionDTO = new SubmissionDTO();
        User user = publicCatalogSubmission.getSubmitter();
        Figure figure = publicCatalogSubmission.getFigure();
        submissionDTO.setId(publicCatalogSubmission.getId());
        submissionDTO.setStatus(publicCatalogSubmission.getStatus());
        submissionDTO.setSubmitDate(publicCatalogSubmission.getSubmissionDate());

        submissionDTO.setId(publicCatalogSubmission.getId());
        submissionDTO.setFigure(figure.getName());
        submissionDTO.setSubmitter(user.getFirstName() + " " + user.getLastName());
        submissionDTO.setEmail(user.getEmail());
        return submissionDTO;

    }

    public static FigureDTO map(Figure figure) {
        FigureDTO figureDTO = new FigureDTO();
        System.out.println(figureDTO.getId());
        figureDTO.setId(figure.getId());
        figureDTO.setFigureName(figure.getName());
        figureDTO.setSeries(figure.getSeries());
        figureDTO.setEstimatedValue(figure.getEstimatedValue());
        figureDTO.setCondition(figure.getCondition());
        figureDTO.setReleaseYear(figure.getReleaseYear());
        figureDTO.setCharType(figure.getCharacterType());
        figureDTO.setImageUrl(figure.getImagePath());
        return figureDTO;
    }

    public static PersonalCollectionDTO map(PersonalCollectionEntry entry){
        PersonalCollectionDTO personalCollectionDTO = new PersonalCollectionDTO();
        Figure figure = entry.getFigure();
        personalCollectionDTO.setFigureId(figure.getId());
        personalCollectionDTO.setFigureName(figure.getName());
        personalCollectionDTO.setSeries(figure.getSeries());
        personalCollectionDTO.setEstimatedValue(figure.getEstimatedValue());
        personalCollectionDTO.setCondition(figure.getCondition());
        personalCollectionDTO.setReleaseYear(figure.getReleaseYear());
        personalCollectionDTO.setCharType(figure.getCharacterType());
        personalCollectionDTO.setImageUrl(figure.getImagePath());

        personalCollectionDTO.setStatus(entry.getStatus());
        personalCollectionDTO.setCreatedAt(entry.getCreatedAt());
        return personalCollectionDTO;
    }
}
