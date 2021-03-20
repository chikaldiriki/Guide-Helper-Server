package server.controller;

import server.dto.TourDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.service.TourService;

import java.util.List;

@RestController
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/tours")
    public List<TourDTO> getAllTours() {
        return tourService.getAllTours();
    }

    @GetMapping("/tours/{city}")
    public List<TourDTO> getToursByCity(@PathVariable String city) {
        return tourService.getToursByCity(city);
    }

    @GetMapping("/tours/{userId}/coming")
    public String getComingUserTours(@PathVariable Long userId) {
        return null;
    }

    @GetMapping("/tours/{guideId}/coming")
    public String getComingGuideTours(@PathVariable Long guideId) {
        return null;
    }

    @GetMapping("/tours/{userId}/favorites")
    public String getFavoriteTours(@PathVariable Long userId) {
        return null;
    }

    @PostMapping("/tours")
    public void addTour(@RequestBody TourDTO tourDTO) {
        tourService.addTour(tourDTO);
    }

    @PutMapping("/tours/{tourId}")
    public String updateTour(@PathVariable Long tourId) {
        return null;
    }

    @DeleteMapping("/tours/{tourId}")
    public void deleteTour(@PathVariable Integer tourId) {
        tourService.deleteTour(tourId);
    }
}