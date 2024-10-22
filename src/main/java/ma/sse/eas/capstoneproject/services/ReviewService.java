package ma.sse.eas.capstoneproject.services;

import ma.sse.eas.capstoneproject.persistence.dtos.ReviewDto;
import ma.sse.eas.capstoneproject.persistence.entities.Review;
import ma.sse.eas.capstoneproject.persistence.entities.Suggestion;
import ma.sse.eas.capstoneproject.persistence.repositories.ReviewRepository;
import ma.sse.eas.capstoneproject.persistence.repositories.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private SuggestionRepository suggestionRepository;

    public Review createReview(Long suggestionId, ReviewDto reviewDto) {
        Suggestion suggestion = suggestionRepository.findById(suggestionId)
                .orElseThrow(() -> new IllegalArgumentException("Suggestion not found"));
        Review review = new Review(suggestion, reviewDto.getReviewerId(), reviewDto.getComment(), reviewDto.getRating());
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsForSuggestion(Long suggestionId) {
        return reviewRepository.findBySuggestionId(suggestionId);
    }

    public Review updateReview(Long reviewId, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());
        return reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
