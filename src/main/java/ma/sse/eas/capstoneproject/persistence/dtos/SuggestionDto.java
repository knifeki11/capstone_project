package ma.sse.eas.capstoneproject.persistence.dtos;

import lombok.Data;
import ma.sse.eas.capstoneproject.persistence.entities.Suggestion;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class SuggestionDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private Long processId;
    private String status;  // Considering enum value as String to keep it simple for DTO
    private List<ReviewDto> reviews;

    protected SuggestionDto() {}

    public SuggestionDto(String title, String content, Long userId, Long processId, String status) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.processId = processId;
        this.status = status;
    }

    public SuggestionDto(Suggestion suggestion) {
        this.id = suggestion.getId();
        this.title = suggestion.getTitle();
        this.content = suggestion.getContent();
        this.userId = suggestion.getUser() != null ? suggestion.getUser().getId() : null;
        this.processId = suggestion.getProcess() != null ? suggestion.getProcess().getId() : null;
        this.status = suggestion.getStatus() != null ? suggestion.getStatus().name() : null; // Convert enum to String
    }


}
