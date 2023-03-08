package itmo.blps.lab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewAnswer {
    Boolean approved;
    String status;
}
