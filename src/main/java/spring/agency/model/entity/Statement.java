package spring.agency.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "statements")
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(length = 20)
    private String status;

    @Column()
    private String about;

    @Column()
    @PositiveOrZero(message = "You put the wrong price")
    private Double price;

    @Column()
    private String comment;

    @Column(name = "master_name")
    private String masterName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
