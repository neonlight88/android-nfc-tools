package neonlight88.com.nfctools;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

public class MainActivity extends Activity {

    private final String MIMETYPE = "application/neonlight88.com.nfctools";
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        intentHandler(getIntent());
    }

    private void intentHandler(Intent intent) {
        if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())
                && intent.getType().equals(MIMETYPE)) {
            Parcelable[] messagesRaw = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage ndefMessage = (NdefMessage) messagesRaw[0];
            if(ndefMessage != null) {
                Tools.displayToast(
                        getApplicationContext(),
                        new String(ndefMessage.getRecords()[0].getPayload())
                );
            }
        }
    }

    //btn1 - onClick
    public void read(View view) {
        startActivity(new Intent(MainActivity.this, ReadActivity.class));
    }

    //btn2 - onClick
    public void write(View view) {
        startActivity(new Intent(MainActivity.this, WriteActivity.class));
    }

    //bt3 - onClick
    public void send(View view) {
        startActivity(new Intent(MainActivity.this, SendActivity.class));
    }
}
