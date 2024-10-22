package ma.sse.eas.capstoneproject.persistence.repositories;


import ma.sse.eas.capstoneproject.persistence.entities.Authority;

import java.util.List;

public interface AuthorityRepository extends BaseRepository<Authority> {
    List<Authority> findByUsername(String username);

    List<Authority> findByAuthority(String authority);
}
