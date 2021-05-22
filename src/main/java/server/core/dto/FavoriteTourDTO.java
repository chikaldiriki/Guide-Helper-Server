package server.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class FavoriteTourDTO {

    @Schema(description = "Почта пользователя, выбравшего экскурсию")
    private String userMail;

    @Schema(description = "Id экскурсии")
    private Long tourId;
}
