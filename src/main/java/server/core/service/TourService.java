package server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.core.dto.OrderDTO;
import server.core.dto.TourDTO;
import server.core.model.Tour;
import server.core.repository.TourRepository;
import server.mapper.Mapper;
import server.specifications.GenericSpecification;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private OrderService orderService;

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

    public List<TourDTO> getComingTours(String userId) { // TODO
        List<Integer> tourIds = orderService.getOrdersByUser(userId).stream()
                .map(OrderDTO::getTourId)
                .collect(Collectors.toList());
        GenericSpecification<Tour> spec = new GenericSpecification<>("id", "in", tourIds);
        return Mapper.mapList(tourRepository.findAll(spec), TourDTO.class);
        /*return orderService.getOrdersByUser(userId)
                .stream()
                .flatMap(orderDTO -> getAllTours().stream().filter(tourDTO -> orderDTO.getTourId() == tourDTO.getId()))
                .collect(Collectors.toList());*/
    }

    public List<TourDTO> getToursByGuide(String guideId) {
        GenericSpecification<Tour> spec = new GenericSpecification<>("guideId", "eq", guideId);
        return Mapper.mapList(tourRepository.findAll(spec), TourDTO.class);
    }

    public List<TourDTO> getFavoriteTours(Long userId) {
        //TODO
        return null;
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
