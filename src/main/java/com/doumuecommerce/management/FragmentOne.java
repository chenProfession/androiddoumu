package com.doumuecommerce.management;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.doumuecommerce.R;
import com.doumuecommerce.customer.Customer;
import com.doumuecommerce.utils.ListViewCustomerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentOne extends Fragment {

    private List<Customer> customers = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);

        init();

        ListViewCustomerAdapter adapter = new ListViewCustomerAdapter(FragmentOne.this.getActivity(), R.layout.user_item, customers);
        adapter.setUrl("http://demo.jeesite.com/js");
        ((ListView) view.findViewById(R.id.user_list)).setAdapter(adapter);

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
}
