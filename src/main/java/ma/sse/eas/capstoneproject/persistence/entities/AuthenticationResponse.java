package ma.sse.eas.capstoneproject.persistence.entities;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
public class AuthenticationResponse {
    private String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }


}
