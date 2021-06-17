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
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "user_mail")
    private String userMail;

    @Column(name = "is_guide")
    private boolean isGuide;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "description")
    private String description;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "token")
    private String token;

    @Override
    public String toString() {
        return "User{" +
                "user_mail=" + userMail +
                ", is_guide=" + isGuide +
                ", name=" + name +
                ", phone_number=" + phoneNumber +
                ", city=" + city +
                ", description=" + description +
                "}";
    }
}
