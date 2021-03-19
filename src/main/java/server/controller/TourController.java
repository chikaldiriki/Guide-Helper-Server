package server.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class TourController {

    @GetMapping("tours/")
    public String getAllTours() {
        return null;
    }

    @GetMapping("tours/{city}")
    public String getToursByCity(@PathVariable String city) {
        return null;
    }

    @GetMapping("tours/{userId}/coming")
    public String getComingUserTours(@PathVariable Long userId) {
        return null;
    }

    @GetMapping("tours/{guideId}/coming")
    public String getComingGuideTours(@PathVariable Long guideId) {
        return null;
    }

    @GetMapping("tours/{userId}/favorites")
    public String getFavoriteTours(@PathVariable Long userId) {
        return null;
    }

    @PostMapping("tours/")
    public String addTour() {
        return null;
    }

    @PutMapping("tours/{tourId}")
    public String updateTour(@PathVariable Long tourId) {
        return null;
    }

    @DeleteMapping("tours/{tourId}")
    public String deleteTour(@PathVariable Long tourId) {
        return null;
    }
}