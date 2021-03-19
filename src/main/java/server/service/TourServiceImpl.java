package server.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import server.dto.TourDTO;
import server.repository.TourRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;

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
        return null;
    }

    @Override
    public List<TourDTO> getComingTours(Long userId) {
        return null;
    }

    @Override
    public List<TourDTO> getGuideTours(Long guideId) {
        return null;
    }

    @Override
    public List<TourDTO> getFavoriteTours(Long userId) {
        return null;
    }

    @Override
    public void addTour(TourDTO tourDTO) {

    }

    @Override
    public void updateTour(TourDTO tourDTO) {

    }

    @Override
    public void deleteTour(Long tourId) {

    }
}
