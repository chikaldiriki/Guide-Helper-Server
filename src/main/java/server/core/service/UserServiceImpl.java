package server.core.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.core.dto.UserDTO;
import server.core.model.User;
import server.core.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserDTO userDTO) {
        userRepository.save(new ModelMapper().map(userDTO, User.class));
    }

    @Override
    public UserDTO getUser(String userId) {
        return new ModelMapper().map(userRepository.findById(userId).get(), UserDTO.class);
    }
}
