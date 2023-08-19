//package cri.vaycompany.linkserve.services;
//
//import cri.vaycompany.linkserve.dto.UserPrivateDTO;
//import cri.vaycompany.linkserve.dto.UserPublicDTO;
//import cri.vaycompany.linkserve.models.User;
//import cri.vaycompany.linkserve.repositories.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.modelmapper.ModelMapper;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class UserServiceTest {
//
//    @InjectMocks
//    UserService userService;
//
//    @Mock
//    UserRepository userRepository;
//
//    @Mock
//    ModelMapper modelMapper;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testGetAllUsers() {
//        User user1 = new User();
//        User user2 = new User();
//
//        UserPublicDTO userPublicDTO1 = new UserPublicDTO();
//        UserPublicDTO userPublicDTO2 = new UserPublicDTO();
//
//        when(modelMapper.map(user1, UserPublicDTO.class)).thenReturn(userPublicDTO1);
//        when(modelMapper.map(user2, UserPublicDTO.class)).thenReturn(userPublicDTO2);
//
//        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
//
//        List<UserPublicDTO> users = userService.getAllUsers();
//
//        assertEquals(2, users.size());
//        verify(userRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testGetUserById() {
//        User user = new User();
//        user.setId(1L);
//
//        UserPublicDTO userPublicDTO = new UserPublicDTO();
//        userPublicDTO.setId(1L);
//
//        when(modelMapper.map(user, UserPublicDTO.class)).thenReturn(userPublicDTO);
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//
//        Optional<UserPublicDTO> foundUser = userService.getUserById(1L);
//
//        assertEquals(1L, foundUser.get().getId());
//        verify(userRepository, times(1)).findById(any(Long.class));
//    }
//
//    @Test
//    public void testCreateUser() {
//        User user = new User();
//        UserPrivateDTO userPrivateDTO = new UserPrivateDTO();
//        userPrivateDTO.setEmail("test@test.com");
//
//        UserPublicDTO userPublicDTO = new UserPublicDTO();
//        userPublicDTO.setEmail("test@test.com");
//
//        when(modelMapper.map(userPrivateDTO, User.class)).thenReturn(user);
//        when(modelMapper.map(user, UserPublicDTO.class)).thenReturn(userPublicDTO);
//
//        when(userRepository.save(any(User.class))).thenReturn(user);
//
//        UserPublicDTO createdUser = userService.createUser(userPrivateDTO);
//
//        assertEquals("test@test.com", createdUser.getEmail());
//        verify(userRepository, times(1)).save(any(User.class));
//    }
//
//    @Test
//    public void testUpdateUser() {
//        User user = new User();
//        UserPrivateDTO userPrivateDTO = new UserPrivateDTO();
//        userPrivateDTO.setEmail("test@test.com");
//
//        UserPublicDTO userPublicDTO = new UserPublicDTO();
//        userPublicDTO.setEmail("test@test.com");
//
//        when(modelMapper.map(userPrivateDTO, User.class)).thenReturn(user);
//        when(modelMapper.map(user, UserPublicDTO.class)).thenReturn(userPublicDTO);
//
//        when(userRepository.save(any(User.class))).thenReturn(user);
//
//        UserPublicDTO updatedUser = userService.updateUser(userPrivateDTO);
//
//        assertEquals("test@test.com", updatedUser.getEmail());
//        verify(userRepository, times(1)).save(any(User.class));
//    }
//
//    @Test
//    public void testDeleteUser() {
//        doNothing().when(userRepository).deleteById(1L);
//        userService.deleteUser(1L);
//        verify(userRepository, times(1)).deleteById(any(Long.class));
//    }
//}
