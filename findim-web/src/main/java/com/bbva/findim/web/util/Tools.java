/*
 * ZyUtils.java
 *
 */
package com.bbva.findim.web.util;

import java.io.ByteArrayInputStream;

/**
 *
 * @author jose.fernandez
 */
public final class Tools {

    
   
   public static String TK = "";
   public static final String TAG = "A2DC5FE4CBA1";

    
    public Tools() {
    }


    public static String bytesToHexadecimal(byte[] datos) {
        String resultado = "";
        ByteArrayInputStream input = new ByteArrayInputStream(datos);
        String cadAux;
        int leido = input.read();
        while (leido != -1) {
            cadAux = Integer.toHexString(leido);
            if (cadAux.length() < 2) //Hay que añadir un 0
            {
                resultado += "0";
            }
            resultado += cadAux;
            leido = input.read();
        }
        return resultado;
    }

    public static final byte[] hexadecimalToBytes(String encoded) {
        byte result[] = new byte[encoded.length() / 2];
        char enc[] = encoded.toUpperCase().toCharArray();
        StringBuffer curr;
        for (int i = 0; i < enc.length; i += 2) {
            curr = new StringBuffer("");
            curr.append(String.valueOf(enc[i]));
            curr.append(String.valueOf(enc[i + 1]));
            result[i] = (byte) Integer.parseInt(curr.toString(), 16);
        }
        return result;
    }
    
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }    
    
}
