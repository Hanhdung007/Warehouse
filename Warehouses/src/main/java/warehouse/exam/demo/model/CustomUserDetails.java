package warehouse.exam.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails extends User implements UserDetails {
    private final String name;

    public CustomUserDetails(String email, String password, Collection<? extends GrantedAuthority> authorities, String name) {
        super(email, password, authorities);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
