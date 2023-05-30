package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.models.Review;
import cri.vaycompany.linkserve.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review addReview(Review review) {
        // TODO: Add validation
        return reviewRepository.save(review);
    }

    public Review updateReview(Review review) {
        // TODO: Add validation
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        // TODO: Add validation
        reviewRepository.deleteById(id);
    }

    // TODO: Implement other review-related methods.
}