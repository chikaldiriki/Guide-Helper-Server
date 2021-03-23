package server.service;

import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import server.core.dto.UserDTO;
import server.core.service.UserService;
import server.core.service.UserServiceImpl;
import server.core.model.User;
import server.core.repository.UserRepository;

public class UserServiceImplTest {
    @Mock
    private UserRepository userRepo;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        userService = new UserServiceImpl(userRepo);
    }

    @Test
    public void testGivenUserServiceWhenLoadingUserByUsernameThenReturnCorrectUser() {
        User user = new User()
                .setUserMail("johndoe@example.com")
                .setCity("Moscow")
                .setFirstName("John")
                .setLastName("Doe")
                .setGuide(true)
                .setDescription("I like discrete math...")
                .setPhoneNumber("123456789");

        userRepo.save(user);

        given(userRepo.findById("john@example.com")).willReturn(Optional.of(user));

        Assertions.assertEquals(userService.getUser("john@example.com"),
                new ModelMapper().map(user, UserDTO.class));
    }
}
