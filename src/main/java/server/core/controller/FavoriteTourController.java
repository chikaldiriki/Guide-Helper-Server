package server.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.core.dto.FavoriteTourDTO;
import server.core.service.FavoriteTourService;

@Tag(
        name = "Favorite Tour Controller",
        description = "Контроллер избранных экскурсий. Избранная экскурсия : id пользователя и id экскурсии"
)
@RestController
@RequestMapping("/favorites")
public class FavoriteTourController {

    @Autowired
    FavoriteTourService favoriteTourService;

    @Operation(summary = "Добавить избранную экскурсию")
    @PostMapping("/add")
    public void addFavoriteTour(@RequestBody FavoriteTourDTO newFavorite) {
        favoriteTourService.addFavoriteTour(newFavorite);
    }

    @Operation(summary = "Является ли экскурсия избранной у юзера")
    @GetMapping("/{userMail}/{tourId}")
    public boolean isFavorite(@PathVariable String userMail, @PathVariable Long tourId) {
        return favoriteTourService.isFavorite(userMail, tourId);
    }

    @Operation(summary = "Удалить избранную экскурсию")
    @DeleteMapping("/delete/{userMail}/{tourId}")
    public void deleteFavoriteTour(@PathVariable String userMail, @PathVariable Long tourId) {
        favoriteTourService.deleteFavoriteTour(new FavoriteTourDTO().setUserMail(userMail).setTourId(tourId));
    }


}
