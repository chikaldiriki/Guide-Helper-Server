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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
@Table(name = "Keywords")
public class Keyword {

    @Id
    @Column(name="keyword_id")
    private int id;

    @Column(name="chat_id")
    private int chatId;

    @Column(name="word")
    private String word;
}
