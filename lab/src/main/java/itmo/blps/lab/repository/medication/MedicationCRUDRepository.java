package itmo.blps.lab.repository.medication;

import itmo.blps.lab.dto.Medication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationCRUDRepository extends PagingAndSortingRepository<Medication, Long> {
    @Query("select medication_id, title from medication limit :limit offset :offset")
    List<Medication> findAllReturnTitleAndId(@Param("limit") Integer limit, @Param("offset") Long offset);
    List<Medication> findByTitleContainingIgnoreCase( String title, Pageable pageable);
    List<Medication> findByTitleStartingWithIgnoreCase(String title, Pageable pageable);
}
