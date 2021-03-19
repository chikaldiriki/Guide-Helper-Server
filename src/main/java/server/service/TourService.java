package server.service;

import org.springframework.stereotype.Service;
import server.dto.TourDTO;

import java.util.List;

public interface TourService {
    TourDTO getTour(Long tourId);

    List<TourDTO> getAllTours();

    List<TourDTO> getToursByCity(String city);

    List<TourDTO> getComingTours(Long userId);

    List<TourDTO> getGuideTours(Long guideId);

    List<TourDTO> getFavoriteTours(Long userId);

    void addTour(TourDTO tourDTO);

    void updateTour(TourDTO tourDTO);

    void deleteTour(Long tourId);
}
