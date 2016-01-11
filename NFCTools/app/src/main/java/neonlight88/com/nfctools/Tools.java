package neonlight88.com.nfctools;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

    public static void foregroundDispatchSetup(final Activity activity, NfcAdapter nfcAdapter, String mimeType, String[][] techList) {
        final Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        final PendingIntent pendingIntent = PendingIntent.getActivity(activity.getApplicationContext(), 0, intent, 0);
        IntentFilter[] intentFilters = new IntentFilter[1];
        intentFilters[0] = new IntentFilter();
        intentFilters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        intentFilters[0].addCategory(Intent.CATEGORY_DEFAULT);
        try {
            intentFilters[0].addDataType(mimeType);
        } catch (IntentFilter.MalformedMimeTypeException ecxeption) {
            throw new RuntimeException("Mime type error. Please check it.");
        }
        nfcAdapter.enableForegroundDispatch(activity, pendingIntent, intentFilters, techList);
    }
}
