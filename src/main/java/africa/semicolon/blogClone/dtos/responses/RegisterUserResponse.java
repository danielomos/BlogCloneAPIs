package africa.semicolon.blogClone.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterUserResponse {
    private String Message;
    private String userId;

}
