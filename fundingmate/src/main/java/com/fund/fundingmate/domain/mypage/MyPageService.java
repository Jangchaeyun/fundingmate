package com.fund.fundingmate.domain.mypage;

import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyPageService {

    @Autowired
    private UserRepository userRepository;
    public UserDTO getBasicUserData(String id) {
        // Assuming you have a method in the UserRepository to fetch the user data
        Optional<User> user = userRepository.findByUserid(id); // Replace userId with the actual user ID you want to fetch

        // Convert the User entity to a UserDto (Data Transfer Object) for sending to the frontend
        return convertToUserDto(user);
    }

    private UserDTO convertToUserDto(Optional<User> userOptional) {
        if (userOptional.isPresent()) {
            User user = userOptional.get(); // Extract the User object from the Optional
            UserDTO userDto = new UserDTO();
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setTel(user.getTel());
            userDto.setPassword(user.getPassword());
            // Set other user properties as needed
            return userDto;
        }
        return null;
    }

    public UserDTO updateEmail(String userId, String email) {
        Optional<User> optionalUser = userRepository.findByUserid(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(email);
            userRepository.save(user);
            return convertToUserDto(Optional.of(user));
        }

        return null;
    }

    public UserDTO updateTel(String userId, String tel) {
        Optional<User> optionalUser = userRepository.findByUserid(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setTel(tel);
            userRepository.save(user);
            return convertToUserDto(Optional.of(user));
        }

        return null;
    }

    public UserDTO updatePassword(String userId, String password) {
        Optional<User> optionalUser = userRepository.findByUserid(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(password);
            userRepository.save(user);
            return convertToUserDto(Optional.of(user));
        }

        return null;
    }
}
