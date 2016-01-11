package neonlight88.com.nfctools;

import android.content.Context;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;

public class NdefRead extends AsyncTask<Ndef, Void, String> {
    private Context context;

    public NdefRead(Context context) {
        this.context = context;
    }
}
