package edu.purdue.vieck.budgetapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.Calendar;

/**
 * Created by vieck on 7/13/15.
 */
public class SubmitFragment extends android.support.v4.app.DialogFragment {
    private Context mContext;
    private Spinner mSpinner;

    private Calendar calendar;

    private ToggleButton toggleButton;

    private EditText amount, month, day, year, note;

    private ImageButton submitButton;

    @Override
    public void onAttach(Activity activity) {
        mContext = activity.getApplicationContext();
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submit, container, false);
        mSpinner = (Spinner) view.findViewById(R.id.spinner_category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext, R.array.categoryarray,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        calendar = Calendar.getInstance();

        toggleButton = (ToggleButton) view.findViewById(R.id.income_or_expense_button);

        amount = (EditText) view.findViewById(R.id.edittext_amount);
        month = (EditText) view.findViewById(R.id.edittext_month);
        day = (EditText) view.findViewById(R.id.edittext_day);
        year = (EditText) view.findViewById(R.id.eddittext_year);

        month.setText(calendar.get(Calendar.MONTH) + "");
        day.setText(calendar.get(Calendar.DAY_OF_MONTH) + "");
        year.setText(calendar.get(Calendar.YEAR) + "");

        note = (EditText) view.findViewById(R.id.eddittext_note);

        submitButton = (ImageButton) view.findViewById(R.id.imagebtn_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BudgetElement budgetElement = new BudgetElement(
                        Float.parseFloat(amount.getText().toString()), mSpinner.getSelectedItem().toString(), toggleButton.isChecked(),
                        calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR),note.getText().toString()
                );
            }
        });
        return view;
    }
}
