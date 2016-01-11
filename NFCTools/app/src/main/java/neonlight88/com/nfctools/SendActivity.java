package neonlight88.com.nfctools;

import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;

public class SendActivity extends Activity
        implements NfcAdapter.CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback {

    private NfcAdapter nfcAdapter;
    private EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        et2 = (EditText)findViewById(R.id.et2);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(Tools.checkNFC(nfcAdapter)) {
            nfcAdapter.setNdefPushMessageCallback(this, this);
            nfcAdapter.setOnNdefPushCompleteCallback(this, this);
        } else {
            Tools.displayToast(this, "This device doesn't support NFC or it is disabled.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onNdefPushComplete(NfcEvent nfcEvent) {
        handler.obtainMessage(1).sendToTarget();
    }

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch(message.what) {
                case 1:
                    Tools.displayToast(getApplicationContext(), "Beaming data with NFC successful.");
                    //finish();
                    break;
            }
        }
    };

    @Override
    public NdefMessage createNdefMessage(NfcEvent nfcEvent) {
        NdefRecord[] ndefRecords = new NdefRecord[1];
        ndefRecords[0] = NdefRecord.createMime("application/neonlight88.com.nfctools", et2.getText().toString().getBytes());
        NdefMessage ndefMessage = new NdefMessage(ndefRecords);
        return ndefMessage;
    }
}
