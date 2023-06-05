package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.models.Rating;
import cri.vaycompany.linkserve.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Optional<Rating> getRatingById(Long id) {
        return ratingRepository.findById(id);
    }
    public List<Rating> getAllRatings(){
        return ratingRepository.findAll();
    }


    public Rating updateRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }
}
