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
    private String name;

    @Schema(description = "Номер телефона")
    private String phoneNumber;

    @Schema(description = "Город пользователя")
    private String city;

    @Schema(description = "Описание пользователя о себе")
    private String description;
  
    @Schema(description = "Url аватара пользователя из гугл почты")
    private String avatarUrl;
}
