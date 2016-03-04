package com.imzhiqiang.glidedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.imzhiqiang.glidedemo.util.Constants.IMG_NAME;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content, new MainFragment())
                .commit();

        copyFile();
    }

    private void copyFile() {
        InputStream is;
        try {
            is = getAssets().open(IMG_NAME);
            FileOutputStream fos = new FileOutputStream(new File(getFilesDir(), IMG_NAME));
            byte[] buffer = new byte[1024];
            int byteCount;
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
            is.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
