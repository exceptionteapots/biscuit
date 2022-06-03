package ru.exceptionteapots.pricetrace.fragments;

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

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.exceptionteapots.pricetrace.MainActivity;
import ru.exceptionteapots.pricetrace.NetworkService;
import ru.exceptionteapots.pricetrace.R;
import ru.exceptionteapots.pricetrace.pojo.Registration;

public class RegFragment extends Fragment {

    private NavController navController;
    private Button sendButton;
    private Button infoButton;
    private EditText loginField;
    private EditText passwordField;
    private EditText emailField;
    private EditText passwordCheckField;
    private CircularProgressIndicator progressIndicator;

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
        sendButton = view.findViewById(R.id.reg_button);
        infoButton = view.findViewById(R.id.info_button);
        progressIndicator = view.findViewById(R.id.reg_progress);
        loginField = view.findViewById(R.id.reg_login_text_field);
        passwordField = view.findViewById(R.id.reg_password_text_field);
        emailField = view.findViewById(R.id.reg_email_text_field);
        passwordCheckField = view.findViewById(R.id.reg_password_text_field2);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle("Правила регистрации")
                        .setMessage("Логин 3-15 символов: возможны строчные и заглавные латинские буквы, цифры и спецсимволы (не более одного в ряд и не в начале строки)\n\nПароль более 8 символов: обязательны строчные и заглавные буквы, цифры и спецсимволы")
                        .setIcon(R.drawable.ic_info)
                        .setPositiveButton("OK", ((dialogInterface, i) -> {}))
                        .show();
            }
        });
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

                NetworkService.getInstance().getPriceTraceAPI().register(registration).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
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
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        });

        return view;
    }
}