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
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String city;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return isGuide == userDTO.isGuide && Objects.equals(userMail, userDTO.userMail) && Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(phoneNumber, userDTO.phoneNumber) && Objects.equals(city, userDTO.city) && Objects.equals(description, userDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userMail, isGuide, firstName, lastName, phoneNumber, city, description);
    }
}
