package com.example.sudeepbajracharya.treeleaftask.feature.userList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sudeepbajracharya.treeleaftask.R;
import com.example.sudeepbajracharya.treeleaftask.feature.addUser.AddUserActivity;
import com.example.sudeepbajracharya.treeleaftask.feature.filterDialog.FilterFragment;
import com.example.sudeepbajracharya.treeleaftask.shared.adapter.UserListAdapter;
import com.example.sudeepbajracharya.treeleaftask.shared.model.UserDetailModel;
import com.example.sudeepbajracharya.treeleaftask.shared.utils.SendDataInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends MvpActivity<UserListView, UserListPresenter> implements UserListView, SendDataInterface {

    FloatingActionButton fabAddUser;
    RecyclerView rvUserList;
    ArrayList<UserDetailModel> userDetailModels = new ArrayList<>();
    private UserListAdapter adapter;
    private EditText edtFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        initListener();
        initRecyclerView();
        getData();
    }

    @NonNull
    @Override
    public UserListPresenter createPresenter() {
        return new UserListPresenter();
    }

    private void initWidgets() {
        rvUserList = findViewById(R.id.rvUserList);
        fabAddUser = findViewById(R.id.fabAddUser);
        edtFilter = findViewById(R.id.edtFilter);
    }

    private void getData() {
        presenter.getData();
    }

    private void initRecyclerView() {
        adapter = new UserListAdapter(userDetailModels);
        rvUserList.setHasFixedSize(true);
        rvUserList.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
        rvUserList.setAdapter(adapter);
    }

    private void initListener() {
        fabAddUser.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), AddUserActivity.class)));
        edtFilter.setOnClickListener(view -> {
            FilterFragment addPhotoBottomDialogFragment =
                    FilterFragment.newInstance();
            addPhotoBottomDialogFragment.show(getSupportFragmentManager(), "add_photo_dialog_fragment");
        });
    }

    @Override
    public void onSuccess(List<UserDetailModel> userDetailModelList) {
        userDetailModels.clear();
        for (UserDetailModel data : userDetailModelList) {
            userDetailModels.add(data);
        }
        synchronized (adapter) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDataPass(String date1, String date2) {
        Log.e("dates", date1 + "    " + date2);

        if(date1 =="reset" && date2 == "reset"){
            getData();
        }
        else{

        }
    }

    public void IsDateBetween(String date1, String date2, String thisDate) {

        long  from= Date.parse(date1);  // From some date

        long to=Date.parse(date2);     // To Some Date

        long check=Date.parse(thisDate);

        int x=0;

        //Todo sub end date wuth this date add 2000 in it. again subtract thisdate with start date

        if((check-from)>0 && (to-check)>0)
        {
            x=1;
        }

        System.out.println ("From Date is greater Than  ToDate : "+x);
    }
}