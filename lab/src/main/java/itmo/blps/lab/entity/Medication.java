package itmo.blps.lab.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Data
@Table("medication")
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

    public Medication(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
