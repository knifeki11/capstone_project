package ma.sse.eas.capstoneproject.persistence.repositories;

import ma.sse.eas.capstoneproject.persistence.entities.Tenant;
import ma.sse.eas.capstoneproject.persistence.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface UserRepository extends BaseRepository<User> {
    User findByUsername(String username);

    @Query("select u from User u where u.active = true")
    List<User> findActiveUsers();


    @Query("select u from User u where u.tenant = ?1")
    List<User> findByTenant(Tenant tenant);



}
