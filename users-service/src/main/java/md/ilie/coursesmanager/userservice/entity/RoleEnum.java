package md.ilie.coursesmanager.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

//@Entity
//@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public enum RoleEnum implements GrantedAuthority {
    ADMIN(1, "ADMIN"),
    USER(2, "USER"),
    MANAGER(3, "MANAGER");

//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
