package ru.exceptionteapots.pricetrace;


import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//        myEdit.clear();
////        myEdit.putString("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJydS5wcmljZXRyYWNlLmNyYWNrZXJtYWtlciIsImlhdCI6MTY1NDA5ODI4MCwiZXhwIjoxNjYxODc0MjgwLCJzdWIiOiJlYjcyZTkzYjgxNWI0Y2Y5OTRmNjU2N2RiYjQxZWU5OSJ9.DG47tqpSearIoJqiwl3Sutsfq2azZa1FRNPKBr4dtQY");
//        myEdit.apply();
        setTheme(R.style.Theme_PriceTrace);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // активация нижней навигации
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
//        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    public void setChosenFragment(int number) {
        bottomNavigationView.getMenu().getItem(number).setChecked(true);
    }
}