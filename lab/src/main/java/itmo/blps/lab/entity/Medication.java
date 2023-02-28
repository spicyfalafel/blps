package itmo.blps.lab.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Set;

@Data
@Table("medication")
public class Medication {
    @Id
    private long medicationId;

    private String title;
    private String description;
    @MappedCollection(idColumn = "medication_id")
    private Set<Review> reviews;

    public Medication(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
