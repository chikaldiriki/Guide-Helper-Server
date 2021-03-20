package server.core.service;

import org.springframework.stereotype.Service;
import server.core.dto.UserDTO;

@Service
public interface UserService {

    void addUser(UserDTO userDTO);

    UserDTO getUser(String userId);
}
