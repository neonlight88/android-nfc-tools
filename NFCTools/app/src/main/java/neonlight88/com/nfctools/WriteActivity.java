package neonlight88.com.nfctools;

import android.app.Activity;
import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.widget.EditText;

import java.io.IOException;

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

    @Override
    protected void onNewIntent(Intent intent) {
        intentHandler(intent);
    }

    private void intentHandler(Intent intent) {
        String intentAction = intent.getAction();
        if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intentAction) ||
                NfcAdapter.ACTION_TECH_DISCOVERED.equals(intentAction)) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String[] techList = tag.getTechList();
            for (String tech : techList) {
                if (tech.equals(Ndef.class.getName())) {
                    writeNfcTag(et1.getText().toString(), tag);
                }
            }
        }
    }

    private void writeNfcTag(String msg, Tag tag) {
        NdefRecord[] ndefRecords = new NdefRecord[1];
        ndefRecords[0] = NdefRecord.createTextRecord("", msg);
        NdefMessage ndefMessage = new NdefMessage(ndefRecords);
        Ndef ndef = Ndef.get(tag);
        try {
            ndef.connect();
            ndef.writeNdefMessage(ndefMessage);
            ndef.close();
            et1.setText("");
            Tools.displayToast(getApplication(), "Text written to the tag: " + msg);
        } catch (FormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
