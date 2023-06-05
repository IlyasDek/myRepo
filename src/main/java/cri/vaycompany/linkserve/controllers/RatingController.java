package cri.vaycompany.linkserve.controllers;

import cri.vaycompany.linkserve.models.Rating;
import cri.vaycompany.linkserve.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public Rating createRating(@RequestBody Rating rating) {
        return ratingService.createRating(rating);
    }

    @GetMapping
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long id) {
        return ratingService.getRatingById(id)
                .map(rating -> ResponseEntity.ok(rating))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long id, @RequestBody Rating rating) {
        if (!id.equals(rating.getId())) {
            throw new IllegalArgumentException("Id in the path and in the request body must be the same");
        }
        return ratingService.getRatingById(id)
                .map(existingRating -> {
                    existingRating.setScore(rating.getScore());
                    return ResponseEntity.ok(ratingService.updateRating(existingRating));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
    }
}
