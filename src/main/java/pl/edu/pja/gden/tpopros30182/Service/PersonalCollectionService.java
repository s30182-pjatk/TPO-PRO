package pl.edu.pja.gden.tpopros30182.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pja.gden.tpopros30182.Auth.User;
import pl.edu.pja.gden.tpopros30182.Auth.UserRepository;
import pl.edu.pja.gden.tpopros30182.DTOS.AddExistingDTO;
import pl.edu.pja.gden.tpopros30182.DTOS.AddFigureDTO;
import pl.edu.pja.gden.tpopros30182.DTOS.PersonalCollectionDTO;
import pl.edu.pja.gden.tpopros30182.Entities.Figure;
import pl.edu.pja.gden.tpopros30182.Entities.PersonalCollectionEntry;
import pl.edu.pja.gden.tpopros30182.Mapper.Mapper;
import pl.edu.pja.gden.tpopros30182.Repository.FigureRepository;
import pl.edu.pja.gden.tpopros30182.Repository.PersonalCollectionRepository;
import pl.edu.pja.gden.tpopros30182.Repository.PublicCatalogueRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonalCollectionService {
    private final PersonalCollectionRepository personalCollectionRepository;
    private final PublicCatalogueRepository publicCatalogueRepository;
    private final FigureRepository figureRepository;
    private final UserRepository userRepository;

    public PersonalCollectionService(PersonalCollectionRepository personalCollectionRepository, PublicCatalogueRepository publicCatalogueRepository, FigureRepository figureRepository, UserRepository userRepository) {
        this.personalCollectionRepository = personalCollectionRepository;
        this.publicCatalogueRepository = publicCatalogueRepository;
        this.figureRepository = figureRepository;
        this.userRepository = userRepository;
    }

    public List<PersonalCollectionDTO> getPersonalCollections(Long userId) {
        return personalCollectionRepository.findAllByOwnerUser_Id(userId).stream().map(Mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public void changeFigureStatus(Long userId, Long figureId) {
        Optional<PersonalCollectionEntry> personalCollectionEntryOptional = personalCollectionRepository.findByOwnerUser_IdAndFigure_Id(userId, figureId);
        System.out.println(personalCollectionEntryOptional.isPresent());
        System.out.println(userId + ":" + figureId);
        if (personalCollectionEntryOptional.isPresent()) {
            PersonalCollectionEntry personalCollectionEntry = personalCollectionEntryOptional.get();
            String currentStatus = personalCollectionEntry.getStatus();
            personalCollectionEntry.setStatus(currentStatus.equals("OWNED") ? "WISHLISTED" : "OWNED");
            personalCollectionRepository.save(personalCollectionEntry);
            System.out.println("Triggered change status to " + currentStatus);
        }
    }

    @Transactional
    public void CreateAndAddFigureToPersonalCollection(AddFigureDTO figureDTO) {
        Figure figure = new Figure();
        PersonalCollectionEntry personalCollectionEntry = new PersonalCollectionEntry();
        User user = userRepository.findById(figureDTO.getUserId()).orElseThrow();

        figure.setCharacterType(figureDTO.getCharType());
        figure.setCondition(figureDTO.getCondition());
        figure.setEstimatedValue(figureDTO.getEstimatedValue());
        figure.setImagePath(figureDTO.getImageUrl());
        figure.setName(figure.getName());
        figure.setReleaseYear(figureDTO.getReleaseYear());
        figure.setSeries(figureDTO.getSeries());
        figure.setName(figureDTO.getFigureName());
        figureRepository.save(figure);

        personalCollectionEntry.setFigure(figure);
        personalCollectionEntry.setOwnerUser(user);
        personalCollectionEntry.setCreatedAt(LocalDateTime.now());
        personalCollectionEntry.setStatus("OWNED");
        personalCollectionRepository.save(personalCollectionEntry);
    }

    @Transactional
    public void AddFromPublicCatalogue(AddExistingDTO addExistingDTO) {
        Figure figure = publicCatalogueRepository.findByFigure_Id(addExistingDTO.getFigureId()).orElseThrow().getFigure();
        User user = userRepository.findById(addExistingDTO.getUserId()).orElseThrow();
        PersonalCollectionEntry personalCollectionEntry = new PersonalCollectionEntry();
        personalCollectionEntry.setOwnerUser(user);
        personalCollectionEntry.setFigure(figure);
        personalCollectionEntry.setCreatedAt(LocalDateTime.now());
        personalCollectionEntry.setStatus("OWNED");
        personalCollectionRepository.save(personalCollectionEntry);
    }

    public boolean FigureInCollection(Long figureId, Long userId) {
        return personalCollectionRepository.findByOwnerUser_IdAndFigure_Id(userId, figureId).isPresent();
    }


}
