package ma.sse.eas.capstoneproject.persistence.entities;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Suggestion extends BaseEntity {

    @NotNull
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id", referencedColumnName = "id")
    private Process process;

    @OneToMany(mappedBy = "suggestion", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("rating asc")
    private List<Review> reviews;

    @NotNull
    @Column(length = 1000)
    private String content;

    @Enumerated(EnumType.STRING)
    private SuggestionStatus status;

    protected Suggestion() {}

    public Suggestion(String title, String content, User user, Process process) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.process = process;
        this.status = SuggestionStatus.PENDING;
    }

    public void updateStatus(SuggestionStatus newStatus) {
        if (this.status == SuggestionStatus.APPROVED || this.status == SuggestionStatus.REJECTED) {
            throw new IllegalArgumentException("Cannot change status once approved or rejected.");
        }
        this.status = newStatus;
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setSuggestion(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setSuggestion(null);
    }
}
