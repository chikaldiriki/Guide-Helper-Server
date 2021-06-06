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
        favoriteTourRepository.save(mapFavoriteTourDTOtoFavoriteTour(newFavorite));
    }

    public void deleteFavoriteTour(FavoriteTourDTO tourToDelete) {
        List<FavoriteTour> favoriteTours = favoriteTourRepository.getFavoriteTourByTourIdAndUserMail(
                tourToDelete.getTourId(), tourToDelete.getUserMail()
        );
        favoriteTourRepository.deleteAll(favoriteTours);
    }

    public boolean isFavorite(String userMail, Long tourId) {
        List<FavoriteTour> favoriteTours = favoriteTourRepository.getFavoriteTourByTourIdAndUserMail(tourId, userMail);
        return !favoriteTours.isEmpty();
    }

    public List<Long> getToursIdsByUser(String userMail) {
        GenericSpecification<FavoriteTour> spec = new GenericSpecification<>("userMail", "eq", userMail);
        return favoriteTourRepository.findAll(spec).stream().map(FavoriteTour::getTourId).collect(Collectors.toList());
    }

    private FavoriteTour mapFavoriteTourDTOtoFavoriteTour(FavoriteTourDTO favoriteTourDTO) {
        return new FavoriteTour().setTourId(favoriteTourDTO.getTourId()).setUserMail(favoriteTourDTO.getUserMail());
    }

}
