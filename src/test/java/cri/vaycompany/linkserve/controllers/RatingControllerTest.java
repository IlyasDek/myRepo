//package cri.vaycompany.linkserve.controllers;
//
//import cri.vaycompany.linkserve.dto.RatingDTO;
//import cri.vaycompany.linkserve.services.RatingService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class RatingControllerTest {
//
//    @InjectMocks
//    RatingController ratingController;
//
//    @Mock
//    RatingService ratingService;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testCreateRating() {
//        RatingDTO rating = new RatingDTO();
//        when(ratingService.createRating(any(RatingDTO.class))).thenReturn(rating);
//        ResponseEntity<RatingDTO> result = ratingController.createRating(new RatingDTO());
//        assertEquals(rating, result.getBody());
//        verify(ratingService, times(1)).createRating(any(RatingDTO.class));
//    }
//
//    @Test
//    public void testGetAllRatings() {
//        RatingDTO rating = new RatingDTO();
//        when(ratingService.getAllRatings()).thenReturn(Collections.singletonList(rating));
//        ResponseEntity<List<RatingDTO>> result = ratingController.getAllRatings();
//        assertEquals(1, result.getBody().size());
//        assertEquals(rating, result.getBody().get(0));
//        verify(ratingService, times(1)).getAllRatings();
//    }
//
//    @Test
//    public void testGetRatingById() {
//        RatingDTO rating = new RatingDTO();
//        when(ratingService.getRatingById(any(Long.class))).thenReturn(Optional.of(rating));
//        ResponseEntity<RatingDTO> result = ratingController.getRatingById(1L);
//        assertEquals(rating, result.getBody());
//        verify(ratingService, times(1)).getRatingById(any(Long.class));
//    }
//
//    @Test
//    public void testUpdateRating() {
//        RatingDTO rating = new RatingDTO();
//        when(ratingService.getRatingById(any(Long.class))).thenReturn(Optional.of(rating));
//        when(ratingService.updateRating(any(RatingDTO.class))).thenReturn(rating);
//        ResponseEntity<RatingDTO> result = ratingController.updateRating(1L, new RatingDTO());
//        assertEquals(rating, result.getBody());
//        verify(ratingService, times(1)).updateRating(any(RatingDTO.class));
//    }
//
//    @Test
//    public void testDeleteRating() {
//        doNothing().when(ratingService).deleteRating(any(Long.class));
//        ratingController.deleteRating(1L);
//        verify(ratingService, times(1)).deleteRating(any(Long.class));
//    }
//}
