package itmo.blps.lab.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.*;

@Table("review")
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = false)
public class Review {
    @Id
    @Null
    private Long reviewId;
    @JsonIgnore
    @Null
    private Boolean approved;
    @NotNull
    @Min(value = 1, message = "min value is 1")
    @Max(value = 5, message = "max value is 5")
    private Long mark;
    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
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
