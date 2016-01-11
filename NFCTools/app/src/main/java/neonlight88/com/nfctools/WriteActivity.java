package neonlight88.com.nfctools;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.widget.EditText;

public class WriteActivity extends Activity {

    private final String MIMETYPE = "*/*";
    private NfcAdapter nfcAdapter;
    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

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
        String[][] techList = new String[][]{
                new String[]{Ndef.class.getName()}
        };
        Tools.foregroundDispatchSetup(this, nfcAdapter, MIMETYPE, techList);
    }

    @Override
    protected void onPause() {
        nfcAdapter.disableForegroundDispatch(this);
        super.onPause();
    }
}
