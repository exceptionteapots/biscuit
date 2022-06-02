package ru.exceptionteapots.pricetrace;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import com.google.android.material.progressindicator.CircularProgressIndicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private NavController navController;

    private CircularProgressIndicator progressIndicator;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unauthorized, container, false);
        progressIndicator = view.findViewById(R.id.progress);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String token = sharedPreferences.getString("token", "");
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainerView);
        if (token.isEmpty()) progressIndicator.setVisibility(View.GONE);
        else {
            Call<User> call = NetworkService.getInstance().getPriceTraceAPI().isAuthenticated("Bearer " + token);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    if (response.isSuccessful()) {
                        Bundle arg = new Bundle();
                        arg.putString("username", response.body().getUsername());
                        navController.navigate(R.id.authorizationFragment, arg);
                    } else {
                        progressIndicator.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    t.printStackTrace();
                }
            });
        }
        Button signIn = view.findViewById(R.id.sign_in);
        Button signUp = view.findViewById(R.id.sign_up);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                navController.navigate(R.id.loginFragment);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                navController.navigate(R.id.regFragment);
            }
        });
        return view;

    }
}