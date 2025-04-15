package org.generation.hermedia.repository;

import org.generation.hermedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
