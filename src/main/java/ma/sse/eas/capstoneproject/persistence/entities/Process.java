package ma.sse.eas.capstoneproject.persistence.entities;


import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "process")
public class Process  extends BaseEntity implements Serializable{
    @Serial
    private static final long serialVersionUID = 8273648726349827L;

    @NotNull
    private String name;

    @NotNull
    @Column(length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Suggestion> suggestions;

    public Process(String name, String description, Tenant tenant) {
        this.name = name;
        this.description = description;
        this.tenant = tenant;
    }

    protected Process() {}
}
