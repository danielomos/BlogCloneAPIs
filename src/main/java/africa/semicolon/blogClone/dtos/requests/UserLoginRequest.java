package africa.semicolon.blogClone.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class UserLoginRequest {
    private String email;
    private String password;
}
