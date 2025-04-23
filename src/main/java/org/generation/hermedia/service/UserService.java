package org.generation.hermedia.service;

import org.generation.hermedia.exception.UserNotFoundException;
import org.generation.hermedia.model.User;
import org.generation.hermedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


//Este paso es después de userRepository
@Service
public class UserService {
    //--Crear variable de tipo UserRepository se encapsula con private
    private final UserRepository userRepository;


    @Autowired

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    //Recuperar los users con metodoList recibira la clase del model no lleva parametros porque queremos todos los usuarios, osea todas las instancias de la entidad

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    //Metodo para crear un nuevo usuario debe ser el mismo tipo del model si se le debe definir parametro para que se cree un objeto en return le pedimos a userRepository el metodo save para eso le pasamos el parametro y lo que hara es guardar una entidad en ese parametro (por el momento no estamos agregando validaciones)

     public User createUser(User newUser){
         return userRepository.save(newUser);
     }


    //Este metodo se creo a partir del nuevo controller y es un metodo para recuperar un usuario por email

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }


    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

    //Metodo para eliminar un usuario

    public void deleteUser(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else{
            throw new UserNotFoundException(id);
        }

    }

    public User updateUser(User user, Long id){
        return userRepository.findById(id)
                .map(userMap -> {
                    userMap.setName(user.getName());
                    userMap.setEmail(user.getEmail());
                    userMap.setPassword(user.getPassword());
                    return userRepository.save(userMap);
                })
                .orElseThrow(()-> new UserNotFoundException(id)) ;//llamamos a exception
    }

}