package ma.sse.eas.capstoneproject.persistence.dtos;
import ma.sse.eas.capstoneproject.persistence.entities.Review;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
public class ReviewDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 10274293479102983L;
    private Long id;
    private Long suggestionId;
    private Long reviewerId;
    private String comment;
    private float rating;
    private Boolean isApproved;

    public ReviewDto() {}

    public ReviewDto(Long suggestionId, Long reviewerId, String comment, float rating, Boolean isApproved) {
        this.suggestionId = suggestionId;
        this.reviewerId = reviewerId;
        this.comment = comment;
        this.rating = rating;
        this.isApproved = isApproved;
    }

    public ReviewDto(Review review) {
        this.id = review.getId();
        this.suggestionId = review.getSuggestion().getId();
        this.reviewerId = review.getReviewer().getId();
        this.comment = review.getComment();
        this.rating = review.getRating();
        this.isApproved = review.getIsApproved();
    }

}
