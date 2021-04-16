package server.core.controller;

import server.core.dto.TourDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.core.service.TourService;

import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {

    private final TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("")
    public List<TourDTO> getAllTours() {
        return tourService.getAllTours();
    }

    @GetMapping("/{city}")
    public List<TourDTO> getToursByCity(@PathVariable String city) {
        return tourService.getToursByCity(city);
    }

    @GetMapping("/{city}?sorted=cost")
    public List<TourDTO> getToursByCitySortedByCost(@PathVariable String city) {
        return tourService.getToursByCitySortedByCost(city);
    }

    @GetMapping("/{userId}/coming")
    public List<TourDTO> getComingUserTours(@PathVariable String userId) {
        return tourService.getComingTours(userId);
    }

    @GetMapping("/{guideId}/coming")
    public List<TourDTO> getComingGuideTours(@PathVariable String guideId) {
        return null;
    }

    @GetMapping("/{userId}/favorites")
    public List<TourDTO> getFavoriteTours(@PathVariable String userId) {
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