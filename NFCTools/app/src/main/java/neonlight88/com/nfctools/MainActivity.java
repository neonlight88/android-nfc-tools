package neonlight88.com.nfctools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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