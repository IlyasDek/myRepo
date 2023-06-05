package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.models.User;
import cri.vaycompany.linkserve.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUsersTest() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(new User(), new User()));
        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void getUserByIdTest() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.getUserById(1L);
        assertEquals(1L, foundUser.get().getId());
        verify(userRepository, times(1)).findById(any(Long.class));
    }

    @Test
    public void createUserTest() {
        User user = new User();
        user.setEmail("test@test.com");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User createdUser = userService.createUser(user);
        assertEquals("test@test.com", createdUser.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void updateUserTest() {
        User user = new User();
        user.setEmail("test@test.com");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User updatedUser = userService.updateUser(user);
        assertEquals("test@test.com", updatedUser.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void deleteUserTest() {
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(any(Long.class));
    }
}
