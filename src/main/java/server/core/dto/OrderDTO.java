package server.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
public class OrderDTO {

    @Schema(description = "Почта заказчика")
    private String customerMail;

    @Schema(description = "Id заказанной экскурсии")
    private Long tourId;

    @Schema(description = "Дата экскурсии")
    private LocalDateTime tourTime;
}
