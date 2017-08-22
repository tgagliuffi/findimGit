package com.bbva.findim.web.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Security;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author ZT
 */
public class AuthExtCryptoBean {

     public String encryptHex(String data, PublicKey PK) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException  {
        byte[] plainText = data.getBytes("UTF8");
        return encryptHex(plainText, PK);
    }

    public String encryptHex(byte[] data, PublicKey PK) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException  {

        Cipher cip = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        byte[] cipherText = null;

        byte[] plainText = data;
        cip.init(Cipher.ENCRYPT_MODE, PK);
        cipherText = cip.doFinal(plainText);

        return Tools.bytesToHexadecimal(cipherText).toUpperCase();
    }

    public String decryptHex(String eData, PublicKey PK) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException  {
        byte[] decryptedPlainText = decryptHexB(eData, PK);
        String decryptedPlainStr = new String(decryptedPlainText);

        return decryptedPlainStr;
    }

    public byte[] decryptHexB(String eData, PublicKey PK) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException  {
        Cipher cip = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cip.init(Cipher.DECRYPT_MODE, PK);

        byte[] c = Tools.hexStringToByteArray(eData);
        byte[] decryptedPlainText = cip.doFinal(c);// exception occurred here

        return decryptedPlainText;
    }


    @SuppressWarnings("restriction")
	public byte[] encrypt(byte[] data, String sKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException  {

        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        //desKey is the key developed by you ...
        SecretKeySpec desKeySpec = new SecretKeySpec(sKey.getBytes(), "DES");
        Key key = (javax.crypto.SecretKey) desKeySpec;

        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] outputBytes = cipher.doFinal(data);

        return outputBytes;

    }

    public String decryptHex(String eData, String sKey) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException  {

        byte[] outputBytes = decryptHexB(eData, sKey);

        return new String(outputBytes);

    }

    @SuppressWarnings("restriction")
	public byte[] decryptHexB(String eData, String sKey) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {

        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        //desKey is the key developed by you ...
        SecretKeySpec desKeySpec = new SecretKeySpec(sKey.getBytes(), "DES");
        Key key = (javax.crypto.SecretKey) desKeySpec;

        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] inputBytes = Tools.hexStringToByteArray(eData);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        return outputBytes;
    }    
    

    @SuppressWarnings("restriction")
	public byte[] decrypt(byte[] eData, String sKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException  {

        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        //desKey is the key developed by you ...
        SecretKeySpec desKeySpec = new SecretKeySpec(sKey.getBytes(), "DES");
        Key key = (javax.crypto.SecretKey) desKeySpec;

        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] outputBytes = cipher.doFinal(eData);

        return outputBytes;

    }

    public String getDESKey() {
        Random r = new Random();
        String rs1 = "00000000" + r.nextInt(100000000);
        rs1 = rs1.substring(rs1.length() - 8, rs1.length());
        String rs2 = "00000000" + r.nextInt(100000000);
        rs2 = rs2.substring(rs2.length() - 8, rs2.length());
        String rs3 = "00000000" + r.nextInt(100000000);
        rs3 = rs3.substring(rs3.length() - 8, rs3.length());
        String rs4 = "00000000" + r.nextInt(100000000);
        rs4 = rs4.substring(rs4.length() - 8, rs4.length());
        String rs5 = "00000000" + r.nextInt(100000000);
        rs5 = rs5.substring(rs5.length() - 8, rs5.length());
        String rs6 = "00000000" + r.nextInt(100000000);
        rs6 = rs6.substring(rs6.length() - 8, rs6.length());
        String rs7 = "00000000" + r.nextInt(100000000);
        rs7 = rs7.substring(rs7.length() - 8, rs7.length());
        String rs8 = "00000000" + r.nextInt(100000000);
        rs8 = rs8.substring(rs8.length() - 8, rs8.length());
        return rs1;//+rs2//+rs3+rs4+rs5+rs6+rs7+rs8;

    }


    public String encryptDataHex(String data, PublicKey PK) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException  {
        byte[] inputBytes = data.getBytes("UTF8");

        return encryptDataHex(inputBytes, PK);
    }

    public String encryptDataHex(byte[] data, PublicKey PK) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException  {

        String k = getDESKey();

        byte[] eData = encrypt(data, k);

        String ek = encryptHex(k, PK);

        return ek + Tools.TAG + Tools.bytesToHexadecimal(eData).toUpperCase();

    }


    public String decryptDataHex(String eData, PublicKey PK) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException  {

        String[] row = eData.split(Tools.TAG);

        String ek = row[0];
        String eData_ = row[1];

        String k = decryptHex(ek, PK);

        String data = decryptHex(eData_, k);


        return data;


    }    
    
}
