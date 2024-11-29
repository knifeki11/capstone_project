package ma.sse.eas.capstoneproject.persistence.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "document")
public class Document extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 3298729837498234L;

    @NotNull
    private String title;

    @NotNull
    private String type;

    @NotNull
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @NotNull
    private LocalDateTime uploadDate;

    public Document(String title, String type, String filePath, Tenant tenant) {
        this.title = title;
        this.type = type;
        this.filePath = filePath;
        this.tenant = tenant;
        this.uploadDate = LocalDateTime.now();
    }

    protected Document() {}
}


