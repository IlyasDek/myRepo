package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.dto.RatingDTO;
import cri.vaycompany.linkserve.models.Rating;
import cri.vaycompany.linkserve.models.ServiceOffer;
import cri.vaycompany.linkserve.models.User;
import cri.vaycompany.linkserve.repositories.RatingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RatingService(RatingRepository ratingRepository, ModelMapper modelMapper) {
        this.ratingRepository = ratingRepository;
        this.modelMapper = modelMapper;
    }

    public RatingDTO createRating(RatingDTO ratingDTO) {
        // Проверка на существование рейтинга от данного пользователя для данной услуги
        Optional<Rating> existingRating = ratingRepository.findByUserAndServiceOffer(
                modelMapper.map(ratingDTO.getUser(), User.class),
                modelMapper.map(ratingDTO.getServiceOffer(), ServiceOffer.class)
        );

        Rating rating;
        if (existingRating.isPresent()) {
            // Если рейтинг уже существует, обновляем его
            rating = existingRating.get();
            rating.setRating(ratingDTO.getRating());
        } else {
            // Если рейтинга не существует, создаем новый
            rating = modelMapper.map(ratingDTO, Rating.class);
        }

        Rating savedRating = ratingRepository.save(rating);
        return modelMapper.map(savedRating, RatingDTO.class);
    }


    public Optional<RatingDTO> getRatingById(Long id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        return rating.map(ratingEntity -> modelMapper.map(ratingEntity, RatingDTO.class));
    }

    public List<RatingDTO> getAllRatings() {
        return ratingRepository.findAll()
                .stream()
                .map(rating -> modelMapper.map(rating, RatingDTO.class))
                .collect(Collectors.toList());
    }

    public RatingDTO updateRating(RatingDTO ratingDTO) {
        Rating rating = modelMapper.map(ratingDTO, Rating.class);
        Rating updatedRating = ratingRepository.save(rating);
        return modelMapper.map(updatedRating, RatingDTO.class);
    }

    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }
}
