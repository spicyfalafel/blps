package itmo.blps.lab.repository;

import itmo.blps.lab.entity.Medication;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends CrudRepository<Medication, Long> {

    @Query("select id, title from medication")
    List<Medication> findAllReturnTitleAndId();
    @Query("select * from medication where title ilike '%:title%'")
    List<Medication> findByTitle(String title);
//    @Modifying
//    @Query("UPDATE medication SET title = :title WHERE medication_id = :id")
//    boolean updateByTitle(@Param("id") Long id, @Param("title") String title);
}
