package org.ssak3.api.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssak3.api.user.entity.User;

@Data
@NoArgsConstructor
public class UserResponse {

    @Schema(description = "user PK", example = "1")
    private Long userId;

    @Schema(description = "user kb pin", example = "aj3df87a1123kjf5dm85")
    private String kbPIN;

    @Schema(description = "userName", example = "국민지")
    private String userName;

    @Schema(description = "age", example = "24")
    private Integer age;

    @Schema(description = "income", example = "400000")
    private Long income;

    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.kbPIN = user.getKbPIN();
        this.age = user.getAge();
        this.userName = user.getUserName();
        this.income = user.getIncome();
    }
}
