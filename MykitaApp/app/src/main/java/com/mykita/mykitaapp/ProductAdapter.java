package com.mykita.mykitaapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mykita.mykitaapp.model.Product;

import java.util.List;

/**
 * Created by shreyas13732 on 5/29/2017.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    public class ViewHolder{
        public TextView tvTitle, tvColor, tvOuterMaterial, tvIdealFor, tvOccasion, tvCost;
        ImageView ivProduct;
        LinearLayout llRowItem;

        public void init(View view) {
            llRowItem = (LinearLayout) view.findViewById(R.id.llRowItem);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvColor = (TextView) view.findViewById(R.id.tvColor);
            tvOuterMaterial = (TextView) view.findViewById(R.id.tvOuterMaterial);
            tvIdealFor = (TextView) view.findViewById(R.id.tvIdealFor);
            tvOccasion = (TextView) view.findViewById(R.id.tvOccasion);
            tvCost = (TextView) view.findViewById(R.id.tvCost);
            ivProduct = (ImageView) view.findViewById(R.id.ivProduct);
        }
    }

    public ProductAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = View.inflate(getContext(), R.layout.list_item_product, null);
            holder = new ViewHolder();
            holder.init(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Product product = getItem(position);

        holder.ivProduct.setImageResource(product.getImageRes());
        holder.tvTitle.setText(product.getTitle());
        holder.tvColor.setText("Colour: "+product.getColor());
        holder.tvOuterMaterial.setText("Outer Material: "+product.getOuterMaterial());
        holder.tvIdealFor.setText("Ideal for: "+product.getIdealFor());
        holder.tvOccasion.setText("Occasion: "+product.getOccassion());
        holder.tvCost.setText("Cost: "+product.getCost());

        return convertView;
    }


}