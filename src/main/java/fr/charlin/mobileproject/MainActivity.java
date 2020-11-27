package fr.charlin.mobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import nl.joery.animatedbottombar.AnimatedBottomBar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MainActivity extends AppCompatActivity {
    private CompositeDisposable disposable = new CompositeDisposable();

    ImageView img;
    private static int REQUEST_IMAGE_CAPTURE = 1;
    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnimatedBottomBar abb = findViewById(R.id.bottom_bar);

        abb.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NotNull AnimatedBottomBar.Tab tab1) {
                if(activeFragment != null) {
                    activeFragment.getFragmentManager().beginTransaction().remove(activeFragment).commit();
                    activeFragment = null;
                }
                switch(i1){
                    case 0:
                        break;
                    case 1:
                        showFragment(new AlertFragment());
                        break;
                    case 2:
                        showFragment(new CameraFragment());
                        break;
                    case 3:
                        showFragment(new ApiFragment());
                        break;
                }
            }

            @Override
            public void onTabReselected(int i, @NotNull AnimatedBottomBar.Tab tab) {

            }

        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bit = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(bit);
        }

    }

    private void showFragment(Fragment fragment){
            activeFragment = fragment;
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layoutFragment, activeFragment)
                    .commit();
    }
}