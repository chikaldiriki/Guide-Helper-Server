package server.chat.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
@Table(name = "Chats")
public class Chat {

    @Id
    @Column (name = "chat_id")
    private int id;

    @Column (name = "first_user_mail")
    private String firstUserMail;

    @Column (name = "second_user_mail")
    private String secondUserMail;

}
