package server.service;

import org.springframework.stereotype.Service;
import server.dto.UserDTO;

@Service
public interface UserService {

    void addUser(UserDTO userDTO);

    UserDTO getUser(Long userId);
}
