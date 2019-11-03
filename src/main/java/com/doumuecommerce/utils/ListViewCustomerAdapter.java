package com.doumuecommerce.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.doumuecommerce.R;
import com.doumuecommerce.customer.Customer;

import java.util.List;

public class ListViewCustomerAdapter extends ArrayAdapter<Customer> {

    private int resourceId;
    private String url;

    /**
     * Constructor. This constructor will result in the underlying data collection being
     * immutable, so methods such as {@link #clear()} will throw an exception.
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public ListViewCustomerAdapter(@NonNull Context context, int resource, @NonNull List<Customer> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Customer customer = (Customer) getItem(position);

        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.image = view.findViewById(R.id.image_list);
            viewHolder.name =  view.findViewById(R.id.name_list);
            view.setTag(viewHolder);//保存
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//取出
        }

        if(customer.getAvatarUrl() != null){
            StringBuffer temp = new StringBuffer(customer.getAvatarUrl());
            temp.replace(0,8,url);

            viewHolder.image.setImageURL(temp.toString());
        }
        viewHolder.name.setText(customer.getUserName());

        return view;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private class ViewHolder {
        ListImageView image;
        TextView name;
    }
}
