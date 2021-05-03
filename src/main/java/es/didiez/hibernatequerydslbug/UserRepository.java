package es.didiez.hibernatequerydslbug;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 *
 * @author didiez
 */
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User>{
    @Query("from User where active = ?1")
    public List<User> findAllByActive(boolean active);
    
    // @Query("select user from User user where user.active = ?1 and user.admin = ?2")
    @Query("from User where active = ?1 and admin = ?2")
    public List<User> findAllByActiveAndAdmin(boolean active, boolean admin);
}
