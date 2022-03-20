package com.android.devthien.handlecameraandrenderimage;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.android.devthien.handlecameraandrenderimage.Constant.LocalAccount;
import com.android.devthien.handlecameraandrenderimage.GridViewImage.GridViewImage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int CAMERA_PERM_CODE = 101;
    GridView imageView;
    Button takePhoto;
    EditText usernameTx;
    EditText passwordTx;
    String username;
    String password;
    Button signIn;
    List<Bitmap> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.grid_view);
        takePhoto = findViewById(R.id.bottomButton);
        signIn = findViewById(R.id.sign_in_btn);
        usernameTx = findViewById(R.id.username);
        passwordTx = findViewById(R.id.password);

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!usernameTx.getText().toString().equals(LocalAccount.LOCAL_USERNAME) || !passwordTx.getText().toString().equals(LocalAccount.LOCAL_PASSWORD) ){
                    Toast.makeText(MainActivity.this, "Tài khoản hoặc mật khẩu sai", Toast.LENGTH_LONG).show();
                    return;
                }
                if (usernameTx.getText().toString().equals(LocalAccount.LOCAL_USERNAME) && passwordTx.getText().toString().equals(LocalAccount.LOCAL_PASSWORD) ){
                   username = usernameTx.getText().toString();
                   password = passwordTx.getText().toString();
                   Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    usernameTx.setText("");
                    passwordTx.setText("");
                }
            }
        });



    }


    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Bitmap photo = (Bitmap) data.getExtras().get("data");
                        images.add(photo);
                        GridViewImage gridViewImage = new GridViewImage(images, MainActivity.this);
                        imageView.setAdapter(gridViewImage);
                    }
                }
            });
    ActivityResultLauncher<String> launchCamera =
            registerForActivityResult(
                    new ActivityResultContracts.RequestPermission(), isGranted -> {
                        if (isGranted) {
                            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            activityResultLauncher.launch(camera);
                        } else {

                        }
                    });

    private void openCamera() {
        if (checkLogin()){
            launchCamera.launch(Manifest.permission.CAMERA);
        }
    }
    private boolean checkLogin(){
        if (username == null || password == null || username.isEmpty() || password.isEmpty()){
            Toast.makeText(MainActivity.this, "Bạn chưa đăng nhập !!!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


}

