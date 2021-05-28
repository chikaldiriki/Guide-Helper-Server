package server.core.service;

import org.springframework.stereotype.Service;
import server.core.dto.TourDTO;
import server.core.model.Tour;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

@Service
public class ImageService {

    public static final String imagesDirectory = "./images/";

    public static final String ERROR = "ERROR";

    public static String saveTourImage(TourDTO tour) {
        byte[] image = Base64.getDecoder().decode(tour.getImage());
        String imagePath = imagesDirectory +  tour.getGuide() + "/" + tour.getId();
        try {
            Files.write(Path.of(imagePath), image);
        } catch (IOException ex) {
            return ERROR;
        }
        return imagePath;
    }

    public static String getTourImageCode(Tour tour) {
        try {
            byte[] image = Files.readAllBytes(Path.of(tour.getImage()));
            return Base64.getEncoder().encodeToString(image);
        } catch (IOException ex) {
            return ERROR;
        }
    }
}
