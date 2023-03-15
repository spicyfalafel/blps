package itmo.blps.lab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("review")
@NoArgsConstructor
@Getter
@Setter
public class Review {
    @Id
    private Long reviewId;
    @JsonIgnore
    private Boolean approved;
    private Long mark;
    private String name;
    private String email;
    private String review;

    public Review(Long reviewId, Boolean approved, Long mark, String name, String email, String review) {
        this.reviewId = reviewId;
        this.approved = approved;
        this.mark = mark;
        this.name = name;
        this.email = email;
        this.review = review;
    }
}
