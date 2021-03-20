package server.chat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
@Table(name = "Messages")
public class Message {

    @Id
    @Column (name = "message_id")
    private int id;

    @Column (name = "chat_id")
    private int chatId;

    @Column (name = "sender_mail")
    private String senderMail;

    @Column (name = "reciever_mail") //TODO: receiver
    private String receiverMail;

    @Column (name = "text")
    private String text;

    @Column (name = "dispatch_time")
    private Timestamp dispatchTime;

    @Column (name = "status")
    private boolean status;

}
