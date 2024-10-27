package ma.sse.eas.capstoneproject.persistence.entities;


import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Authority extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 123456789L;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String authority;

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    protected Authority() {}
}