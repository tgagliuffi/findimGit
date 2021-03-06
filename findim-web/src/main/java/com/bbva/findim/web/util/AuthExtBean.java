package com.bbva.findim.web.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.bbva.findim.dom.BioAuthData;

/**
*
* @author Jose Fernandez
*/
public class AuthExtBean {	
	
   static String PKSTR = "30819f300d06092a864886f70d010101050003818d00308189028181009b7f261dc368f980e17b5fd71d16b50a27e3114c07e9241fa8a41c634b0ee2019354bfd4e64071ff2d20229b6b9c68146207f0aa9b56a0a1c8a9de0f180bb31dfc0842b4df9d8b4b9cdddd05d9cec7fd6666e27f7c20f8f07e39b135427176f2a717709ecd8f670821d6bb7e8dbed32508a2e53b5e08bbf3cdbec555bfce73a30203010001";
   static PublicKey PK;
   
   AuthExtCryptoBean aec;

   public AuthExtBean() throws NoSuchAlgorithmException, InvalidKeySpecException{

           byte[] PKBytes = new java.math.BigInteger(PKSTR, 16).toByteArray();
           X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(PKBytes);
           KeyFactory keyFact = KeyFactory.getInstance("RSA");
           PK = keyFact.generatePublic(x509KeySpec);
           aec = new AuthExtCryptoBean();
   }

   public BioAuthData decryptDecodeData(String eData) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
       BioAuthData bioAuthData = new BioAuthData();
       if (eData==null) {
           bioAuthData.setCoError("19018"); //trama es es nula
           return bioAuthData;
       }
       if (eData.length()<6){
           bioAuthData.setCoError("19019"); // trama es incompleta
           return bioAuthData;
       }
       
       eData = eData.substring(6, eData.length());
       
       String data = decryptData(eData);
       
       String arr[] = data.split(Tools.TAG);

       if (arr.length==5 || arr.length==6){
           bioAuthData.setCoError("19020"); //version incompatible
           return bioAuthData;
       }

       if (arr.length!=20){
           bioAuthData.setCoError("19019"); //trama es incompleta
           return bioAuthData;
       }

       bioAuthData.setTypeTxn(arr[0]);
       bioAuthData.setCoError(arr[1]);
       bioAuthData.setDeError(arr[2]);
       bioAuthData.setIdPC(arr[3]);
       bioAuthData.setIdMSO(arr[4]);
       bioAuthData.setMac(arr[5]);
       bioAuthData.setIp(arr[6]);
       bioAuthData.setAgentName(arr[7]);
       bioAuthData.setAgentVersion(arr[8]);
       bioAuthData.setNombre(arr[9]);
       bioAuthData.setPaterno(arr[10]);
       bioAuthData.setMaterno(arr[11]);
       bioAuthData.setCoErrorReniec(arr[12]);
       bioAuthData.setDeErrorReniec(arr[13]);
       bioAuthData.setNuSolicitud(arr[14]);
       bioAuthData.setBioInRestriccion(arr[15]);
       bioAuthData.setBioDesInRestriccion(arr[16]);
       bioAuthData.setBioInVigencia(arr[17]);
       bioAuthData.setBioDesInVigencia(arr[18]);
       bioAuthData.setIdTxn(arr[19]);
       
       return bioAuthData;
   
   }    
   
   public String encryptData(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
       return aec.encryptDataHex(data, PK);
   }
   
   public String decryptData(String eData) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException  {
       return aec.decryptDataHex(eData, PK);
   }
   
   
}
