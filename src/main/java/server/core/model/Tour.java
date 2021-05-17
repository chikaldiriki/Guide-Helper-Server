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
@Table (name = "Tours")
public class Tour {

    @Id
    @Column (name = "tour_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "title")
    private String title;

    @Column (name = "city")
    private String city;

    @Column (name = "guide_mail")
    private String guide;

    @Column (name = "cost")
    private Long cost;

    @Column (name = "description")
    private String description;

    @Column (name = "tour_image")
    private Byte[] image;

}
