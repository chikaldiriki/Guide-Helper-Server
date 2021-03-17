package server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Long id;
    private String login;
    private Boolean isGuide;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String city;
    private String description;
}
