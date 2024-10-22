package ma.sse.eas.capstoneproject.persistence.repositories;


import ma.sse.eas.capstoneproject.persistence.entities.Review;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends BaseRepository<Review> {
    @Query("select r from Review r where r.suggestion.id = ?1")
    List<Review> findBySuggestionId(Long id);

    @Query("select r from Review r where r.reviewer.id = ?1")
    List<Review> findByReviewerId(Long id);

}
