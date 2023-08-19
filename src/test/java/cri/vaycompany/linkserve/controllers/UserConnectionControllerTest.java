//package cri.vaycompany.linkserve.controllers;
//
//import cri.vaycompany.linkserve.dto.UserConnectionDTO;
//import cri.vaycompany.linkserve.services.UserConnectionService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class UserConnectionControllerTest {
//
//    @InjectMocks
//    private UserConnectionController userConnectionController;
//
//    @Mock
//    private UserConnectionService userConnectionService;
//
//    private UserConnectionDTO userConnectionDTO;
//
//    @BeforeEach
//    public void setup() {
//        userConnectionController = new UserConnectionController(userConnectionService);
//        userConnectionDTO = new UserConnectionDTO();
//        userConnectionDTO.setId(1L);
//        userConnectionDTO.setConfirmed(false);
//        userConnectionDTO.setDeleted(false);
//    }
//
//    @Test
//    public void testCreateUserConnection() {
//        when(userConnectionService.createUserConnection(any(UserConnectionDTO.class))).thenReturn(userConnectionDTO);
//        ResponseEntity<UserConnectionDTO> result = userConnectionController.createUserConnection(userConnectionDTO);
//        assertEquals(userConnectionDTO, result.getBody());
//    }
//
//    @Test
//    public void testGetUserConnectionById() {
//        when(userConnectionService.getUserConnectionById(any(Long.class))).thenReturn(Optional.of(userConnectionDTO));
//        ResponseEntity<UserConnectionDTO> result = userConnectionController.getUserConnectionById(userConnectionDTO.getId());
//        assertEquals(userConnectionDTO, result.getBody());
//    }
//
//    @Test
//    public void testGetUserConnectionsByUserId() {
//        List<UserConnectionDTO> userConnections = new ArrayList<>();
//        userConnections.add(userConnectionDTO);
//        when(userConnectionService.getUserConnectionById(any(Long.class))).thenReturn(Optional.of(userConnectionDTO));
//        ResponseEntity<UserConnectionDTO> results = userConnectionController.getUserConnectionById(userConnectionDTO.getUserOne().getId());
//        assertEquals(userConnections, results.getBody());
//    }
//
//    @Test
//    public void testConfirmUserConnection() {
//        doNothing().when(userConnectionService).confirmUserConnection(any(Long.class));
//        ResponseEntity<UserConnectionDTO> result = userConnectionController.confirmUserConnection(userConnectionDTO.getId());
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        verify(userConnectionService, times(1)).confirmUserConnection(userConnectionDTO.getId());
//    }
//
//    @Test
//    public void testDeclineUserConnection() {
//        doNothing().when(userConnectionService).declineUserConnection(any(Long.class));
//        ResponseEntity<Void> result = userConnectionController.declineUserConnection(userConnectionDTO.getId());
//        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
//        verify(userConnectionService, times(1)).declineUserConnection(userConnectionDTO.getId());
//    }
//}
