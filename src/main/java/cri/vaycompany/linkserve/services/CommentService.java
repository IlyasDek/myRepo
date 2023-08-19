package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.dto.CommentDTO;
import cri.vaycompany.linkserve.dto.ServiceOfferDTO;
import cri.vaycompany.linkserve.dto.UserPublicDTO;
import cri.vaycompany.linkserve.enums.Status;
import cri.vaycompany.linkserve.models.Comment;
import cri.vaycompany.linkserve.models.Rating;
import cri.vaycompany.linkserve.models.ServiceOffer;
import cri.vaycompany.linkserve.models.User;
import cri.vaycompany.linkserve.repositories.CommentRepository;
import cri.vaycompany.linkserve.repositories.RatingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final RatingRepository ratingRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, RatingRepository ratingRepository,
                          ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
       this.ratingRepository = ratingRepository;
        this.modelMapper = modelMapper;
    }

    public CommentDTO createComment(CommentDTO commentDTO) {
        // Проверка на существование рейтинга от данного пользователя для данной услуги
        Optional<Rating> existingRating = ratingRepository.findByUserAndServiceOffer(
                modelMapper.map(commentDTO.getUser(), User.class),
                modelMapper.map(commentDTO.getServiceOffer(), ServiceOffer.class)
        );

        if (!existingRating.isPresent()) {
            // Если рейтинг не существует, выбрасываем исключение
            throw new IllegalStateException("Рейтинг для данного пользователя и услуги не существует");
        }

        boolean commentExists = commentRepository.existsByUserIdAndServiceOfferId(commentDTO.getUser().getId(), commentDTO.getServiceOffer().getId());
        if (commentExists) {
            throw new IllegalStateException("Для данного пользователя и услуги уже существует комментарий");
        }

        Comment comment = modelMapper.map(commentDTO, Comment.class);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentDTO.class);
    }


    public CommentDTO getCommentById(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        return commentOptional.map(comment -> modelMapper.map(comment, CommentDTO.class)).orElse(null);
    }

    public List<CommentDTO> getCommentsByServiceOffer(ServiceOfferDTO serviceOfferDTO) {
        List<Comment> comments = commentRepository.findByServiceOfferId(serviceOfferDTO.getId());
        return comments.stream().map(comment -> modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
    }

    public List<CommentDTO> getCommentsByUser(UserPublicDTO userDTO) {
        List<Comment> comments = commentRepository.findByUserId(userDTO.getId());
        return comments.stream().map(comment -> modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
    }

    public CommentDTO updateComment(CommentDTO commentDTO) {
        Optional<Comment> commentOptional = commentRepository.findById(commentDTO.getId());
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setUser(modelMapper.map(commentDTO.getUser(), User.class));
            comment.setServiceOffer(modelMapper.map(commentDTO.getServiceOffer(), ServiceOffer.class));
            comment.setParentComment(modelMapper.map(commentDTO.getParentComment(), Comment.class));
            comment.setReplies(commentDTO.getReplies().stream()
                    .map(replyDto -> modelMapper.map(replyDto, Comment.class))
                    .collect(Collectors.toList()));
            comment.setText(commentDTO.getText());
            comment.setStatus(commentDTO.getStatus());

            Comment updatedComment = commentRepository.save(comment);
            return modelMapper.map(updatedComment, CommentDTO.class);
        }
        return null;
    }

    public void deleteComment(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setStatus(Status.DELETED);
            commentRepository.save(comment);
        }
    }
}

