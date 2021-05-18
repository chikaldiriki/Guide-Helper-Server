package server.core.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class OrderDTO {
    private String customerMail;
    private Long tourId;
    private LocalDateTime tourTime;
}
