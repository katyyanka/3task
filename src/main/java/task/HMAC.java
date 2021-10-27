package task;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

public class HMAC {

    private final String hmacKey;

    private final int move;

    private String HMAC;

    public HMAC(List<String> moves) {
        hmacKey = generateHMACKey();
        move = generateMove(moves);
        try {
            HMAC = encode(hmacKey, moves.get(move));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String encode(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        //new String(Hex.encodeHex("..."));
        StringBuilder formatted = new StringBuilder();
        byte[] bytes = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        for(byte b: bytes) {
            formatted.append(String.format("%x", b));
        }
        System.out.println(formatted);
        return formatted.toString();
    }

    public String getHmacKey() {
        return hmacKey;
    }

    public int getMove() {
        return move;
    }

    public String getHmac() {
        return HMAC;
    }

    private String generateHMACKey(){
        SecureRandom random = new SecureRandom();
        byte[] num = new byte[16];
        random.nextBytes(num);
        StringBuilder formatted = new StringBuilder();
        for(byte b: num) {
            formatted.append(String.format("%x", b));
        }
        formatted = new StringBuilder(formatted.toString().toUpperCase());
        return formatted.toString();
    }

    private int generateMove(List<String> moves){
        SecureRandom random = new SecureRandom();
        return random.nextInt(moves.size());
    }

}
