package server.core.controller;

import server.core.dto.TourDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.core.service.TourService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/all")
    public List<TourDTO> getAllTours() {
        return tourService.getAllTours();
    }

    @GetMapping("/id={tourId}")
    public TourDTO getTour(@PathVariable Long tourId) {
        return tourService.getTour(tourId);
    }

    @GetMapping("/{city}")
    public List<TourDTO> getToursByCitySortedByOptionalParameter(@PathVariable String city,
                                                                 @RequestParam(required = false) String sorted) {
        if (Objects.equals(sorted, null)) {
            return tourService.getToursByCity(city);
        }
        return tourService.getToursByCitySortedByParameter(city, sorted);
    }

    @GetMapping("/{userId}/coming")
    public List<TourDTO> getComingUserTours(@PathVariable String userId) {
        return tourService.getComingTours(userId);
    }

    @GetMapping("/{guideId}")
    public List<TourDTO> getToursByGuide(@PathVariable String guideId) {
        return tourService.getToursByGuide(guideId);
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
    public void updateTour(@RequestBody TourDTO newTour, @PathVariable Long tourId) {
        tourService.updateTour(newTour, tourId);
    }

    @DeleteMapping("/{tourId}")
    public void deleteTour(@PathVariable Long tourId) {
        tourService.deleteTour(tourId);
    }
}