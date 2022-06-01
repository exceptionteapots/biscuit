package ru.exceptionteapots.pricetrace;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

public class ProfileFragment extends Fragment {
    private View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//        myEdit.putString("name", "aboba");
//        myEdit.putInt("age", Integer.parseInt("3"));
//        myEdit.apply();
        String s1 = sharedPreferences.getString("token", "");
        view = inflater.inflate(R.layout.fragment_unauthorized, container, false);


        return view;
    }
}