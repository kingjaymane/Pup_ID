package com.example.PupId;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.provider.MediaStore;

import com.example.flowerid.R;
import com.example.flowerid.ml.TfModel;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageView imageView;
    private Button selectImageButton, captureButton, predictButton;
    private TextView resultTextView;
    private Bitmap bitmap;

    private Interpreter interpreter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Permissions
        getPermission();
        String[] lables = new String[121];
        int cnt = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("labels.txt")));
            String line=bufferedReader.readLine();
            while (line!=null){
                lables[cnt]=line;
                cnt++;
                line=bufferedReader.readLine();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        imageView = findViewById(R.id.imageView);
        selectImageButton = findViewById(R.id.button);
        captureButton = findViewById(R.id.button2);
        predictButton = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.textView2);


        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 10);
            }
        });

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,12);

            }
        });

        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    TfModel model = TfModel.newInstance(MainActivity.this);

                    bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true);

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                    inputFeature0.loadBuffer(TensorImage.fromBitmap(bitmap).getBuffer());

                    // Runs model inference and gets result.
                    TfModel.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    resultTextView.setText(lables[getMax(outputFeature0.getFloatArray())]+" ");

                    // Releases model resources if no longer used.
                    model.close();
                } catch (IOException e) {
                    // TODO Handle the exception
                }
            }
        });
    }

    int getMax(float[] arr){
        int max=0;
        for (int i=0; i<arr.length;i++){
            if (arr[1]> arr[max]) max=i;
        }
        return max;
    }

    void getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 11);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 11) {
            if (grantResults.length > 0) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    this.getPermission();

                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (requestCode == 12) {
                bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
