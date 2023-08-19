//package cri.vaycompany.linkserve.controllers;
//
//import cri.vaycompany.linkserve.dto.UserPrivateDTO;
//import cri.vaycompany.linkserve.dto.UserPublicDTO;
//import cri.vaycompany.linkserve.services.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class UserControllerTest {
//
//    @InjectMocks
//    UserController userController;
//
//    @Mock
//    UserService userService;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testCreateUser() {
//        UserPublicDTO userDTO = new UserPublicDTO();
//        when(userService.createUser(any(UserPrivateDTO.class))).thenReturn(userDTO);
//        ResponseEntity<UserPublicDTO> result = userController.createUser(new UserPrivateDTO());
//        assertEquals(userDTO, result.getBody());
//        verify(userService, times(1)).createUser(any(UserPrivateDTO.class));
//    }
//
//    @Test
//    public void testGetUserById() {
//        UserPublicDTO userDTO = new UserPublicDTO();
//        when(userService.getUserById(any(Long.class))).thenReturn(Optional.of(userDTO));
//        ResponseEntity<UserPublicDTO> result = userController.getUserById(1L);
//        assertEquals(userDTO, result.getBody());
//        verify(userService, times(1)).getUserById(any(Long.class));
//    }
//
//    @Test
//    public void testUpdateUser() {
//        UserPublicDTO userDTO = new UserPublicDTO();
//        UserPrivateDTO userToUpdate = new UserPrivateDTO();
//        userToUpdate.setId(1L);  // Set the id to match the one in updateUser()
//
//        when(userService.updateUser(any(UserPrivateDTO.class))).thenReturn(userDTO);
//        ResponseEntity<UserPublicDTO> result = userController.updateUser(1L, userToUpdate);
//
//        assertEquals(userDTO, result.getBody());
//        verify(userService, times(1)).updateUser(any(UserPrivateDTO.class));
//    }
//
//
//    @Test
//    public void testDeleteUser() {
//        doNothing().when(userService).deleteUser(any(Long.class));
//        userController.deleteUser(1L);
//        verify(userService, times(1)).deleteUser(any(Long.class));
//    }
//}
