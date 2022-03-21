package com.android.devthien.handlecameraandrenderimage.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.devthien.handlecameraandrenderimage.R;

public class Hello extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        Button btnHello = findViewById(R.id.helloBtn);
        btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText text = findViewById(R.id.editTextHello);
                Toast.makeText(Hello.this, text.getText().toString(), Toast.LENGTH_LONG).show();
                onBackPressed();

            }
        });

    }
}
