package ma.sse.eas.capstoneproject.services;

import ma.sse.eas.capstoneproject.persistence.dtos.SuggestionDto;
import ma.sse.eas.capstoneproject.persistence.entities.Suggestion;
import ma.sse.eas.capstoneproject.persistence.entities.SuggestionStatus;
import ma.sse.eas.capstoneproject.persistence.repositories.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SuggestionService {

    @Autowired
    private SuggestionRepository suggestionRepository;

    public Suggestion createSuggestion(SuggestionDto suggestionDto) {
        Suggestion suggestion = new Suggestion(
                suggestionDto.getTitle(),
                suggestionDto.getContent(),
                null,
                null
        );
        return suggestionRepository.save(suggestion);
    }

    public List<Suggestion> getAllSuggestions() {
        return suggestionRepository.findAll();
    }

    public Suggestion getSuggestionById(Long suggestionId) {
        return suggestionRepository.findById(suggestionId).orElseThrow(() -> new IllegalArgumentException("Suggestion not found"));
    }

    public Suggestion updateSuggestion(Long suggestionId, SuggestionDto suggestionDto) {
        Suggestion suggestion = suggestionRepository.findById(suggestionId)
                .orElseThrow(() -> new IllegalArgumentException("Suggestion not found"));

        suggestion.setTitle(suggestionDto.getTitle());
        suggestion.setContent(suggestionDto.getContent());
        suggestion.setStatus(SuggestionStatus.valueOf(suggestionDto.getStatus().toUpperCase()));

        return suggestionRepository.save(suggestion);
    }

    public void deleteSuggestion(Long suggestionId) {
        suggestionRepository.deleteById(suggestionId);
    }

    public void updateSuggestionStatus(Long suggestionId, SuggestionStatus status) {
        Suggestion suggestion = suggestionRepository.findById(suggestionId)
                .orElseThrow(() -> new IllegalArgumentException("Suggestion not found"));
        suggestion.updateStatus(status);
        suggestionRepository.save(suggestion);
    }

    public List<Suggestion> getSuggestionsByUser(Long userId) {
        return suggestionRepository.findByUserId(userId);
    }

    public List<Suggestion> getSuggestionsByStatus(SuggestionStatus status) {
        return suggestionRepository.findByStatus(status);
    }
}
