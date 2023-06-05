package cri.vaycompany.linkserve.controllers;

import cri.vaycompany.linkserve.models.Complaint;
import cri.vaycompany.linkserve.services.ComplaintService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComplaintControllerTest {

    @InjectMocks
    private ComplaintController complaintController;

    @Mock
    private ComplaintService complaintService;

    private Complaint complaint;

    @BeforeEach
    public void setup() {
        complaint = new Complaint();
        complaint.setId(1L);
        complaint.setDescription("Test complaint");
    }

    @Test
    public void testCreateComplaint() {
        when(complaintService.createComplaint(any(Complaint.class))).thenReturn(complaint);
        Complaint result = complaintController.createComplaint(complaint);
        assertEquals(complaint, result);
    }

    @Test
    public void testGetAllComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        complaints.add(complaint);
        when(complaintService.getAllComplaints()).thenReturn(complaints);
        List<Complaint> results = complaintController.getAllComplaints();
        assertEquals(complaints, results);
    }

    @Test
    public void testGetComplaintById() {
        when(complaintService.getComplaintById(any(Long.class))).thenReturn(Optional.of(complaint));
        ResponseEntity<Complaint> result = complaintController.getComplaintById(1L);
        assertEquals(complaint, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testUpdateComplaint() {
        when(complaintService.getComplaintById(any(Long.class))).thenReturn(Optional.of(complaint));
        when(complaintService.updateComplaint(any(Complaint.class))).thenReturn(complaint);
        ResponseEntity<Complaint> result = complaintController.updateComplaint(1L, complaint);
        assertEquals(complaint, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
