//package cri.vaycompany.linkserve.services;
//
//import cri.vaycompany.linkserve.dto.ComplaintDTO;
//import cri.vaycompany.linkserve.dto.ServiceOfferDTO;
//import cri.vaycompany.linkserve.dto.UserPublicDTO;
//import cri.vaycompany.linkserve.models.Complaint;
//import cri.vaycompany.linkserve.repositories.ComplaintRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class ComplaintServiceTest {
//
//    @Mock
//    private ComplaintRepository complaintRepository;
//
//    @Mock
//    private UserPublicDTO complainant;
//
//    @Mock
//    private UserPublicDTO target;
//
//    @Mock
//    private ServiceOfferDTO serviceOffer;
//
//    private ComplaintService complaintService;
//    private ComplaintDTO complaint;
//    private ModelMapper modelMapper;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//         modelMapper = new ModelMapper();
//        complaintService = new ComplaintService(complaintRepository, modelMapper);
//
//        complaint = new ComplaintDTO();
//        complaint.setComplainant(complainant);
//        complaint.setTarget(target);
//        complaint.setServiceOffer(serviceOffer);
//        complaint.setComplaintText("Test Complaint");
//        complaint.setDescription("Test Description");
//    }
//
//    @Test
//    public void testCreateComplaint() {
//        when(complaintRepository.save(any(Complaint.class))).thenAnswer(i -> i.getArguments()[0]);
//        ComplaintDTO createdComplaint = complaintService.createComplaint(complaint);
//        assertNotNull(createdComplaint);
//    }
//
//    @Test
//    public void testGetComplaintById() {
//        when(complaintRepository.findById(anyLong()))
//                .thenAnswer(i -> Optional.of(modelMapper.map(complaint, Complaint.class)));
//        Optional<ComplaintDTO> foundComplaint = complaintService.getComplaintById(1L);
//
//        assertTrue(foundComplaint.isPresent());
//        assertEquals(complaint.getComplaintText(), foundComplaint.get().getComplaintText());
//        assertEquals(complaint.getDescription(), foundComplaint.get().getDescription());
//    }
//
//    @Test
//    public void testUpdateComplaint() {
//        when(complaintRepository.save(any(Complaint.class))).thenAnswer(i -> i.getArguments()[0]);
//        ComplaintDTO updatedComplaint = complaintService.updateComplaint(complaint);
//        assertNotNull(updatedComplaint);
//    }
//
//    @Test
//    public void testDeleteComplaint() {
//        doNothing().when(complaintRepository).deleteById(anyLong());
//        complaintService.deleteComplaint(1L);
//        verify(complaintRepository, times(1)).deleteById(1L);
//    }
//}
