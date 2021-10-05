package com.example.sudeepbajracharya.treeleaftask.feature.addUser;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.sudeepbajracharya.treeleaftask.R;
import com.example.sudeepbajracharya.treeleaftask.feature.userList.MainActivity;
import com.example.sudeepbajracharya.treeleaftask.shared.model.UserDetailModel;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddUserActivity extends MvpActivity<AddUserView, AddUserPresenter> implements AddUserView {
    EditText edtUserName, edtDate, edtMailID, edtPhoneNumber, edtDeviceUsed;
    Button btnSave;
    ImageView imvPic;
    int IMAGE_CODE = 100;
    byte[] byteArray = null;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        initWidgets();
        initListener();
    }

    @NonNull
    @Override
    public AddUserPresenter createPresenter() {
        return new AddUserPresenter();
    }

    private void initWidgets() {
        edtUserName = findViewById(R.id.edtUserName);
        edtDate = findViewById(R.id.edtDate);
        edtMailID = findViewById(R.id.edtMailID);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtDeviceUsed = findViewById(R.id.edtDeviceUsed);
        imvPic = findViewById(R.id.imvPic);
        btnSave = findViewById(R.id.btnSave);
    }

    private void initListener() {
        imvPic.setOnClickListener(view -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, IMAGE_CODE);

        });
        btnSave.setOnClickListener(view -> {
            UserDetailModel userDetailModel = new UserDetailModel();
            userDetailModel.setDevice(edtDeviceUsed.getText().toString());
            userDetailModel.setDate(edtDate.getText().toString());
            userDetailModel.setMail(edtMailID.getText().toString());
            userDetailModel.setName(edtUserName.getText().toString());
            userDetailModel.setPhone(edtPhoneNumber.getText().toString());
            userDetailModel.setImageUrl(byteArray);

//            userDetailModel.setDevice("Mac");
//            userDetailModel.setDate("2021-01-03");
//            userDetailModel.setMail("bajracharyasudeep@gmail.com");
//            userDetailModel.setName("Sudeep Bajracharya");
//            userDetailModel.setPhone("9808242510");

            presenter.saveData(userDetailModel);
        });

        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setDate();
        };

        edtDate.setOnClickListener(v -> new DatePickerDialog(AddUserActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());
    }

    private void setDate() {
        String myFormat = "MMM dd,yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Log.e("date", sdf.toString());
        edtDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap bmp = BitmapFactory.decodeStream(imageStream);
                imvPic.setImageBitmap(bmp);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}