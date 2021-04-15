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
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "user_mail")
    private String userMail;

    @Column(name = "is_guide")
    private boolean isGuide;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "description")
    private String description;

    @Override
    public String toString() {
        return "User{" +
                "user_mail=" + userMail +
                ", is_guide=" + isGuide +
                ", first_name=" + firstName +
                ", last_name=" + lastName +
                ", phone_number=" + phoneNumber +
                ", city=" + city +
                ", description=" + description +
                "}";
    }
}
