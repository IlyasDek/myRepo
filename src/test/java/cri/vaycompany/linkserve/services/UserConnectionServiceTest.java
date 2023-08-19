//package cri.vaycompany.linkserve.services;
//
//import cri.vaycompany.linkserve.models.User;
//import cri.vaycompany.linkserve.models.UserConnection;
//import cri.vaycompany.linkserve.repositories.UserConnectionRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import cri.vaycompany.linkserve.dto.UserConnectionDTO;
//import cri.vaycompany.linkserve.entity.User;
//import cri.vaycompany.linkserve.entity.UserConnection;
//import cri.vaycompany.linkserve.exception.ConnectionNotFoundException;
//import cri.vaycompany.linkserve.repository.UserConnectionRepository;
//import cri.vaycompany.linkserve.service.UserConnectionService;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class UserConnectionServiceTest {
//    @Mock
//    UserConnectionRepository userConnectionRepository;
//    ModelMapper modelMapper = new ModelMapper();
//
//    @InjectMocks
//    UserConnectionService userConnectionService;
//
//    UserConnection userConnection;
//    UserConnectionDTO userConnectionDTO;
//    User userOne;
//    User userTwo;
//    List<UserConnection> userConnections = new ArrayList<>();
//
//    @BeforeEach
//    void setUp() {
//        userOne = User.builder().id(1L).build();
//        userTwo = User.builder().id(2L).build();
//        userConnection = UserConnection.builder().id(1L).userOne(userOne).userTwo(userTwo).isConfirmed(false).isDeleted(false).build();
//        userConnectionDTO = modelMapper.map(userConnection, UserConnectionDTO.class);
//        userConnections.add(userConnection);
//    }
//
//    @Test
//    void createUserConnection() {
//        when(userConnectionRepository.save(any(UserConnection.class))).thenReturn(userConnection);
//        UserConnectionDTO result = userConnectionService.createUserConnection(userConnectionDTO);
//        assertEquals(userConnectionDTO, result);
//    }
//
//    @Test
//    void confirmUserConnection() {
//        when(userConnectionRepository.findById(any(Long.class))).thenReturn(Optional.of(userConnection));
//        when(userConnectionRepository.save(any(UserConnection.class))).thenReturn(userConnection);
//        UserConnectionDTO result = userConnectionService.confirmUserConnection(userConnectionDTO.getId());
//        assertEquals(userConnectionDTO, result);
//        assertTrue(result.getIsConfirmed());
//    }
//
//    @Test
//    void declineUserConnection() {
//        when(userConnectionRepository.findById(any(Long.class))).thenReturn(Optional.of(userConnection));
//        doNothing().when(userConnectionRepository).save(any(UserConnection.class));
//        userConnectionService.declineUserConnection(userConnectionDTO.getId());
//        verify(userConnectionRepository, times(1)).save(userConnection);
//        assertTrue(userConnection.isDeleted());
//
//    }
//
//    @Test
//    void getUserConnectionById() {
//        when(userConnectionRepository.findById(any(Long.class))).thenReturn(Optional.of(userConnection));
//        Optional<UserConnectionDTO> result = userConnectionService.getUserConnectionById(userConnectionDTO.getId());
//        assertTrue(result.isPresent());
//        assertEquals(userConnectionDTO, result.get());
//    }
//
//    @Test
//    void getAllUserConnections() {
//        when(userConnectionRepository.findAll()).thenReturn(userConnections);
//        List<UserConnectionDTO> result = userConnectionService.getAllUserConnections();
//        assertEquals(1, result.size());
//        assertEquals(userConnectionDTO, result.get(0));
//    }
//
//    @Test
//    void getUserConnections() {
//        when(userConnectionRepository.findByUserOneIdOrUserTwoId(any(Long.class), any(Long.class))).thenReturn(userConnections);
//        List<UserConnectionDTO> result = userConnectionService.getUserConnections(userConnectionDTO.getUserOne().getId());
//        assertEquals(1, result.size());
//        assertEquals(userConnectionDTO, result.get(0));
//    }
//
//    @Test
//    void deleteUserConnection() {
//        doNothing().when(userConnectionRepository).deleteById(any(Long.class));
//        userConnectionService.deleteUserConnection(userConnectionDTO.getId());
//        verify(userConnectionRepository, times(1)).deleteById(userConnectionDTO.getId());
//    }
//
//}
//
