package server.core.service;

import org.springframework.stereotype.Service;
import server.core.dto.TourDTO;

import java.util.List;

@Service
public interface TourService {
    TourDTO getTour(Integer tourId);

    List<TourDTO> getAllTours();

    List<TourDTO> getToursByCity(String city);

    List<TourDTO> getComingTours(String userId);

    List<TourDTO> getGuideTours(String guideId);

    List<TourDTO> getFavoriteTours(Long userId);

    void addTour(TourDTO tourDTO);

    void updateTour(TourDTO tourDTO);

    void deleteTour(Integer tourId);
}
