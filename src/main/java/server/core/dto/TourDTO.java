package server.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "Сущность экскурсии")
public class TourDTO {

    @Schema(description = "Уникальный идентификатор экскурсии")
    private Long id;

    @Schema(description = "Название экскурсии")
    private String title;

    @Schema(description = "Название города экскурсии")
    private String city;

    @Schema(description = "Описание экскурсии")
    private String description;

    @Schema(description = "Почта гида")
    private String guide;

    @Schema(description = "Стоимость экскурсии")
    private Long cost;

    @Schema(description = "Картинка экскурсии")
    private Byte[] image;
}
