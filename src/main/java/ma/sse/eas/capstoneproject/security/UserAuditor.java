package ma.sse.eas.capstoneproject.security;

import ma.sse.eas.capstoneproject.persistence.entities.User;
import ma.sse.eas.capstoneproject.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Component
public class UserAuditor implements AuditorAware<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            System.out.println("Null or anonymous authentication");
            return Optional.empty();
        }

        String username;
        if (authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            username = authentication.getPrincipal().toString();
        }

        System.out.println("username: " + username);
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
}
