package server.core.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserDTO {

    private String userMail;
    private Boolean isGuide;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String city;
    private String description;
}
