package server.chat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Getter
@Setter
@Accessors(chain = true)
public class MessageDTO {

    @Schema(description = "Почта адресанта")
    private String senderMail;

    @Schema(description = "Почта адресата")
    private String receiverMail;

    @Schema(description = "Имя адресанта")
    private String senderName;

    @Schema(description = "Имя адресата")
    private String receiverName;

    @Schema(description = "Текст сообщения")
    private String text;

    @Schema(description = "Дата отправки сообщения")
    private Timestamp dispatchTime;
}