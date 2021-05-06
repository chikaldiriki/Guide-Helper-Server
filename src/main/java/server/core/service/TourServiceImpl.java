package server.core.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.core.dto.OrderDTO;
import server.core.dto.TourDTO;
import server.core.model.Tour;
import server.core.repository.TourRepository;
import server.specifications.GenericSpecification;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private OrderService orderService;

    private List<TourDTO> tourToDTO(List<Tour> tours) {
        return tours.stream()
                .map(tour -> new ModelMapper().map(tour, TourDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TourDTO getTour(Integer tourId) {
        return new ModelMapper().map(tourRepository.findById(tourId).get(), TourDTO.class);
    }

    @Override
    public List<TourDTO> getAllTours() {
        return tourToDTO(tourRepository.findAll());
    }

    @Override
    public List<TourDTO> getToursByCity(String city) {
        GenericSpecification<Tour> spec = new GenericSpecification<>("city", "eq", city);
        return tourToDTO(tourRepository.findAll(spec));
    }

    @Override
    public List<TourDTO> getToursByCitySortedByParameter(String city, String parameter) {
        GenericSpecification<Tour> spec = new GenericSpecification<>("city", "eq", city);
        return tourToDTO(tourRepository.findAll(spec, Sort.by(Sort.Direction.ASC, parameter)));
    }

    @Override
    public List<TourDTO> getComingTours(String userId) { // TODO
        List<Integer> tourIds = orderService.getOrdersByUser(userId).stream()
                .map(OrderDTO::getTourId)
                .collect(Collectors.toList());
        GenericSpecification<Tour> spec = new GenericSpecification<>("id", "in", tourIds);
        return tourToDTO(tourRepository.findAll(spec));
        /*return orderService.getOrdersByUser(userId)
                .stream()
                .flatMap(orderDTO -> getAllTours().stream().filter(tourDTO -> orderDTO.getTourId() == tourDTO.getId()))
                .collect(Collectors.toList());*/
    }

    @Override
    public List<TourDTO> getGuideTours(String guideId) {
        GenericSpecification<Tour> spec = new GenericSpecification<>("guideId", "eq", guideId);
        return tourToDTO(tourRepository.findAll(spec));
    }

    @Override
    public List<TourDTO> getFavoriteTours(Long userId) {
        return null;
    }

    @Override
    public void addTour(TourDTO tourDTO) {
        tourRepository.save(new ModelMapper().map(tourDTO, Tour.class)); // TODO
    }

    @Override
    public void updateTour(TourDTO tourDTO) {
    }

    @Override
    public void deleteTour(Integer tourId) {
        tourRepository.deleteById(tourId);
    }
}
