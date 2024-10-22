package ma.sse.eas.capstoneproject.persistence.dtos;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserDto  implements Serializable {

    @Serial
    private static final long serialVersionUID = -2789798823602463949L;
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String newPassword;

    public UserDto() {}

    public UserDto(String username, String firstName, String lastName, String email, String phone, String password, String newPassword) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.newPassword = newPassword;
    }
}
