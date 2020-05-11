package com.app.contentresolversample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String txt = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tv);
        if (ContextCompat.checkSelfPermission(
                MainActivity.this,
                Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS},101);
        }
        ContentResolver cr = getContentResolver();
        Cursor csr = cr.query(ContactsContract.Contacts.CONTENT_URI, new String[] {ContactsContract.Contacts.DISPLAY_NAME_PRIMARY}, null, null, ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " ASC");
        while(csr.moveToNext()){
            txt = txt.concat(csr.getString(0) + "\n"
                    );
        }
        tv.setText(txt);
    }
}
