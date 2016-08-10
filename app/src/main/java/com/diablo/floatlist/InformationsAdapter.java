package com.diablo.floatlist;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;

/**
 * Created by Diablo on 16/8/10.
 */

public class InformationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<InformationData> informations;
    private static InformationsItemClickListener itemClickListener;
    private Activity activity;

    public InformationsAdapter(List<InformationData> list, Activity activity) {
        this.informations = list;
        this.activity = activity;
    }

    public void setItemClickListener(InformationsItemClickListener clickListener) {
        this.itemClickListener = clickListener;
    }

    public List<InformationData> getDataList() {
        return informations;
    }

    @Override
    public int getItemCount() {
        return informations == null ? 0 : informations.size();
    }

    @Override
    public int getItemViewType(int position) {
        return informations.get(position).getType();
    }

    public void setDataList(Collection<InformationData> list) {
        this.informations.clear();
        this.informations.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(Collection<InformationData> list) {
        int lastIndex = this.informations.size();
        if (this.informations.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

    public void delete(int position) {
        if (informations != null) {
            informations.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        if (informations != null) {
            informations.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = null;
        if (i != InformationsItemType.InformationAction.value()) {
            if (i > 0 && i < 10) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.information_baseitem, viewGroup, false);
                return new BaseItemViewHolder(view);
            } else {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.information_error_type, viewGroup, false);
                return new ErrorViewHolder(view);
            }
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.information_actionitem, viewGroup, false);
            return new ActionViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof BaseItemViewHolder) {
            if (informations.get(position).getTitle() != null) {
                ((BaseItemViewHolder) viewHolder).title.setText(informations.get(position).getTitle());
            }
            if (informations.get(position).getContent() != null) {
                ((BaseItemViewHolder) viewHolder).content.setText(informations.get(position).getContent());
            }
        } else {
            ((ActionViewHolder) viewHolder).actionIcon.setImageDrawable(activity.getResources().getDrawable(R.mipmap.ic_launcher));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(informations.get(position));
            }
        });
    }

    public static class BaseItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        TextView data;
        TextView time;

        public BaseItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.information_item_title);
            content = (TextView) itemView.findViewById(R.id.information_item_content);
            data = (TextView) itemView.findViewById(R.id.information_item_createData);
            time = (TextView) itemView.findViewById(R.id.information_item_createTime);
        }
    }

    public static class ErrorViewHolder extends RecyclerView.ViewHolder {
        public ErrorViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class ActionViewHolder extends RecyclerView.ViewHolder {
        private ImageView actionIcon;

        public ActionViewHolder(View itemView) {
            super(itemView);
            actionIcon = (ImageView) itemView.findViewById(R.id.information_action_icon);
        }
    }
}
