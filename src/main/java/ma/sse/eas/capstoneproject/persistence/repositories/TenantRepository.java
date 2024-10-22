package ma.sse.eas.capstoneproject.persistence.repositories;

import ma.sse.eas.capstoneproject.persistence.entities.Tenant;

import java.util.Optional;

public interface TenantRepository extends BaseRepository<Tenant> {
    Optional<Tenant> findByName(String name);

}
