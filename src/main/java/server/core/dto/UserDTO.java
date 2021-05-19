package server.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "Сущность пользователя")
public class UserDTO {

    @Schema(description = "Почта пользователя")
    private String userMail;

    @Schema(description = "Является ли пользователь гидом")
    private boolean isGuide;

    @Schema(description = "Имя пользователя")
    private String firstName;

    @Schema(description = "Имя пользователя")
    private String lastName;

    @Schema(description = "Номер телефона")
    private String phoneNumber;

    @Schema(description = "Город пользователя")
    private String city;

    @Schema(description = "Описание пользователя о себе")
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
