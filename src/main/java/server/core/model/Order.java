package server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
@Table(name = "Orders")
public class Order {

    @Id
    @Column (name = "order_id")
    private int id;

    @Column (name = "user_mail")
    private String customerMail;

    @Column (name = "tour_id")
    private int tourId;

    @Column (name = "date_time")
    private Date tourTime;


}
