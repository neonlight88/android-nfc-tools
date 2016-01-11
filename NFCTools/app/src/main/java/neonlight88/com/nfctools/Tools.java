package neonlight88.com.nfctools;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.widget.Toast;

public class Tools {

    public static boolean checkNFC(NfcAdapter nfcAdapter) {
        if (nfcAdapter == null) {
            return false;
        } else {
            if (!nfcAdapter.isEnabled()) {
                return false;
            }
        }
        return true;
    }

    public static void displayToast(Context context, String msg) {
        Toast.makeText(
                context,
                msg,
                Toast.LENGTH_LONG
        ).show();
    }
}
