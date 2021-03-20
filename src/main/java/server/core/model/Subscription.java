package server.core.model;

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
@Table(name = "Subscriptions") //TODO: subscriPtions
public class Subscription {

    @Id
    @Column (name = "subscription_id")
    private int id;

    @Column (name = "user_mail")
    private String userMail;

    @Column (name = "guide_mail")
    private String guideMail;

}
