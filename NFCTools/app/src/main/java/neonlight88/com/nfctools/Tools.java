package neonlight88.com.nfctools;

import android.nfc.NfcAdapter;

public class Tools {

    public static boolean checkNFC(NfcAdapter nfcAdapter) {
        if(nfcAdapter == null) {
            return false;
        } else {
            if(!nfcAdapter.isEnabled()) {
                return false;
            }
        }
}
