package neonlight88.com.nfctools;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.widget.EditText;

public class SendActivity extends Activity
        implements NfcAdapter.CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback {

    private NfcAdapter nfcAdapter;
    private EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
    }
}
