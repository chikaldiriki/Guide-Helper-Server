package server.core.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class FavoriteTourDTO {

    private String userMail;
    private Long tourId;
}
