package itmo.blps.lab.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("medication")
@NoArgsConstructor
@Getter
@Setter
public class Medication {
    @Id
    private Long medicationId;
    private String title;
    private String description;
    private String dosage;
    private String reasonToUse;
    private String sideEffects;
    private Boolean byRecipe;
    private String storageConditions;
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
