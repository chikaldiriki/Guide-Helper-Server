package server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import server.core.dto.OrderDTO;
import server.core.dto.TourDTO;
import server.core.model.Tour;
import server.core.repository.TourRepository;
import server.specifications.GenericSpecification;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private FavoriteTourService favoriteTourService;

    private TourDTO mapToDTO(Tour tour) {
        return new TourDTO(tour.getId(), tour.getTitle(), tour.getCity(),
                tour.getDescription(), tour.getGuide(), tour.getCost(),
                ImageService.getTourImageCode(tour));
    }

    private List<TourDTO> mapToDTOList(List<Tour> tours) {
        return tours.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private Tour mapToEntity(TourDTO tourDTO) {
        return new Tour(tourDTO.getId(), tourDTO.getTitle(), tourDTO.getCity(),
                tourDTO.getGuide(), tourDTO.getCost(), tourDTO.getDescription(),
                ImageService.saveTourImage(tourDTO));
    }

    public TourDTO getTour(Long tourId) {
        return mapToDTO(tourRepository.findById(tourId).orElseThrow());
    }

    public List<TourDTO> getAllTours() {
        return mapToDTOList(tourRepository.findAll());
    }

    public List<TourDTO> getToursByCity(String city) {
        GenericSpecification<Tour> spec = new GenericSpecification<>("city", "eq", city);
        return mapToDTOList(tourRepository.findAll(spec));
    }

    public List<TourDTO> getToursByCitySortedByParameter(String city, String parameter) {
        GenericSpecification<Tour> spec = new GenericSpecification<>("city", "eq", city);
        return mapToDTOList(tourRepository.findAll(spec, Sort.by(Sort.Direction.ASC, parameter)));
    }

    public List<TourDTO> getComingTours(String userId, LocalDateTime currentDate) {
        List<Long> tourIds = orderService.getComingOrders(userId, currentDate).stream()
                .map(OrderDTO::getTourId)
                .collect(Collectors.toList());
        if (tourIds.isEmpty()) {
            return Collections.emptyList();
        }

        Specification<Tour> spec = new GenericSpecification<>("id", "eq", tourIds.get(0));
        for (int i = 1; i < tourIds.size(); i++) {
            spec = Specification.where(spec).or(new GenericSpecification<>("id", "eq", tourIds.get(i)));
        }
        return mapToDTOList(tourRepository.findAll(spec));
    }

    public List<TourDTO> getToursByGuide(String guideId) {
        GenericSpecification<Tour> spec = new GenericSpecification<>("guideId", "eq", guideId);
        return mapToDTOList(tourRepository.findAll(spec));
    }

    public List<TourDTO> getFavoriteTours(String userMail) {
        List<Tour> favoriteTours = tourRepository.findAllById(favoriteTourService.getToursIdsByUser(userMail));
        return mapToDTOList(favoriteTours);
    }

    public void addTour(TourDTO tourDTO) {
        tourRepository.save(mapToEntity(tourDTO));
    }

    public void updateTour(TourDTO tourDTO, Long tourId) {
        String imagePath = ImageService.saveTourImage(tourDTO);

        tourRepository.findById(tourId).map(tour -> {
            tour.setTitle(tourDTO.getTitle());
            tour.setCity(tourDTO.getCity());
            tour.setGuide(tourDTO.getGuide());
            tour.setCost(tourDTO.getCost());
            tour.setDescription(tourDTO.getDescription());
            tour.setImage(imagePath);
            return tourRepository.save(tour);
        }).orElseGet(() -> {
            Tour tour = mapToEntity(tourDTO);
            tour.setId(tourId);
            return tourRepository.save(tour);
        });
    }

    public void deleteTour(Long tourId) {
        tourRepository.deleteById(tourId);
    }
}
