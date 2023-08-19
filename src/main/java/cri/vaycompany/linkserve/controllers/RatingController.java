package cri.vaycompany.linkserve.controllers;

import cri.vaycompany.linkserve.dto.RatingDTO;
import cri.vaycompany.linkserve.services.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;
    private final ModelMapper modelMapper;

    @Autowired
    public RatingController(RatingService ratingService, ModelMapper modelMapper) {
        this.ratingService = ratingService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<RatingDTO> createRating(@RequestBody RatingDTO ratingDTO) {
        RatingDTO createdRatingDTO = ratingService.createRating(ratingDTO);
        return ResponseEntity.ok(createdRatingDTO);
    }

    @GetMapping
    public ResponseEntity<List<RatingDTO>> getAllRatings() {
        List<RatingDTO> ratings = ratingService.getAllRatings();
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingDTO> getRatingById(@PathVariable Long id) {
        return ratingService.getRatingById(id)
                .map(ratingDTO -> ResponseEntity.ok(ratingDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingDTO> updateRating(@PathVariable Long id, @RequestBody RatingDTO ratingDTO) {
        if (!id.equals(ratingDTO.getId())) {
            throw new IllegalArgumentException("Id in the path and in the request body must be the same");
        }
        RatingDTO updatedRatingDTO = ratingService.updateRating(ratingDTO);
        if (updatedRatingDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedRatingDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.ok().build();
    }
}
