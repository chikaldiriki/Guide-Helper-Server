package server.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import server.core.dto.TourDTO;
import server.core.service.TourService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Tag(name = "Tour Controller", description = "Контроллер экскурсий")
@RestController
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private TourService tourService;

    @Operation(summary = "Получить все список всех экскурсий")
    @GetMapping("/all")
    public List<TourDTO> getAllTours() {
        return tourService.getAllTours();
    }

    @Operation(summary = "Получить экскурсию по её id")
    @GetMapping("/id={tourId}")
    public TourDTO getTour(@PathVariable Long tourId) {
        return tourService.getTour(tourId);
    }

    @Operation(
            summary = "Получить список всех экскурсий в конкретном городе",
            description = "Можно отсортировать полученный список по опциональному параметру. " +
                    "Например, \"tours/city=Moscow?sorted=cost\" : " +
                    "список всех экскурсий в городе Moscow, отсортированные в порядке возрастания cost"
    )
    @GetMapping("/city={city}")
    public List<TourDTO> getToursByCitySortedByOptionalParameter(@PathVariable String city,
                                                                 @RequestParam(required = false) String sorted) {
        if (Objects.equals(sorted, null)) {
            return tourService.getToursByCity(city);
        }
        return tourService.getToursByCitySortedByParameter(city, sorted);
    }

    @Operation(
            summary = "Получить список всех экскурсий, заказанных пользователем, с датой начала не раньше time",
            description = "Время в формате YYYY-MM-DD'T'hh:mm:ss, например : 2007-12-03T10:15:30"
    )
    @GetMapping("/coming/user_mail={userMail}/time")
    public List<TourDTO> getComingUserTours(@PathVariable String userMail,
                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                    LocalDateTime date) {
        return tourService.getComingTours(userMail, date);
    }

    @Operation(summary = "Получить список всех экскурсий гида")
    @GetMapping("/guide={guideId}")
    public List<TourDTO> getToursByGuide(@PathVariable String guideId) {
        return tourService.getToursByGuide(guideId);
    }

    @Operation(summary = "Получить список всех избранных экскурсий пользователя")
    @GetMapping("/favorites/{userMail}")
    public List<TourDTO> getFavoriteTours(@PathVariable String userMail) {
        return tourService.getFavoriteTours(userMail);
    }

    @Operation(summary = "Добавить экскурсию")
    @PostMapping("")
    public void addTour(@RequestBody TourDTO tourDTO) {
        tourService.addTour(tourDTO);
    }

    @Operation(summary = "Изменить экскурсию по её id")
    @PutMapping("/{tourId}")
    public void updateTour(@RequestBody TourDTO newTour, @PathVariable Long tourId) {
        tourService.updateTour(newTour, tourId);
    }

    @Operation(summary = "Удалить экскурсию")
    @DeleteMapping("/{tourId}")
    public void deleteTour(@PathVariable Long tourId) {
        tourService.deleteTour(tourId);
    }
}