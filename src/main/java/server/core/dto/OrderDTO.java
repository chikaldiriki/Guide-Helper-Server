package server.core.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;

@Getter
@Setter
@Accessors(chain = true)
public class OrderDTO {

    private int id;
    private String customerMail;
    private int tourId;
    private Date tourTime;
}
