package imt.api.gachapi.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document
public class Account {
    @Id
    private int id;
    private String username;
    private String pasword;
    private String token;
    private LocalDateTime tokenExpiry;

    public Account(String username, String password) {
        this.username = username;
        this.pasword = password;
    }
}
