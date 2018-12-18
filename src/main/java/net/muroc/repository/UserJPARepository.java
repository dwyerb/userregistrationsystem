package net.muroc.repository;

import net.muroc.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Brendan on 12/15/2018.
 */

@Repository
public interface UserJPARepository extends JpaRepository<UserDTO, Long>
{
    UserDTO findByName(String name);
}
