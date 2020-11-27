package fr.charlin.mobileproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class DataFragment extends Fragment {
    String name;
    String surname;

    public DataFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View resView =  inflater.inflate(R.layout.fragment_data, container, false);
        EditText nameET = (EditText)resView.findViewById(R.id.editTextTextPersonName);
        EditText surnameET = (EditText)resView.findViewById(R.id.editTextTextPersonName2);

        if(savedInstanceState != null){
            name = savedInstanceState.getString("name");
            surname = savedInstanceState.getString("surname");

            nameET.setText(name);
            surnameET.setText(surname);
        }

        return resView;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name",name);
        outState.putString("surname", surname);
    }
}
