package ma.sse.eas.capstoneproject.persistence.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Review extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Suggestion suggestion;

    @ManyToOne(fetch = FetchType.LAZY)
    private User reviewer;

    @NotNull
    @Column(length = 500)
    private String comment;

    private float rating;

    private Boolean isApproved = false;

    public Review(Suggestion suggestion, User reviewer, String comments) {
        this.suggestion = suggestion;
        this.reviewer = reviewer;
        this.comment = comments;
    }

    protected Review() {}

    public Review(Suggestion suggestion, Long reviewerId, String comment, float rating) {
        this.suggestion = suggestion;
        this.reviewer = new User();
        this.reviewer.setId(reviewerId);
        this.comment = comment;
    }
}

