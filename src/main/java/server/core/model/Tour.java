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
@Table (name = "Tours")
public class Tour {

    @Id
    @Column (name = "tour_id")
    private int id;

    @Column (name = "title")
    private String title;

    @Column (name = "city")
    private String city;

    @Column (name = "guide_mail")
    private String guide;

    @Column (name = "cost")
    private int cost;

    @Column (name = "tour_image")
    private Byte[] image;

}
