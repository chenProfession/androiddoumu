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
import com.doumuecommerce.product.Product;

import java.util.List;

public class ListViewProductAdapter extends ArrayAdapter<Product> {

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
    public ListViewProductAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Product product = (Product) getItem(position);

        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.image = view.findViewById(R.id.image_product);
            viewHolder.name =  view.findViewById(R.id.name_product);
            view.setTag(viewHolder);//保存
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//取出
        }

        if(product.getSmallImage() != null){
            viewHolder.image.setImageURL(product.getSmallImage());
        }
        viewHolder.name.setText(product.getTitle());

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
