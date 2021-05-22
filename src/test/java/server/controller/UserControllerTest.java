package server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import server.core.dto.UserDTO;
import server.core.model.User;
import server.core.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    private User user;

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void init() {
        user = new User()
                .setUserMail("johndoe@example.com")
                .setCity("Moscow")
                .setName("John")
                .setGuide(true)
                .setDescription("I like discrete math...")
                .setPhoneNumber("123456789");
    }

    @Test
    public void getUserTest() throws Exception {
        Mockito.when(userService.getUser("johndoe@example.com"))
                .thenReturn(new ModelMapper().map(user, UserDTO.class));

        mockMvc.perform(get("/users/johndoe@example.com").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(user.getName()));
    }

    @Test
    public void addUserTest() throws Exception {
        doNothing().when(userService).addUser(any(UserDTO.class));

        mockMvc.perform(post("/users")
                .content(asJsonString(new ModelMapper().map(user, UserDTO.class)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}