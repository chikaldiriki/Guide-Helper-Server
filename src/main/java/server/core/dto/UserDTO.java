package server.core.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
public class UserDTO {

    private String userMail;
    private boolean isGuide;
    private String name;
    private String phoneNumber;
    private String city;
    private String description;
    private String avatarUrl;

}
