package ru.exceptionteapots.pricetrace;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) requireActivity()).setChosenFragment(2);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainerView);
        Button sendButton = view.findViewById(R.id.login_button);
        CircularProgressIndicator progressIndicator = view.findViewById(R.id.login_progress);
        EditText loginField = view.findViewById(R.id.login_text_field);
        EditText passwordField = view.findViewById(R.id.password_text_field);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressIndicator.setVisibility(View.VISIBLE);
                if (loginField.getText().toString().trim().length() == 0 || passwordField.getText().toString().trim().length() == 0) {
                    new MaterialAlertDialogBuilder(getContext())
                            .setTitle(getString(R.string.no_data_title))
                            .setMessage(getString(R.string.no_data_message))
                            .setIcon(R.drawable.ic_cancel)
                            .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {})
                            .show();
                    progressIndicator.setVisibility(View.INVISIBLE);
                    return;
                }

                Login login = new Login();
                login.setUsername(loginField.getText().toString());
                login.setPassword(passwordField.getText().toString());

                Call<Token> call = NetworkService.getInstance().getPriceTraceAPI().authenticate(login);
                call.enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(@NonNull Call<Token> call, @NonNull Response<Token> response) {
                        if (response.isSuccessful()) {
                            getParentFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            myEdit.putString("token", response.body().getToken());
                            myEdit.apply();
                            Bundle arg = new Bundle();
                            arg.putString("username", loginField.getText().toString());
                            navController.navigate(R.id.authorizationFragment, arg);
                        }
                        else {
                            new MaterialAlertDialogBuilder(getContext())
                                    .setTitle(getString(R.string.login_error_title))
                                    .setMessage(getString(R.string.login_error_message))
                                    .setIcon(R.drawable.ic_cancel)
                                    .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {})
                                    .show();
                            progressIndicator.setVisibility(View.INVISIBLE);
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<Token> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        });

        return view;
    }
}