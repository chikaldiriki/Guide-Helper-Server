package server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import server.core.dto.OrderDTO;
import server.core.dto.TourDTO;
import server.core.model.Tour;
import server.core.repository.TourRepository;
import server.mapper.Mapper;
import server.specifications.GenericSpecification;

import java.time.LocalDateTime;
import java.util.Date;
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

    public TourDTO getTour(Long tourId) {
        return Mapper.map(tourRepository.findById(tourId).get(), TourDTO.class);
    }

    public List<TourDTO> getAllTours() {
        return Mapper.mapList(tourRepository.findAll(), TourDTO.class);
    }

    public List<TourDTO> getToursByCity(String city) {
        GenericSpecification<Tour> spec = new GenericSpecification<>("city", "eq", city);
        return Mapper.mapList(tourRepository.findAll(spec), TourDTO.class);
    }

    public List<TourDTO> getToursByCitySortedByParameter(String city, String parameter) {
        GenericSpecification<Tour> spec = new GenericSpecification<>("city", "eq", city);
        return Mapper.mapList(tourRepository.findAll(spec, Sort.by(Sort.Direction.ASC, parameter)), TourDTO.class);
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
        return Mapper.mapList(tourRepository.findAll(spec), TourDTO.class);
    }

    public List<TourDTO> getToursByGuide(String guideId) {
        GenericSpecification<Tour> spec = new GenericSpecification<>("guideId", "eq", guideId);
        return Mapper.mapList(tourRepository.findAll(spec), TourDTO.class);
    }

    public List<TourDTO> getFavoriteTours(String userMail) {
        List<Tour> favoriteTours = tourRepository.findAllById(favoriteTourService.getToursIdsByUser(userMail));
        return Mapper.mapList(favoriteTours, TourDTO.class);
    }

    public void addTour(TourDTO tourDTO) {
        tourRepository.save(Mapper.map(tourDTO, Tour.class));
    }

    public void updateTour(TourDTO tourDTO, Long tourId) {
        tourRepository.findById(tourId).map(tour -> {
            tour.setTitle(tourDTO.getTitle());
            tour.setCity(tourDTO.getCity());
            tour.setGuide(tourDTO.getGuide());
            tour.setCost(tourDTO.getCost());
            tour.setDescription(tourDTO.getDescription());
            tour.setImage(tourDTO.getImage());
            return tourRepository.save(tour);
        }).orElseGet(() -> {
            Tour tour = Mapper.map(tourDTO, Tour.class);
            tour.setId(tourId);
            return tourRepository.save(tour);
        });
    }

    public void deleteTour(Long tourId) {
        tourRepository.deleteById(tourId);
    }
}
