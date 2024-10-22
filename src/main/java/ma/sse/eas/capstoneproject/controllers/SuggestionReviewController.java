package ma.sse.eas.capstoneproject.controllers;


import ma.sse.eas.capstoneproject.persistence.dtos.SuggestionDto;
import ma.sse.eas.capstoneproject.persistence.entities.Suggestion;
import ma.sse.eas.capstoneproject.persistence.entities.SuggestionStatus;
import ma.sse.eas.capstoneproject.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest/suggestions")
public class SuggestionReviewController {
    @Autowired
    private SuggestionService suggestionService;

    @PostMapping
    public ResponseEntity<Suggestion>
    createSuggestion(@RequestBody SuggestionDto suggestionDto) {
        Suggestion suggestion = suggestionService.createSuggestion(suggestionDto);
        return new ResponseEntity<>(suggestion, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Suggestion>> getAllSuggestions() {
        List<Suggestion> suggestions = suggestionService.getAllSuggestions();
        return new ResponseEntity<>(suggestions, HttpStatus.OK);
    }

    @GetMapping("/{suggestionId}")
    public ResponseEntity<Suggestion> getSuggestionById(@PathVariable Long suggestionId) {
        Suggestion suggestion = suggestionService.getSuggestionById(suggestionId);
        return new ResponseEntity<>(suggestion, HttpStatus.OK);
    }

    @PutMapping("/{suggestionId}")
    public ResponseEntity<Suggestion> updateSuggestion(@PathVariable Long suggestionId, @RequestBody SuggestionDto suggestionDto) {
        Suggestion updatedSuggestion = suggestionService.updateSuggestion(suggestionId, suggestionDto);
        return new ResponseEntity<>(updatedSuggestion, HttpStatus.OK);
    }

    @DeleteMapping("/{suggestionId}")
    public ResponseEntity<Void> deleteSuggestion(@PathVariable Long suggestionId) {
        suggestionService.deleteSuggestion(suggestionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Suggestion>> getSuggestionsByUser(@PathVariable Long userId) {
        List<Suggestion> suggestions = suggestionService.getSuggestionsByUser(userId);
        return new ResponseEntity<>(suggestions, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Suggestion>> getSuggestionsByStatus(@PathVariable SuggestionStatus status) {
        List<Suggestion> suggestions = suggestionService.getSuggestionsByStatus(status);
        return new ResponseEntity<>(suggestions, HttpStatus.OK);
    }

}