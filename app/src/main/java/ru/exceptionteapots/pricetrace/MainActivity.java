package ru.exceptionteapots.pricetrace;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_PriceTrace);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // активация нижней навигации
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            navController.navigate(item.getItemId());
            return true;
        });

        bottomNavigationView.getMenu().getItem(2).setChecked(false);

    }


}