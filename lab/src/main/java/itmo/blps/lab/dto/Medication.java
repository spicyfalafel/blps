package itmo.blps.lab.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Set;

@Table("medication")
@NoArgsConstructor
@Getter
@Setter
public class Medication {
    public interface New { }
    public interface Exist {}
    public interface TitleAndId{}
    @Id
    @Null(groups = {New.class}, message = "id should not be in body for POST")
    @NotNull(groups = {Exist.class})
    @JsonView({TitleAndId.class})
    private Long medicationId;
    @JsonView({TitleAndId.class})
    @NotNull(groups = {New.class, TitleAndId.class, Exist.class}, message = "Title can't be empty")
    private String title;

    private String description;
    private String dosage;
    private String reasonToUse;
    private String sideEffects;
    private Boolean byRecipe;
    private String storageConditions;
    @Null
    @MappedCollection(idColumn = "medication_id")
    private Set<Review> reviews;

    public Medication(String title, String description, String dosage, String reasonToUse, String sideEffects, Boolean byRecipe, String storageConditions) {
        this.title = title;
        this.description = description;
        this.dosage = dosage;
        this.reasonToUse = reasonToUse;
        this.sideEffects = sideEffects;
        this.byRecipe = byRecipe;
        this.storageConditions = storageConditions;
    }
}
