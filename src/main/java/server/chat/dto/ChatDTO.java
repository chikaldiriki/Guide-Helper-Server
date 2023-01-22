package server.chat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ChatDTO {

    @Schema(description = "Почта первого собеседника")
    private String firstUserMail;

    @Schema(description = "Почта второго собеседника")
    private String secondUserMail;

    @Schema(description = "Имя первого собеседника")
    private String firstUserName;

    @Schema(description = "Имя второго собеседника")
    private String secondUserName;

    @Schema(description = "Аватар первого собеседника")
    private String firstUserPhoto;

    @Schema(description = "Аватар второго собеседника")
    private String secondUserPhoto;
}
