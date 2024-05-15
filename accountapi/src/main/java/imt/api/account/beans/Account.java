package imt.api.account.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Document
public class Account {
    @Id
    private @Getter @Setter String id;

    private @Getter @Setter String username;
    private @Getter @Setter String password;
    private @Getter @Setter String token;
    private @Getter @Setter LocalDateTime tokenExpiry;
    public String generateToken() throws NoSuchAlgorithmException {
        String token = this.username
                + "-"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY/MM/dd"))
                + "-"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return encryptToken(token);
    }

    private static String encryptToken(String token){
        // TODO voir si on garde cet algo de cryptage car on peux pas décrypt
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(token.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            StringBuilder hashtext = new StringBuilder(no.toString(16));
            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }
            return hashtext.toString();
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkCredential(String password) {
        return this.password.equals(password);
    }
}
