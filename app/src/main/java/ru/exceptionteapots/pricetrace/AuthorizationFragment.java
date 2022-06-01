package ru.exceptionteapots.pricetrace;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

public class AuthorizationFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authorized, container, false);
        String username = AuthorizationFragmentArgs.fromBundle(getArguments()).getUsername();
        TextView helloText = view.findViewById(R.id.hello);
        Button buttonQuit = view.findViewById(R.id.quit_button);
        helloText.setText("Привет, " + username + "!");
        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.remove("token");
                myEdit.apply();
                NavController navController = Navigation.findNavController(getActivity(), R.id.fragmentContainerView);
                navController.navigate(R.id.profileFragment);
            }
        });

        return view;
    }
}