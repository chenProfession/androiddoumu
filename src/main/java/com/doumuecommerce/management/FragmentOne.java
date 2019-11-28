package com.doumuecommerce.management;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.doumuecommerce.R;
import com.doumuecommerce.customer.Customer;
import com.doumuecommerce.resultsearch.ResultSearchCustomer;
import com.doumuecommerce.utils.HttpUtils;
import com.doumuecommerce.utils.ListViewCustomerAdapter;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;

public class FragmentOne extends Fragment {

    private List<Customer> customers = new ArrayList<>();
    private String sessionId;
    private ListView userlistView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);

        init();

        ListViewCustomerAdapter adapter = new ListViewCustomerAdapter(FragmentOne.this.getActivity(), R.layout.user_item, customers);
        adapter.setUrl("http://demo.jeesite.com/js");
        userlistView = view.findViewById(R.id.user_list);
        userlistView.setAdapter(adapter);

        return  view;
    }

    private void init() {
        customers.add(new Customer("暹罗猫","/ctxPath/static/images/user1.jpg"));
        customers.add(new Customer("布偶猫","/ctxPath/static/images/user1.jpg"));
        customers.add(new Customer("波斯猫","/ctxPath/static/images/user1.jpg"));
        customers.add(new Customer("俄罗斯蓝猫","/ctxPath/static/images/user1.jpg"));
        customers.add(new Customer("美国短毛猫","/ctxPath/static/images/user1.jpg"));
        customers.add(new Customer("异国短毛猫","/ctxPath/static/images/user1.jpg"));
        customers.add(new Customer("挪威森林猫","/ctxPath/static/images/user1.jpg"));
        customers.add(new Customer("孟买猫","/ctxPath/static/images/user1.jpg"));
        customers.add(new Customer("缅因猫","/ctxPath/static/images/user1.jpg"));
        customers.add(new Customer("埃及猫","/ctxPath/static/images/user1.jpg"));
    }

    /**
     * Fragment不是布局器，不具备渲染视图的能力
     * 在Fragment的onActivityCreated 方法中执行
     * */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //数据（网络请求数据使用Handler+子线程）
        new Thread(){
            @Override
            public void run() {
                super.run();
                final String httpUrl = "http://demo.jeesite.com/js/a/sys/secAdmin/listData.json?__sid=" + sessionId;
                HttpUtils httpUtils = new HttpUtils();
                String resultString = httpUtils.sendRequestGet(httpUrl);
                if(resultString != null) {
                    try {
                        //把数据发送给主线程
                        Message message=new Message();
                        message.what=1;
                        message.obj=resultString;
                        handler.sendMessage(message);
                        //的得到数据后发送给handler进行解析
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        //ListView listView = getActivity().findViewById(R.id.user_list);
        userlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer customer = customers.get(position);
                Toast.makeText(FragmentOne.this.getActivity(), customer.getMobile(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //得到数据并解析
                    String resultString = (String) msg.obj;
                    try {
                        //解析........需要导一个Gson的包
                        Gson gson = new Gson();
                        ResultSearchCustomer resultSearchCustomer = gson.fromJson(resultString, ResultSearchCustomer.class);
                        //展示数据到listView上
                        List<Customer> templist = resultSearchCustomer.getList();
                        customers.clear();
                        customers = templist;
                        ListViewCustomerAdapter adapter = new ListViewCustomerAdapter(FragmentOne.this.getActivity(), R.layout.user_item, templist);
                        adapter.setUrl("http://demo.jeesite.com/js");
                        userlistView.setAdapter(adapter);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;

            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        sessionId  = ((ManageActivity)context).getSessionId();
    }
}
