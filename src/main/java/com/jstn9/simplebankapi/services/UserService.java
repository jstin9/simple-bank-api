package com.jstn9.simplebankapi.services;

import com.jstn9.simplebankapi.dtos.UserRequestDTO;
import com.jstn9.simplebankapi.dtos.UserResponseDTO;
import com.jstn9.simplebankapi.exceptions.UserNotFoundException;
import com.jstn9.simplebankapi.models.User;
import com.jstn9.simplebankapi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserResponseDTO> getUsers(){
        return userRepository.findAll().stream()
                .map(u -> modelMapper.map(u, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUser(UUID id){
        User user = findUserById(id);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO){
        User user = modelMapper.map(userRequestDTO, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public UserResponseDTO partialUpdateUser(UUID id, UserRequestDTO userRequestDTO){
        User user = findUserById(id);
        if(userRequestDTO.getFirstName() != null){
            user.setFirstName(userRequestDTO.getFirstName());
        }
        if(userRequestDTO.getLastName() != null){
            user.setLastName(userRequestDTO.getLastName());
        }
        user = userRepository.save(user);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public UserResponseDTO updateUser(UUID id, UserRequestDTO userRequestDTO){
        User user = findUserById(id);
        modelMapper.map(userRequestDTO, user);
        user = userRepository.save(user);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public void deleteUser(UUID id){
        User user = findUserById(id);
        userRepository.delete(user);
    }

    private User findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
