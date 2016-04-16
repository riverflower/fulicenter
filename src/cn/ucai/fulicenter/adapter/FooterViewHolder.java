package cn.ucai.fulicenter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.ucai.fulicenter.R;

/**
 * Created by sks on 2016/4/16.
 */
public class FooterViewHolder extends RecyclerView.ViewHolder {
    TextView footer;

    public FooterViewHolder(View itemView) {
        super(itemView);
        footer = (TextView) itemView.findViewById(R.id.tvfooter);
    }
}

