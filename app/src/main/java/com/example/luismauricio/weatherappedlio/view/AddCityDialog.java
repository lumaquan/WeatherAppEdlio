package com.example.luismauricio.weatherappedlio.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luismauricio.weatherappedlio.R;

public class AddCityDialog extends DialogFragment {


    interface AddCityListener {
        void onCityAdded(String city);
    }

    private AddCityListener addCityListener;

    @SuppressLint("ValidFragment")
    public AddCityDialog(AddCityListener addCityListener) {
        this.addCityListener = addCityListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View view = layoutInflaterAndroid.inflate(R.layout.add_city_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
        alertDialogBuilderUserInput.setView(view);

        final EditText cityName = view.findViewById(R.id.city);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("save", (dialogBox, id) -> {
                })
                .setNegativeButton("cancel",
                        (dialogBox, id) -> dialogBox.cancel());

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            if (TextUtils.isEmpty(cityName.getText().toString())) {
                Toast.makeText(getContext(), "Enter note!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                addCityListener.onCityAdded(cityName.getText().toString());
                alertDialog.dismiss();
            }
        });
        return alertDialog;
    }
}
