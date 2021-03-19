package server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tour {

    private Long id;
    private String title;
    private String city;
    private String description;
    private Long guideId;
    private Long cost;
}
