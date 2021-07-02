package ru.scarletarrow.bootmap.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsernameAndPasswordRequest {
    private String username;
    private String password;
}
