package server.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.core.dto.FavoriteTourDTO;
import server.core.service.FavoriteTourService;

@RestController
@RequestMapping("/favorites")
public class FavoriteTourController {

    @Autowired
    FavoriteTourService favoriteTourService;

    @PostMapping("/add")
    public void addFavoriteTour(@RequestBody FavoriteTourDTO newFavorite) {
        favoriteTourService.addFavoriteTour(newFavorite);
    }

    @DeleteMapping("/delete")
    public void deleteFavoriteTour(@RequestBody FavoriteTourDTO favoriteTourDTO) {
        favoriteTourService.deleteFavoriteTour(favoriteTourDTO);
    }

}
