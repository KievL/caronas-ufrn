package br.ufrn.umbrella.caronasufrn.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.umbrella.caronasufrn.dtos.UserDTO;
import br.ufrn.umbrella.caronasufrn.entities.User;
import br.ufrn.umbrella.caronasufrn.models.classes.ApiResponse;
import br.ufrn.umbrella.caronasufrn.models.enums.UserRoles;
import br.ufrn.umbrella.caronasufrn.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse<List<User>> findAll(){
        List<User> users = new ArrayList<>();

        users = userRepository.findAll();

        ApiResponse<List<User>> response = new ApiResponse<>();
        
        return response.of(HttpStatus.ACCEPTED, "Users fetched.", users);
    }

    @Transactional
    public ApiResponse<User> save(UserDTO userDTO){
        ApiResponse<User> response = new ApiResponse<>();

        User user = new User();

        BeanUtils.copyProperties(userDTO, user);

        user.setRole(UserRoles.USER);
        user.setRidesProvided(0);

        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        
        User savedUser = userRepository.save(user);

        return response.of(HttpStatus.CREATED, "User was created.", savedUser);
    }
}
