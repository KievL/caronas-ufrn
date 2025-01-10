package br.ufrn.umbrella.caronasufrn.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    private final FilebaseService filebaseService;

    @Value("${filebase.s3.image-uri}")
	private String imageUri;

    public ApiResponse<List<User>> findAll(){
        List<User> users = new ArrayList<>();

        users = userRepository.findAll();

        ApiResponse<List<User>> response = new ApiResponse<>();
        
        return response.of(HttpStatus.ACCEPTED, "Users fetched.", users);
    }

    public ApiResponse<User> findMyUser(String userEmail){
        ApiResponse<User> response = new ApiResponse<>();

        Optional<User> user = userRepository.findByEmail(userEmail);
        
        user.ifPresentOrElse(
            value -> {
                response.of(HttpStatus.ACCEPTED, "User fetched.", value);
            },
            () -> {
                response.of(HttpStatus.NOT_FOUND, "User not found.", null);
            }
        );

        return response;
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

    @Transactional
    public ApiResponse<User> patchUserImage(String userEmail, MultipartFile userFile){
        ApiResponse<User> response = new ApiResponse<>();

        Optional<User> user = userRepository.findByEmail(userEmail);

        user.ifPresentOrElse(
            value -> {
                if(userFile.isEmpty()) {
                    filebaseService.removeImage("caronas-users", value.getId());
                    
                    value.setImageUrl(null);
                    userRepository.save(value);
                    
                    response.of(HttpStatus.ACCEPTED, "Image of "+value.getId()+" was removed.", value);
                }else {
                    try {
                        String cid = filebaseService.uploadImage("caronas-users", value.getId(), userFile);
                        
                        value.setImageUrl(imageUri+cid);
                        userRepository.save(value);
                        
                        response.of(HttpStatus.ACCEPTED, "Image of "+value.getId()+" was updated.", value);
                        
                    }catch(Exception e) {
                        response.of(HttpStatus.INTERNAL_SERVER_ERROR, "Could not update image: "+e.getMessage(), null);
                    }						
                }
            },
            () -> {
                response.of(HttpStatus.NOT_FOUND, "User with email "+userEmail+" was not found.", null);
            });
          return response;
    }
}
