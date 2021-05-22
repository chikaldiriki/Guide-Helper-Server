package server.chat.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ChatDTO {
    @Schema(description = "Почта первого собеседника")
    private String firstUserMail;

    @Schema(description = "Почта второго собеседника")
    private String secondUserMail;

    @Schema(description = "Имя первого собеседника")
    private String firstUserName;

    @Schema(description = "Имя второго собеседника")
    private String secondUserName;
}
