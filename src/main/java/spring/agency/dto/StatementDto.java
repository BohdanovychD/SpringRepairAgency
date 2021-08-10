package spring.agency.dto;

import lombok.*;
import spring.agency.model.entity.User;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StatementDto {
    private Long id;
    private String status;
    private String about;
    private Double price;
    private Date date;
    private User user;

}
