package ma.sse.eas.capstoneproject.persistence.dtos;

import lombok.Data;

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
}
