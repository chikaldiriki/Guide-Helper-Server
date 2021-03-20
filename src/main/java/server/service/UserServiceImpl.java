package server.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import server.dto.UserDTO;
import server.repository.UserRepository;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserDTO userDTO) {
    }

    @Override
    public UserDTO getUser(String userId) {
        return new ModelMapper().map(userRepository.findById(userId).get(), UserDTO.class);
    }
}
