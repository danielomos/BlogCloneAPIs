package africa.semicolon.blogClone.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserResponse {
    private Boolean isSuccessful = false;
    private String message;
    private String email;
    private String userId;
}
