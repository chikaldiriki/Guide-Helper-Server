package server.core.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;

@Getter
@Setter
@Accessors(chain = true)
public class OrderDTO {
    private String customerMail;
    private Long tourId;
    private Date tourTime;
}
