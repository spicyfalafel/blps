package itmo.blps.lab.repository.medication;

import itmo.blps.lab.entity.Medication;
import itmo.blps.lab.entity.MedicationIdTitle;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationCRUDRepository extends CrudRepository<Medication, Long> {
    @Query("select medication_id, title from medication")
    List<MedicationIdTitle> findAllReturnTitleAndId();
    List<Medication> findByTitleContainingIgnoreCase(String title);
}
