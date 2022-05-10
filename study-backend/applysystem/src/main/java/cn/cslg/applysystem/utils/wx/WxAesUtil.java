package cn.cslg.applysystem.utils.wx;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 微信AES加密和解密类
 */
public class WxAesUtil {
    private static WxAesUtil instance = null;

    private WxAesUtil() {

    }

    public static WxAesUtil getInstance() {
        if (instance == null) {
            instance = new WxAesUtil();
        }
        return instance;
    }

    /**
     * OAuth2用的AES加密方法，常熟理工学院OAuth2平台服务器端使用的是C# AES/CBC/ZeroPadding 加密解密
     *
     * @param plain     要加密的明文字符串
     * @param secretKey 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位
     * @param iv        加密用的IV 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，IV需要为16位
     * @return 加密后的密文字节数组
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     */
    public byte[] encryptZeroPadding(String plain, String secretKey, String iv) throws
            NoSuchPaddingException, NoSuchAlgorithmException {

        if (secretKey == null) {
            return null;
        }
        if (secretKey.length() != 16) {
            return null;
        }

        //Java没有ZeroPadding填充，只能使用NoPadding填充之后自己进行填充
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        int blockSize = cipher.getBlockSize();
        byte[] dataBytes = plain.getBytes();
        int plainTextLength = dataBytes.length;
        if (plainTextLength % blockSize != 0) {
            plainTextLength += (blockSize - (plainTextLength % blockSize));
        }
        byte[] plainText = new byte[plainTextLength];
        System.arraycopy(dataBytes, 0, plainText, 0, dataBytes.length);

        //执行加密
        return doEncrypt(secretKey, iv, cipher, plainText);
    }


    /**
     * AES加密方法，使用AES/CBC/PKCS5Padding填充
     *
     * @param encData   要加密的明文字符串
     * @param secretKey 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位
     * @param vector    加密用的IV 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，IV需要为16位
     * @return 加密后的密文字节数组
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public byte[] encryptPkcs5Padding(String encData, String secretKey, String vector) throws NoSuchPaddingException,
            NoSuchAlgorithmException, UnsupportedEncodingException {

        if (secretKey == null) {
            return null;
        }
        if (secretKey.length() != 16) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        return doEncrypt(secretKey, vector, cipher, encData.getBytes("utf-8"));
    }


    /**
     * 使用指定的加密算法对象和（key，iv）对明文字节数组进行加密
     *
     * @param secretKey AES加密key
     * @param iv        AES填充IV
     * @param cipher    加密算法对象
     * @param plainText 明文字节数组
     * @return 加密后的字节数组；如果出现异常，则返回null
     */
    private byte[] doEncrypt(String secretKey, String iv, Cipher cipher, byte[] plainText) {
        byte[] raw = secretKey.getBytes();
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec ivs = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivs);
            byte[] encrypted = cipher.doFinal(plainText);
            return encrypted;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * OAuth2用的AES解密方法，常熟理工学院OAuth2平台服务器端使用的是C# AES/CBC/ZeroPadding 加密
     *
     * @param cipherText 密文
     * @param key        AES加密密钥
     * @param ivs        AES填充IV
     * @param base64     密文解密前是否需要先做BASE64解码。如果是，先做BASE64解码，然后再转换成16进制字符串；否则，直接转换成十六进制字符串
     * @return 解密后的明文；如果出现异常，则返回null
     */
    public String decryptZeroPadding(String cipherText, String key, String ivs, boolean base64) {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            if (base64) {
                // 先用base64解密
                byte[] encrypted1 = new BASE64Decoder().decodeBuffer(cipherText);
                //解码后的十六进制字符串再转换为二进制数组
                encrypted1 = HexBinTool.decodeHexString(new String(encrypted1));
                //AES解密
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } else {
                //十六进制字符串换为二进制数组
                byte[] encrypted1 = HexBinTool.decodeHexString(cipherText);
                //AES解密
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
