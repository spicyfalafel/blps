package itmo.blps.lab.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
public class Review {
    @Id
    private Long reviewId;
    private Boolean approved;
    private Long mark;
    private String name;
    private String email;
    private String review;
}
