package server.core.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.core.TourService;
import server.core.dto.TourDTO;
import server.core.model.Tour;
import server.core.repository.TourRepository;
import server.core.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private OrderService orderService;

    @Override
    public TourDTO getTour(Integer tourId) {
        return new ModelMapper().map(tourRepository.findById(tourId).get(), TourDTO.class);
    }

    @Override
    public List<TourDTO> getAllTours() {
        return StreamSupport.stream(tourRepository.findAll().spliterator(), false)
                .map(tour -> new ModelMapper().map(tour, TourDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TourDTO> getToursByCity(String city) {
        return getAllTours()
                .stream()
                .filter(tourDTO -> tourDTO.getCity().equals(city))
                .collect(Collectors.toList());
    }

    @Override
    public List<TourDTO> getComingTours(String userId) {
        return orderService.getAllOrders()
                .stream()
                .filter(orderDTO -> orderDTO.getCustomerMail().equals(userId))
                .flatMap(orderDTO -> getAllTours().stream().filter(tourDTO -> orderDTO.getTourId() == tourDTO.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TourDTO> getGuideTours(String guideId) {
        return getAllTours()
                .stream()
                .filter(tourDTO -> tourDTO.getGuide().equals(guideId))
                .collect(Collectors.toList());
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
