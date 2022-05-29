package ru.exceptionteapots.pricetrace;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

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

        bottomNavigationView.getMenu().getItem(2).setChecked(true);
//        FrameLayout layoutBottomSheet;
//        layoutBottomSheet = findViewById(R.id.bottom_sheet);
//        BottomSheetBehavior sheetBehavior;
//        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
//        sheetBehavior.setPeekHeight(0);
//        TextView textView = findViewById(R.id.sheet_title);
//        TextView textView1 = findViewById(R.id.sheet_description);
//        ImageView imageView = findViewById(R.id.sheet_img);
//        textView.setText("FFp");
//        textView1.setText("FNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNFONFWLIFNFNFOIENFWINFLWINFLWIFNWLEFNWLINFWLIFNFNWLINFWLIFN LWINF LWNEFL WENLF NWEL FNL NFWE LNWL N NLWEIFWLIFNE LWN LE NL IN IN");
//        try {
//            Picasso.get().load("https://pricetrace.ru/static/img/5eef8bb9aca485606cceb5c706535dce5f0a6c0b58fb2a2e2ca9576b20b970ae.jpg").into(imageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }


}