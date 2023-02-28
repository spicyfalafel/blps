package itmo.blps.lab.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
public class Review {
    @Id
    Long reviewId;
    private boolean approved;
    int mark;
    String name;
    String email;
    String review;
}
