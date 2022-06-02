package ru.exceptionteapots.pricetrace;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegFragment extends Fragment {

    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) requireActivity()).setChosenFragment(2);
        View view = inflater.inflate(R.layout.fragment_reg, container, false);
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainerView);
        Button sendButton = view.findViewById(R.id.reg_button);
        CircularProgressIndicator progressIndicator = view.findViewById(R.id.reg_progress);
        EditText loginField = view.findViewById(R.id.reg_login_text_field);
        EditText passwordField = view.findViewById(R.id.reg_password_text_field);
        EditText emailField = view.findViewById(R.id.reg_email_text_field);
        EditText passwordCheckField = view.findViewById(R.id.reg_password_text_field2);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressIndicator.setVisibility(View.VISIBLE);
                if (loginField.getText().toString().trim().length() == 0 || passwordField.getText().toString().trim().length() == 0 || emailField.getText().toString().trim().length() == 0 || passwordCheckField.getText().toString().trim().length() == 0) {
                    new MaterialAlertDialogBuilder(getContext())
                            .setTitle(getString(R.string.no_data_title))
                            .setMessage(getString(R.string.no_data_message))
                            .setIcon(R.drawable.ic_cancel)
                            .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {})
                            .show();
                    progressIndicator.setVisibility(View.INVISIBLE);
                    return;
                }
                if (!passwordCheckField.getText().toString().equals(passwordCheckField.getText().toString())) {
                    new MaterialAlertDialogBuilder(getContext())
                            .setTitle(getString(R.string.no_data_title))
                            .setMessage(getString(R.string.check_password_failed))
                            .setIcon(R.drawable.ic_cancel)
                            .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {})
                            .show();
                    progressIndicator.setVisibility(View.INVISIBLE);
                    return;
                }
                Registration registration = new Registration();
                registration.setUsername(loginField.getText().toString());
                registration.setPassword(passwordField.getText().toString());
                registration.setEmail(emailField.getText().toString());

                Call<RegAnswer> call = NetworkService.getInstance().getPriceTraceAPI().register(registration);
                call.enqueue(new Callback<RegAnswer>() {
                    @Override
                    public void onResponse(@NonNull Call<RegAnswer> call, @NonNull Response<RegAnswer> response) {
                        if (response.isSuccessful()) {
                            getParentFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            Snackbar.make(view, "Успешно! Теперь можно войти в новый аккаунт", Snackbar.LENGTH_LONG).show();
                            getParentFragmentManager().popBackStack();
                        }
                        else {
                            new MaterialAlertDialogBuilder(getContext())
                                    .setTitle(getString(R.string.reg_error_title))
                                    .setMessage(getString(R.string.login_error_message))
                                    .setIcon(R.drawable.ic_cancel)
                                    .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {})
                                    .show();
                            progressIndicator.setVisibility(View.INVISIBLE);
                            Toast.makeText(getContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<RegAnswer> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        });

        return view;
    }
}