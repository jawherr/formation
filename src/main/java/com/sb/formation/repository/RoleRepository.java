package com.sb.formation.repository;

import com.sb.formation.entities.ERole;
import com.sb.formation.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin("*")
public interface RoleRepository  extends JpaRepository<Role, Long> {
    Optional<Role> findByNom(ERole nom);
    Boolean existsByNom(ERole nom);

}
