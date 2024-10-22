package ma.sse.eas.capstoneproject.security;

import ma.sse.eas.capstoneproject.persistence.entities.User;
import ma.sse.eas.capstoneproject.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class UserAuditor {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("Null authentication");
            return null;
        }

        String username = (String)authentication.getPrincipal();
        System.out.println("username: " + username);
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
}
