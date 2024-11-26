package ma.sse.eas.capstoneproject.services;

import ma.sse.eas.capstoneproject.persistence.entities.Tenant;
import ma.sse.eas.capstoneproject.persistence.entities.Authority;
import ma.sse.eas.capstoneproject.persistence.entities.User;
import ma.sse.eas.capstoneproject.persistence.repositories.AuthorityRepository;
import ma.sse.eas.capstoneproject.persistence.repositories.TenantRepository;
import ma.sse.eas.capstoneproject.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    TenantRepository tenantRepository;

    public boolean createTenantAdmin(String username, String password, String firstName, String lastName,
                                     String email, String phone, String tenantName, String description) {
        if (tenantRepository.findByName(tenantName).isPresent()) {
            throw new IllegalArgumentException("Tenant name already exists");
        }

        Tenant tenant = new Tenant(tenantName, description);
        tenantRepository.save(tenant);

        String[] authorities = { "ROLE_TENANT_ADMIN" };
        create(username, password, firstName, lastName, email, phone, tenant, authorities);
        return true;
    }

    public void createUser(String username, String password, String firstName, String lastName, String email,
                           String phone, Long tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Tenant not found"));

        String[] authorities = { "ROLE_USER" };
        create(username, password, firstName, lastName, email, phone, tenant, authorities);
    }

    public void createReviewer(String username, String password, String firstName, String lastName, String email,
                               String phone, Long tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Tenant not found"));

        String[] authorities = { "ROLE_USER", "ROLE_REVIEWER" };
        create(username, password, firstName, lastName, email, phone, tenant, authorities);
    }

    public void createUpperManager(String username, String password, String firstName, String lastName, String email,
                                   String phone, Long tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Tenant not found"));

        String[] authorities = { "ROLE_USER", "ROLE_REVIEWER", "ROLE_UPPER_MANAGER" };
        create(username, password, firstName, lastName, email, phone, tenant, authorities);
    }


    private void create(String username, String password, String firstName, String lastName, String email, String phone,
                        Tenant tenant, String[] authorities) {
        User user = new User(username, firstName, lastName, new BCryptPasswordEncoder().encode(password), email, phone);
        user.setTenant(tenant);
        userRepository.save(user);
        for (String auth : authorities) {
            Authority authority = new Authority(username, auth);
            authorityRepository.save(authority);
        }
    }

    public void update(long userId, String password, String newPassword, String firstName, String lastName,
                       String email, String phone) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (password != null && (newPassword == null
                || !(new BCryptPasswordEncoder().matches(password, user.getPassword())))) {
            throw new IllegalArgumentException("Invalid password");
        }

        if (newPassword != null)
            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        if (firstName != null)
            user.setFirstname(firstName);
        if (lastName != null)
            user.setLastname(lastName);
        if (email != null)
            user.setEmail(email);
        if (phone != null)
            user.setPhone(phone);

        userRepository.save(user);
    }

    public List<User> getUsersByTenant(Long tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Tenant not found"));
        return userRepository.findByTenant(tenant);
    }
}