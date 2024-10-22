package ma.sse.eas.capstoneproject.persistence.repositories;
import ma.sse.eas.capstoneproject.persistence.entities.Process;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProcessRepository extends BaseRepository<Process> {
    @Query("select p from Process p where p.tenant.id = ?1")
    List<Process> findByTenantId(Long id);
}
