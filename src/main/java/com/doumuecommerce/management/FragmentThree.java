package com.doumuecommerce.management;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.doumuecommerce.R;
import com.doumuecommerce.authorization.User;

public class FragmentThree extends Fragment {

    private User userInfo;
    private ImageView ivUser;
    private TextView tvUserName;
    private TextView tvUserCode;
    private TextView tvUserDate;
    private TextView tvUserIP;
    private EditText edtPWD;
    private Button btnUpdate;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_three, container, false);
        ivUser = view.findViewById(R.id.ivUser);
        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserCode = view.findViewById(R.id.tvUserCode);
        tvUserDate = view.findViewById(R.id.tvDate);
        tvUserIP = view.findViewById(R.id.tvIP);
        edtPWD = view.findViewById(R.id.tvNewPWD);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //ivUser
        tvUserName.setText(userInfo.getUserName());
        tvUserCode.setText(userInfo.getUserCode());
        tvUserDate.setText(userInfo.getOldLoginDate());
        tvUserIP.setText(userInfo.getLastLoginIp());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:用户密码更新

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        userInfo  = ((ManageActivity)context).getUserInfo();
    }
}
