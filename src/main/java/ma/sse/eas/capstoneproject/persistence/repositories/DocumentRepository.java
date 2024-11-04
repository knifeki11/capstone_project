//package ma.sse.eas.capstoneproject.persistence.repositories;
//
//import ma.sse.eas.capstoneproject.persistence.entities.Document;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface DocumentRepository extends BaseRepository<Document> {
//    @Query("select d from Document d where d.process.id = ?1")
//    List<Document> findByProcessId(Long id);
//}
