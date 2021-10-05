package com.example.sudeepbajracharya.treeleaftask.feature.filterDialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sudeepbajracharya.treeleaftask.R;
import com.example.sudeepbajracharya.treeleaftask.shared.utils.SendDataInterface;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FilterFragment extends BottomSheetDialogFragment {
    final Calendar myCalendar = Calendar.getInstance();
    TextView txvReset;
    EditText edtFrom, edtTo;
    Button btnApply;
    SendDataInterface sendDataInterface;

    public static FilterFragment newInstance() {
        return new FilterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        sendDataInterface = (SendDataInterface) getContext();

        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWidgets(view);
        initListener();
        setDateTo();
    }

    public void passDate(String date1, String date2) {
        sendDataInterface.onDataPass(date1, date2);
    }

    private void setDateTo() {
        String myFormat = "MMM dd,yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Log.e("date", sdf.toString());
        edtTo.setText(sdf.format(myCalendar.getTime()));
    }

    private void setDateFrom() {
        String myFormat = "MMM dd,yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Log.e("date", sdf.toString());
        edtFrom.setText(sdf.format(myCalendar.getTime()));
    }

    private void initListener() {
        DatePickerDialog.OnDateSetListener dateTo = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setDateTo();
        };

        DatePickerDialog.OnDateSetListener dateFrom = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setDateFrom();
        };

        edtTo.setOnClickListener(v -> new DatePickerDialog(getContext(), dateTo, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        edtFrom.setOnClickListener(v -> new DatePickerDialog(getContext(), dateFrom, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());


        txvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passDate("reset","reset");
                dismiss();
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtFrom.getText().toString().isEmpty()) {
                    passDate(edtFrom.getText().toString(), edtTo.getText().toString());
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Please make sure From date is not empty.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void openCalender() {

    }

    private void initWidgets(View view) {
        txvReset = view.findViewById(R.id.txvReset);
        edtFrom = view.findViewById(R.id.edtFrom);
        edtTo = view.findViewById(R.id.edtTo);
        btnApply = view.findViewById(R.id.btnApply);
    }
}
