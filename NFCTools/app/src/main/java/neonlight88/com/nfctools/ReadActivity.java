package neonlight88.com.nfctools;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.os.Bundle;

public class ReadActivity extends Activity {

    private static final String MIMETYPE = "text/plain";
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(Tools.checkNFC(nfcAdapter)) {
            intentHandler(getIntent());
        } else {
            Tools.displayToast(this, "This device doesn't support NFC or it is disabled.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Tools.foregroundDispatchSetup(this, nfcAdapter, MIMETYPE, new String[][]{});
    }

    @Override
    protected void onPause() {
        nfcAdapter.disableForegroundDispatch(this);
        super.onPause();
    }

}
