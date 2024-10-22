package ma.sse.eas.capstoneproject.persistence.entities;

import com.sun.istack.NotNull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


@Data
@Entity
@Table(name = "\"user\"")
public class User extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Tenant tenant;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String password;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Suggestion> suggestions;

    public User(String username, String firstname, String lastname,
                String password, String email, String phone) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.email = email;
        this.phone = phone;
    }

    protected User() {}
}