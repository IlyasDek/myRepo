//package cri.vaycompany.linkserve.controllers;
//
//import cri.vaycompany.linkserve.dto.ComplaintDTO;
//import cri.vaycompany.linkserve.services.ComplaintService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ComplaintControllerTest {
//
//    @InjectMocks
//    private ComplaintController complaintController;
//
//    @Mock
//    private ComplaintService complaintService;
//
//    private ModelMapper modelMapper = new ModelMapper();
//    private ComplaintDTO complaintDTO;
//
//    @BeforeEach
//    public void setup() {
//        complaintController = new ComplaintController(complaintService, modelMapper);
//        complaintDTO = new ComplaintDTO();
//        complaintDTO.setId(1L);
//        complaintDTO.setDescription("Test complaint");
//    }
//
//    @Test
//    public void testCreateComplaint() {
//        when(complaintService.createComplaint(any(ComplaintDTO.class))).thenReturn(complaintDTO);
//        ComplaintDTO result = complaintController.createComplaint(complaintDTO);
//        assertEquals(complaintDTO, result);
//    }
//
//    @Test
//    public void testGetAllComplaints() {
//        List<ComplaintDTO> complaints = new ArrayList<>();
//        complaints.add(complaintDTO);
//        when(complaintService.getAllComplaints()).thenReturn(complaints);
//        List<ComplaintDTO> results = complaintController.getAllComplaints();
//        assertEquals(complaints, results);
//    }
//
//    @Test
//    public void testGetComplaintById() {
//        when(complaintService.getComplaintById(any(Long.class))).thenReturn(Optional.of(complaintDTO));
//        ResponseEntity<ComplaintDTO> result = complaintController.getComplaintById(1L);
//        assertEquals(complaintDTO, result.getBody());
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//    }
//
//    @Test
//    public void testUpdateComplaint() {
//        when(complaintService.updateComplaint(any(ComplaintDTO.class))).thenReturn(complaintDTO);
//        ResponseEntity<ComplaintDTO> result = complaintController.updateComplaint(1L, complaintDTO);
//        assertEquals(complaintDTO, result.getBody());
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//    }
//}
