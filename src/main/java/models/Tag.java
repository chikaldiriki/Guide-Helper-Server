package models;

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
@Table(name = "Tags")
public class Tag {

    @Id
    @Column (name = "tag_id")
    private int id;

    @Column (name = "user_mail")
    private String tagHolderMail;

    @Column (name = "tag")
    private String tagText;


}
