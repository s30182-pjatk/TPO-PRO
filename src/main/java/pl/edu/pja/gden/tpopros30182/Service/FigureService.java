package pl.edu.pja.gden.tpopros30182.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pja.gden.tpopros30182.DTOS.AddFigureDTO;
import pl.edu.pja.gden.tpopros30182.DTOS.FigureDTO;
import pl.edu.pja.gden.tpopros30182.Entities.Figure;
import pl.edu.pja.gden.tpopros30182.Mapper.Mapper;
import pl.edu.pja.gden.tpopros30182.Repository.FigureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FigureService {
    private final FigureRepository repository;

    public FigureService(FigureRepository repository) {
        this.repository = repository;
    }

    public FigureDTO getFigureById(Long figure_id) {
        return Mapper.map(repository.findFigureById(figure_id).orElseThrow());
    }

    public List<FigureDTO> getAllFigures() {
        List<FigureDTO> figures = new ArrayList<>();
        for (Figure figure : repository.findAll()) {
            figures.add(Mapper.map(figure));
        }
        return figures;
    }
    @Transactional
    public Figure save(AddFigureDTO figureDTO) {
        Figure figure = new Figure();
        figure.setCharacterType(figureDTO.getCharType());
        figure.setCondition(figureDTO.getCondition());
        figure.setEstimatedValue(figureDTO.getEstimatedValue());
        figure.setImagePath(figureDTO.getImageUrl());
        figure.setName(figure.getName());
        figure.setReleaseYear(figureDTO.getReleaseYear());
        figure.setSeries(figureDTO.getSeries());
        return repository.save(figure);
    }

    @Transactional
    public void delete(Long figure_id) {
        repository.deleteById(figure_id);
    }
}
