package es.didiez.hibernatequerydslbug;

import com.sun.istack.NotNull;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.Type;


/**
 *
 * @author didiez
 */
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    Long id;
    String name;
    
    @Basic(optional = false)
    @NotNull
    @Type(type="A_I")
    @Column(name = "active", nullable = false, length = 1)
    private Boolean active = true;
    
    private Boolean admin;

    public User() {}

    public User(String name, Boolean active, Boolean admin) {
        this.name = name;
        this.active = active;
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", active=" + active + ", admin=" + admin + '}';
    }
    
}
