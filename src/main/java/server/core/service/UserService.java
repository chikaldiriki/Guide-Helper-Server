package server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.core.dto.UserDTO;
import server.core.model.User;
import server.core.repository.UserRepository;
import server.mapper.Mapper;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(UserDTO userDTO) {
        userRepository.save(Mapper.map(userDTO, User.class));
    }

    public UserDTO getUser(String userId) {
        return Mapper.map(userRepository.findById(userId).get(), UserDTO.class);
    }

    public void updateUser(UserDTO newUser, String userId) {
        userRepository.findById(userId).map(user -> {
            user.setGuide(newUser.isGuide());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPhoneNumber(newUser.getPhoneNumber());
            user.setCity(newUser.getCity());
            user.setDescription(newUser.getDescription());
            return userRepository.save(user);
        }).orElseGet(() ->  {
            User user = Mapper.map(newUser, User.class);
            user.setUserMail(userId);
            return userRepository.save(user);
        });
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public void setGuideStatus(String userId) {
        UserDTO user = getUser(userId);
        user.setGuide(true);
        updateUser(user, userId);
    }
}
