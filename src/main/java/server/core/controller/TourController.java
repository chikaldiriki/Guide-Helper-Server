package server.core.controller;

import org.springframework.format.annotation.DateTimeFormat;
import server.core.dto.TourDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.core.service.TourService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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

    @GetMapping("/city={city}")
    public List<TourDTO> getToursByCitySortedByOptionalParameter(@PathVariable String city,
                                                                 @RequestParam(required = false) String sorted) {
        if (Objects.equals(sorted, null)) {
            return tourService.getToursByCity(city);
        }
        return tourService.getToursByCitySortedByParameter(city, sorted);
    }

    @GetMapping("/coming/user_mail={userMail}/time")
    public List<TourDTO> getComingUserTours(@PathVariable String userMail,
                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                    LocalDateTime date) {
        return tourService.getComingTours(userMail, date);
    }

    @GetMapping("/guide={guideId}")
    public List<TourDTO> getToursByGuide(@PathVariable String guideId) {
        return tourService.getToursByGuide(guideId);
    }

    @GetMapping("/favorites/{userMail}")
    public List<TourDTO> getFavoriteTours(@PathVariable String userMail) {
        return tourService.getFavoriteTours(userMail);
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