package server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.core.dto.FavoriteTourDTO;
import server.core.model.FavoriteTour;
import server.core.repository.FavoriteTourRepository;
import server.mapper.Mapper;
import server.specifications.GenericSpecification;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteTourService {

    @Autowired
    FavoriteTourRepository favoriteTourRepository;

    public void addFavoriteTour(FavoriteTourDTO newFavorite) {
        //TODO: id increment
        favoriteTourRepository.save(Mapper.map(newFavorite, FavoriteTour.class));
    }

    public void deleteFavoriteTour(FavoriteTourDTO tourToDelete) {
        GenericSpecification<FavoriteTour> userSpec =
                new GenericSpecification<>("userMail", "eq", tourToDelete.getUserMail());
        GenericSpecification<FavoriteTour> tourSpec =
                new GenericSpecification<>("tourId", "eq", tourToDelete.getTourId());

        List<FavoriteTour> favoriteTours = favoriteTourRepository.findAll(userSpec.and(tourSpec));
        favoriteTourRepository.deleteAll(favoriteTours);
    }

    public List<Long> getToursIdsByUser(String userMail) {
        GenericSpecification<FavoriteTour> spec = new GenericSpecification<>("userMail", "eq", userMail);
        return favoriteTourRepository.findAll(spec).stream().map(FavoriteTour::getTourId).collect(Collectors.toList());
    }
}
