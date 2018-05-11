package pl.sdacademy.sardatabase;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import java.util.Collection;

@Entity
public class Reader extends User {
    //......
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }
}
