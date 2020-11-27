package fr.charlin.mobileproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class CameraFragment extends Fragment {

    private static int REQUEST_IMAGE_CAPTURE = 1;
    private ImageButton buttonCamera;

    public CameraFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View resView =  inflater.inflate(R.layout.fragment_camera, container, false);

        buttonCamera = (ImageButton) resView.findViewById(R.id.imageButton);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt,REQUEST_IMAGE_CAPTURE);
            }
        });
        return resView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bitmap bit = (Bitmap) data.getExtras().get("data");
            buttonCamera.setImageBitmap(bit);
        }

    }
}