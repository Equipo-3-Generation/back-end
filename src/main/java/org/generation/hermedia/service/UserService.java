package org.generation.hermedia.service;

import org.generation.hermedia.exception.UserNotFoundException;
import org.generation.hermedia.model.User;
import org.generation.hermedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    // Create user
    public User createUser(User newUser){
         return userRepository.save(newUser);
    }

    // Find user by Email
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    // Fing user by ID
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

    // Delete user
    public void deleteUser(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else{
            throw new UserNotFoundException(id);
        }
    }

    // Update user
    public User updateUser(User user, Long id){
        return userRepository.findById(id)
                .map(userMap -> {
                    userMap.setName(user.getName());
                    userMap.setEmail(user.getEmail());
                    userMap.setTelephoneNumber(user.getTelephoneNumber());
                    userMap.setPassword(user.getPassword());
                    return userRepository.save(userMap);
                })
                .orElseThrow(()-> new UserNotFoundException(id)) ;//llamamos a exception
    }

}