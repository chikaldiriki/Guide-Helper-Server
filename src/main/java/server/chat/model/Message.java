package server.chat.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class Message {
    @Id
    private String id;
    private String dialogId;
    private String senderId;
    private String receiverId;
    private String data;
    private Date dispatchTime;
    private MessageStatus status;
}
