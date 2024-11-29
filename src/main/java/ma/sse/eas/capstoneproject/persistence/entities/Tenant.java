package ma.sse.eas.capstoneproject.persistence.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "\"tenant\"")
public class Tenant extends BaseEntity {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private boolean admin = true;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Document> documents = new HashSet<>();

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Process> processes = new HashSet<>();

    public Tenant(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Tenant() {}

    public void addUser(User user) {
        users.add(user);
        user.setTenant(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setTenant(null);
    }

}

