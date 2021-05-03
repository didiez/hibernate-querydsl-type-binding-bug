package es.didiez.hibernatequerydslbug;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author didiez
 */
@DataJpaTest
@ComponentScan(basePackageClasses = Application.class)
@TestMethodOrder(Alphanumeric.class)
public class UserRepositoryTest {
    
    private static final Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);
    
    @Autowired
    private UserRepository userRepository;
    
    // with JPQL
    
    @Test
    public void jpql_findAllActive(){
        log.info("jpql_findAllActive");
        assertEquals(2, userRepository.findAllByActive(true).size());
    }
    
    @Test
    public void jpql_findAllActiveAndAdmin(){
        log.info("jpql_findAllActiveAndAdmin");
        assertEquals(1, userRepository.findAllByActiveAndAdmin(true, true).size());
    }
    
    // with QueryDSL
    
    @Test
    public void querydsl_findAllActive(){
        log.info("querydsl_findAllActive");
        assertEquals(2, Lists.newArrayList(userRepository.findAll(QUser.user.active.isTrue())).size());
    }
    
    @Test  // FAILED
    public void querydsl_findAllActiveAndAdmin(){
        log.info("querydsl_findAllActiveAndAdmin (true, true)");
        assertEquals(1, Lists.newArrayList(userRepository.findAll(QUser.user.active.isTrue().and(QUser.user.admin.isTrue()))).size());
    }
    
    @Test
    public void querydsl_findAllActiveAndNonAdmin(){
        log.info("querydsl_findAllActiveAndNonAdmin (true, false)");
        assertEquals(1, Lists.newArrayList(userRepository.findAll(QUser.user.active.isTrue().and(QUser.user.admin.isFalse()))).size());
    }    
    
 
    // logging separators to make it clearer
    
    @AfterAll
    public static void endSeparator(){
        System.out.println("\n-------------------------------------------------------\n");
    }
    
    @BeforeEach
    public void testSeparator(){
        System.out.println("\n-------------------------------------------------------\n");
    }
    
}
