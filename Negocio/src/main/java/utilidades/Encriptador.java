/*
 * Encriptador.java
 */
package utilidades;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase para encriptar y desencriptar valores que requieran seguridad.
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class Encriptador {
    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "5Zn8Hq3rKjDpVxYs";

    /**
     * Método para encriptar valores valores.
     * @param valor Valor a encriptar.
     * @return El valor encriptado.
     * @throws Exception si sucede algún imprevisto.
     */
    public String encriptar(String valor) throws Exception {
        SecretKeySpec llave = generarLlave();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, (java.security.Key) llave);
        byte[] valorByteEncriptado = cipher.doFinal(valor.getBytes("utf-8"));
        return Base64.getEncoder().encodeToString(valorByteEncriptado);
    }

    /**
     * Método para desencriptar valores valores.
     * @param valor Valor a desencriptar.
     * @return El valor desencriptado.
     * @throws Exception si sucede algún imprevisto.
     */
    public String desencriptar(String valor) throws Exception {
        SecretKeySpec llave = generarLlave();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, (java.security.Key) llave);
        byte[] valor64Desencriptado = Base64.getDecoder().decode(valor);
        byte[] valorByteDesencriptado = cipher.doFinal(valor64Desencriptado);
        return new String(valorByteDesencriptado, "utf-8");
    }

    /**
     * Método para generar llaves secretas.
     * @return La llave secreta.
     */
    private static SecretKeySpec generarLlave() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
    }
}
