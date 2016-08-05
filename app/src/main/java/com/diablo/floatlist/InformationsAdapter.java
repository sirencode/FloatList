package com.diablo.floatlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;

/**
 * Created by clevo on 2015/7/27.
 */

public class InformationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<InformationData> products;
    private static InformationsItemClickListener itemClickListener;

    public InformationsAdapter(List<InformationData> list, InformationsItemClickListener clickListener) {
        products = list;
        itemClickListener = clickListener;
    }

    public List<InformationData> getDataList() {
        return products;
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    @Override
    public int getItemViewType(int position) {
        return InformationsItemType.Improtant.value();
    }

    public void setDataList(Collection<InformationData> list) {
        this.products.clear();
        this.products.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(Collection<InformationData> list) {
        int lastIndex = this.products.size();
        if (this.products.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

    public void delete(int position) {
        if (products != null) {
            products.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        if (products != null) {
            products.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = null;
        if (i == InformationsItemType.Improtant.value()) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.information_item, viewGroup, false);
            return new DemoViewHolder(view);
        }else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.information_error_type, viewGroup, false);
            return new ErrorViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof DemoViewHolder) {
            ((DemoViewHolder) viewHolder).imageView.setImageResource(products.get(position).getImg());
            ((DemoViewHolder) viewHolder).textView.setText(products.get(position).getTitle());
        }
    }

    //viewholder
    public static class DemoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView textView;


        public DemoViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.masonry_item_img);
            textView = (TextView) itemView.findViewById(R.id.masonry_item_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, this.getLayoutPosition());
        }
    }

    //viewholder
    public static class ErrorViewHolder extends RecyclerView.ViewHolder{

        public ErrorViewHolder(View itemView) {
            super(itemView);
        }
    }
}
