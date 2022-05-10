package cn.edu.cslg.util;

/**
 * 十六进制和二进制字符之间转换
 */
public class HexBinTool {
    /**
     * 字节转换成两位十六进制字符
     *
     * @param num 要转换的字节
     * @return 两位十六进制字符组成的字符串
     */
    public static String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }

    /**
     * 两位十六进制字符组成的字符串转换成字节
     *
     * @param hexString 两位十六进制字符组成的字符串
     * @return 一个字节
     */
    public static byte hexToByte(String hexString) {
        int firstDigit = toDigit(hexString.charAt(0));
        int secondDigit = toDigit(hexString.charAt(1));
        return (byte) ((firstDigit << 4) + secondDigit);
    }

    /**
     * 返回由指定基数（16进制）中的字符表示的数值
     *
     * @param hexChar 字符
     * @return 字符代表的数值
     */
    private static int toDigit(char hexChar) {
        int digit = Character.digit(hexChar, 16);
        if (digit == -1) {
            throw new IllegalArgumentException(
                    "Invalid Hexadecimal Character: " + hexChar);
        }
        return digit;
    }

    /**
     * 十六进制字符串转换成字节数组
     *
     * @param hexString 十六进制字符串
     * @return 字节数组
     */
    public static byte[] decodeHexString(String hexString) {
        if (hexString.length() % 2 == 1) {
            throw new IllegalArgumentException(
                    "Invalid hexadecimal String supplied.");
        }

        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
        }
        return bytes;
    }

    /**
     * 字节数组转换成十六进制字符串
     *
     * @param byteArray 字节数组
     * @return 十六进制字符串
     */
    public static String encodeHexString(byte[] byteArray) {
        StringBuffer hexStringBuffer = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            hexStringBuffer.append(byteToHex(byteArray[i]));
        }
        return hexStringBuffer.toString();
    }

}
