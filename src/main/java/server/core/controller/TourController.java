package server.core.controller;

import server.core.dto.TourDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.core.TourService;

import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("")
    public List<TourDTO> getAllTours() {
        return tourService.getAllTours();
    }

    @GetMapping("/{city}")
    public List<TourDTO> getToursByCity(@PathVariable String city) {
        return tourService.getToursByCity(city);
    }

    @GetMapping("/{userId}/coming")
    public String getComingUserTours(@PathVariable Long userId) {
        return null;
    }

    @GetMapping("/{guideId}/coming")
    public String getComingGuideTours(@PathVariable Long guideId) {
        return null;
    }

    @GetMapping("/{userId}/favorites")
    public String getFavoriteTours(@PathVariable Long userId) {
        return null;
    }

    @PostMapping("")
    public void addTour(@RequestBody TourDTO tourDTO) {
        tourService.addTour(tourDTO);
    }

    @PutMapping("/{tourId}")
    public String updateTour(@PathVariable Long tourId) {
        return null;
    }

    @DeleteMapping("/{tourId}")
    public void deleteTour(@PathVariable Integer tourId) {
        tourService.deleteTour(tourId);
    }
}