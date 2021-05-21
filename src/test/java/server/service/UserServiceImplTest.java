package server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import server.core.dto.UserDTO;
import server.core.model.User;
import server.core.repository.UserRepository;
import server.core.service.UserService;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User()
                .setUserMail("johndoe@example.com")
                .setCity("Moscow")
                .setName("John")
                .setGuide(true)
                .setDescription("I like discrete math...")
                .setPhoneNumber("123456789");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUser() {
        when(userRepo.findById(user.getUserMail())).thenReturn(Optional.of(user));

        Assertions.assertEquals(userService.getUser(user.getUserMail()),
                new ModelMapper().map(user, UserDTO.class));
    }

    @Test
    public void testAddUser() {
        AtomicBoolean isOk = new AtomicBoolean(false);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                isOk.set(true);
                return null;
            }
        }).when(userRepo).save(any(User.class));

        userService.addUser(new ModelMapper().map(user, UserDTO.class));

        Assertions.assertTrue(isOk.get());
    }
}
