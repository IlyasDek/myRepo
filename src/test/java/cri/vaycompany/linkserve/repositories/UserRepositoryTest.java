//package cri.vaycompany.linkserve.repositories;
//
//import cri.vaycompany.linkserve.models.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@DataJpaTest
//public class UserRepositoryTest {
//
//    @Mock
//    UserRepository userRepository;
//
//    @Mock
//    User user;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void findByEmailTest() {
//        when(userRepository.findByEmail(any(String.class))).thenReturn(user);
//        User foundUser = userRepository.findByEmail("test@test.com");
//        assertEquals(user, foundUser);
//        verify(userRepository, times(1)).findByEmail(any(String.class));
//    }
//
//    @Test
//    public void findByPhoneNumberTest() {
//        when(userRepository.findByPhoneNumber(any(String.class))).thenReturn(user);
//        User foundUser = userRepository.findByPhoneNumber("1234567890");
//        assertEquals(user, foundUser);
//        verify(userRepository, times(1)).findByPhoneNumber(any(String.class));
//    }
//}
