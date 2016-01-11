package neonlight88.com.nfctools;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
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

    @Override
    protected void onNewIntent(Intent intent) {
        intentHandler(intent);
    }

    private void intentHandler(Intent intent) {
        String intentAction = intent.getAction();
        if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intentAction) ||
                NfcAdapter.ACTION_TECH_DISCOVERED.equals(intentAction)) {
            if (MIMETYPE.equals(intent.getType())) {
                Tag nfcTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                new NdefRead(getApplicationContext()).execute(Ndef.get(nfcTag));
            } else {
                Tools.displayToast(getApplicationContext(), "Mime type error.");
            }
        }
    }

}
