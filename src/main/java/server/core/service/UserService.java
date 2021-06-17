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
        User user = userRepository.findById(userId).orElseThrow();
        return Mapper.map(user, UserDTO.class).setIsGuide(user.isGuide());
    }

    public void updateUser(UserDTO newUser, String userId) {
        userRepository.findById(userId).map(user -> {
            user.setGuide(newUser.getIsGuide());
            user.setName(newUser.getName());
            user.setPhoneNumber(newUser.getPhoneNumber());
            user.setCity(newUser.getCity());
            user.setDescription(newUser.getDescription());
            user.setAvatarUrl(newUser.getAvatarUrl());
            user.setToken(newUser.getToken());
            return userRepository.save(user);
        }).orElseGet(() -> {
            User user = Mapper.map(newUser, User.class);
            user.setUserMail(userId);
            return userRepository.save(user);
        });
    }

    public String getTokenById(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getToken();
    }

    public void updateTokenById(String userId, String token) {
        userRepository.updateTokenByUserId(userId, token);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public void setGuideStatus(String userId) {
        UserDTO user = getUser(userId);
        user.setIsGuide(true);
        updateUser(user, userId);
    }
}
