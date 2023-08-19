package cri.vaycompany.linkserve.models;

import cri.vaycompany.linkserve.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private ServiceOffer serviceOffer;

    @OneToOne
    private Rating rating;
    @ManyToOne
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replies;

    private String text;
    private Status status;
}
