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

    private Fragment activeFragment;

    private final String DATA_FRAGMENT_TAG = "datafragtag";

    private DataFragment dataFragment;
    private AlertFragment alertFragment;
    private CameraFragment cameraFragment;
    private ApiFragment apiFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnimatedBottomBar abb = findViewById(R.id.bottom_bar);
        showFragment(new DataFragment());
        abb.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NotNull AnimatedBottomBar.Tab tab1) {
                if(activeFragment != null) {
                    activeFragment.getFragmentManager().beginTransaction().remove(activeFragment).commit();
                    activeFragment = null;
                }
                switch(i1){
                    case 0:
                        if(savedInstanceState != null){
                            dataFragment = (DataFragment) getSupportFragmentManager().findFragmentByTag(DATA_FRAGMENT_TAG);
                        }
                        showFragment(dataFragment = dataFragment == null?new DataFragment(): dataFragment);
                        break;
                    case 1:
                        showFragment(alertFragment = alertFragment == null?new AlertFragment(): alertFragment);
                        break;
                    case 2:
                        showFragment(cameraFragment = cameraFragment == null?new CameraFragment(): cameraFragment);
                        break;
                    case 3:
                        showFragment(apiFragment = apiFragment == null?new ApiFragment(): apiFragment);
                        break;
                }

            }
            @Override
            public void onTabReselected(int i, @NotNull AnimatedBottomBar.Tab tab) {

            }
        });

    }


    private void showFragment(Fragment fragment){
            activeFragment = fragment;
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layoutFragment, activeFragment)
                    .commit();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (dataFragment != null &&getSupportFragmentManager().findFragmentById(dataFragment.getId()) != null)
            getSupportFragmentManager().findFragmentById(dataFragment.getId()).setRetainInstance(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dataFragment != null && getSupportFragmentManager().findFragmentById(dataFragment.getId()) != null)
            getSupportFragmentManager().findFragmentById(dataFragment.getId()).getRetainInstance();
    }
}