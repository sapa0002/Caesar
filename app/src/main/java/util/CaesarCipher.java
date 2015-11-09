package util;

/**
 *  Encrypting and descrypting user inputs
 *  @author Abix S.(sapa0002@alqonquinlive.com)
 */
public class CaesarCipher {

    // No instantiation.
    private CaesarCipher() {
    }

    public static String encryptAndDecrypt(String plainMessage) {
        String encodedMessage = "";
        if (plainMessage == null) {
            return plainMessage;
        }
        for (int i = 0; i < plainMessage.length(); i++) {
            char c = plainMessage.charAt(i);
            if (c >= 'a' && c <= 'm') {
                c += 13;
            } else if (c >= 'n' && c <= 'z') {
                c -= 13;
            } else if (c >= 'A' && c <= 'M') {
                c += 13;
            } else if (c >= 'N' && c <= 'Z') {
                c -= 13;
            }
            encodedMessage += c;
        }
        return encodedMessage;
    }
}
