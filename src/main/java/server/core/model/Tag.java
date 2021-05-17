package server.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
@Table(name = "Tags")
public class Tag {

    @Id
    @Column (name = "tag_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column (name = "user_mail")
    private String tagHolderMail;

    @Column (name = "tag")
    private String tagText;


}
