package neonlight88.com.nfctools;

import android.content.Context;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class NdefRead extends AsyncTask<Ndef, Void, String> {
    private Context context;

    public NdefRead(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Ndef... params) {
        Ndef ndef = params[0];
        if(ndef == null) {
            return null;
        }
        NdefMessage ndefMessage = ndef.getCachedNdefMessage();
        NdefRecord[] ndefRecords = ndefMessage.getRecords();
        for(NdefRecord ndefRecord : ndefRecords) {
            if((ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN) &&
                    (Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT))) {
                try {
                    byte[] payload = ndefRecord.getPayload();
                    String utf8 = "UTF-8";
                    String utf16 = "UTF-16";
                    String textEncoding = ((payload[0] & 128) == 0) ? utf8 : utf16;
                    int lanCodeLength = payload[0] & 0063;
                    return new String(payload, lanCodeLength + 1, payload.length - lanCodeLength - 1, textEncoding);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("Unsupported encoding error");
                }
            }
        }
        return null;
    }
}
