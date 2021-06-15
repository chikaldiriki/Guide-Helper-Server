package server.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private String image;

    @Schema(description = "Размер группы")
    private int capacity;

    @Schema(description = "Продолжительность экскурсии")
    private Time duration;
}
