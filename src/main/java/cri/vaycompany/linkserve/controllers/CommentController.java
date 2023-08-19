package cri.vaycompany.linkserve.controllers;

import cri.vaycompany.linkserve.dto.CommentDTO;
import cri.vaycompany.linkserve.dto.ServiceOfferDTO;
import cri.vaycompany.linkserve.dto.UserPublicDTO;
import cri.vaycompany.linkserve.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO createdCommentDTO = commentService.createComment(commentDTO);
        return ResponseEntity.ok(createdCommentDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id) {
        CommentDTO commentDTO = commentService.getCommentById(id);
        if (commentDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(commentDTO);
        }
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByServiceOffer(@PathVariable Long serviceId) {
        ServiceOfferDTO serviceOfferDTO = new ServiceOfferDTO();
        serviceOfferDTO.setId(serviceId);
        List<CommentDTO> comments = commentService.getCommentsByServiceOffer(serviceOfferDTO);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByUser(@PathVariable Long userId) {
        UserPublicDTO userDTO = new UserPublicDTO();
        userDTO.setId(userId);
        List<CommentDTO> comments = commentService.getCommentsByUser(userDTO);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        if (!id.equals(commentDTO.getId())) {
            throw new IllegalArgumentException("Id in the path and in the request body must be the same");
        }
        CommentDTO updatedCommentDTO = commentService.updateComment(commentDTO);
        if (updatedCommentDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedCommentDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}

