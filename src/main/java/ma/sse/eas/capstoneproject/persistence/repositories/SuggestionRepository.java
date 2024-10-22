package ma.sse.eas.capstoneproject.persistence.repositories;


import ma.sse.eas.capstoneproject.persistence.entities.Suggestion;
import ma.sse.eas.capstoneproject.persistence.entities.SuggestionStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionRepository extends BaseRepository<Suggestion> {

    @Query("select s from Suggestion s where s.process.id = ?1")
    List<Suggestion> findByProcessId(Long id);

    @Query("select s from Suggestion s where s.user.id = ?1")
    List<Suggestion> findByUserId(Long id);

    @Query("select s from Suggestion s where s.status = ?1")
    List<Suggestion> findByStatus(SuggestionStatus status);

    @Query("SELECT s FROM Suggestion s JOIN FETCH s.reviews WHERE s.id = ?1")
    Suggestion findByIdWithReviews(Long suggestionId);

}
