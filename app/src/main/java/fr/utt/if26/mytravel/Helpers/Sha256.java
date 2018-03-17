package fr.utt.if26.mytravel.Helpers;

import android.util.Log;

import java.security.MessageDigest;

public class Sha256 {
    public static final String sha256(String _str) {
        String str_ = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(_str.getBytes());

            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if(hex.length() == 1) sb.append('0');
                sb.append(hex);
            }
            str_ = sb.toString();
        } catch (Exception ex) {
            Log.e("===", "SHA256: "+ ex.getMessage());
        }

        return str_;
    }
}
