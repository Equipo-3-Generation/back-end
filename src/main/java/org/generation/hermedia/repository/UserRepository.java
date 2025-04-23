package org.generation.hermedia.repository;

import org.generation.hermedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface UserRepository extends JpaRepository<User,Long> {
        //Métodos para consultar cierta información (JPQL)

        //Se crea un metodo para encontrar por email metodo de tipo abstracto no es necesario crear un cuerpo porque es abstracto

        User findByEmail(String email);
    }


