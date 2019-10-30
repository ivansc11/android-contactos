package com.example.agenda;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class BirthdaySearchDialog extends DialogFragment {

    private static final String TAG = "BirthdaySearchDialog";

    //Widgets
    private EditText mDialogBirthdayInput;

    private Button mDialogOk;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dialog, container, false);
        mDialogBirthdayInput = view.findViewById(R.id.etBirthday);
        mDialogBirthdayInput.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etBirthday:
                        showDatePickerDialog();
                        break;
                }
            }
        });

        mDialogOk = view.findViewById(R.id.btnSearch);

        final DBManager manager = new DBManager(getActivity());

        mDialogOk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                MainActivity ma = (MainActivity) getActivity();

                Intent intent = new Intent(getActivity(), BirthdaySearchActivity.class);

                intent.putExtra("birthday", mDialogBirthdayInput.getText().toString());

                startActivity(intent);
            }
        });
        return view;
    }



    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "/" + (month+1) + "/" + year;
                mDialogBirthdayInput.setText(selectedDate);
            }
        });

        newFragment.show(getFragmentManager(), "datePicker");
    }

}
