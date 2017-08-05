package com.button.simulator;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

public class MainActivity extends AppCompatActivity {

    private RoundedImageView btnView;
    private TextView clickText;
    private FloatingActionButton chooserBtn;
    private FloatingActionButton defaultBtn;

    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = ((MyApplication) getApplication()).preferences;

        initViews();
    }

    private void initViews() {
        btnView = (RoundedImageView) findViewById(R.id.btnView);
        btnView.setOnClickListener(onClick);
        if (!preferences.getImageUri().isEmpty()) {
            btnView.setImageURI(Uri.parse(preferences.getImageUri()));
        }
        chooserBtn = (FloatingActionButton) findViewById(R.id.chooserBtn);
        chooserBtn.setOnClickListener(onClick);
        defaultBtn = (FloatingActionButton) findViewById(R.id.defaultBtn);
        defaultBtn.setOnClickListener(onClick);
        clickText = (TextView) findViewById(R.id.clicksText);
        updateClicks();
    }

    private void updateClicks() {
        clickText.setText("Clicks: " + preferences.getClicks());
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.chooserBtn) {
                pickImage();
            } else if (view.getId() == R.id.btnView) {
                preferences.addClicks(1);
                updateClicks();
            } else if (view.getId() == R.id.defaultBtn) {
                preferences.setImageUri("");
                btnView.setImageBitmap(null);
            }
        }
    };

    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 1111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1111 && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            preferences.setImageUri(imageUri.toString());
            btnView.setImageURI(imageUri);
        }
    }
}
