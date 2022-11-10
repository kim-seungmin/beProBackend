package NoJobs.BePro.Tool;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.MessageDigest;
import java.util.Date;

public class SecureTool {
    private static final int STRETCH_SIZE = 1000;        //스트레칭 횟수

    //Byte배열을 16진수 문자열로 변환
    private String byteToString(byte[] digest) {
        StringBuilder sb = new StringBuilder();

        for (byte b : digest) {
            sb.append(String.format("%02x", b));    // 1Byte = 8Bit = 16진수 2자리
        }

        return sb.toString();
    }

    // 비밀번호 + Salt값에 대한 해시 값을 반환
    public String getHashValue(String password,String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        for (int i = 0; i < STRETCH_SIZE; i++) {
            String temp = password + salt;
            md.update(temp.getBytes());
            password = byteToString(md.digest());
        }

        return password;
    }

    public String makePassword(String password, String id) throws Exception {
        return getHashValue(password,id);
    }

    public String makeJwtToken(String userId, String nickname) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(now)
                .claim("userId", userId)
                .claim("nickname", nickname)
                .signWith(SignatureAlgorithm.HS256, "bePro")
                .compact();
    }

}
