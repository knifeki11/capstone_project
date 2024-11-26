package ma.sse.eas.capstoneproject.controllers;

import ma.sse.eas.capstoneproject.persistence.dtos.ReviewDto;
import ma.sse.eas.capstoneproject.persistence.dtos.SuggestionDto;
import ma.sse.eas.capstoneproject.services.ReviewService;
import ma.sse.eas.capstoneproject.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/suggestions")
public class SuggestionReviewController {

    private final SuggestionService suggestionService;
    private final ReviewService reviewService;

    @Autowired
    public SuggestionReviewController(SuggestionService suggestionService, ReviewService reviewService) {
        this.suggestionService = suggestionService;
        this.reviewService = reviewService;
    }

    // Suggestions Endpoints
    @PostMapping
    public ResponseEntity<SuggestionDto> createSuggestion(@RequestBody SuggestionDto suggestionDto) {
        var suggestion = suggestionService.createSuggestion(suggestionDto);
        return ResponseEntity.ok(new SuggestionDto(suggestion));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SuggestionDto>> getAllSuggestions() {
        var suggestions = suggestionService.getAllSuggestions();
        var suggestionDtos = suggestions.stream().map(SuggestionDto::new).toList();
        return ResponseEntity.ok(suggestionDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuggestionDto> getSuggestionById(@PathVariable Long id) {
        var suggestion = suggestionService.getSuggestionById(id);
        return ResponseEntity.ok(new SuggestionDto(suggestion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuggestionDto> updateSuggestion(
            @PathVariable Long id, @RequestBody SuggestionDto suggestionDto) {
        var updatedSuggestion = suggestionService.updateSuggestion(id, suggestionDto);
        return ResponseEntity.ok(new SuggestionDto(updatedSuggestion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuggestion(@PathVariable Long id) {
        suggestionService.deleteSuggestion(id);
        return ResponseEntity.noContent().build();
    }

    // Reviews Endpoints
    @PostMapping("/{suggestionId}/reviews")
    public ResponseEntity<ReviewDto> addReviewToSuggestion(
            @PathVariable Long suggestionId, @RequestBody ReviewDto reviewDto) {
        var review = reviewService.createReview(suggestionId, reviewDto);
        return ResponseEntity.ok(new ReviewDto(review));
    }

    @GetMapping("/{suggestionId}/reviews")
    public ResponseEntity<List<ReviewDto>> getReviewsForSuggestion(@PathVariable Long suggestionId) {
        var reviews = reviewService.getReviewsForSuggestion(suggestionId);
        var reviewDtos = reviews.stream().map(ReviewDto::new).toList();
        return ResponseEntity.ok(reviewDtos);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(
            @PathVariable Long reviewId, @RequestBody ReviewDto reviewDto) {
        var updatedReview = reviewService.updateReview(reviewId, reviewDto);
        return ResponseEntity.ok(new ReviewDto(updatedReview));
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
