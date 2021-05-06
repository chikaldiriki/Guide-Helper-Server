package server.core.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class TourDTO {

    private String title;
    private String city;
    private String description;
    private String guide;
    private Long cost;
    private Byte[] image;
}
