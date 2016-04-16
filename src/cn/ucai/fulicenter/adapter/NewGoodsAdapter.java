package cn.ucai.fulicenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.bean.NewGoodsBean;
import cn.ucai.fulicenter.utils.ImageUtils;


/**
 * Created by sks on 2016/4/16.
 */
public class NewGoodsAdapter extends RecyclerView.Adapter {
    final static int ITEM_GOODS = 0;
    final static int ITEM_FOOTER = 1;

    NewGoodsHolder goodsHolder;
    FooterViewHolder footerHolder;
    String footerText;

    Context context;
    ArrayList<NewGoodsBean> newGoodsList;

    public void setFooterText(String footer){
        footerText = footer;
        notifyDataSetChanged();
    }

    public NewGoodsAdapter(Context context, ArrayList<NewGoodsBean> newGoodsList) {
        this.context = context;
        this.newGoodsList = newGoodsList;
    }

    public void initGoodslist(ArrayList<NewGoodsBean> list){
        newGoodsList.clear();
        addGoodslist(list);
    }

    public void addGoodslist(ArrayList<NewGoodsBean> list) {
        newGoodsList.addAll(list);
        notifyDataSetChanged();
    }

    public NewGoodsBean getItem(int position) {
        return newGoodsList.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        if(position==getItemCount()){
            return ITEM_FOOTER;
        }else{
            return ITEM_GOODS;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(context);
        if(viewType==ITEM_FOOTER){
            holder = new FooterViewHolder(inflater.inflate(R.layout.item_footer,parent));
        }else{
            holder = new NewGoodsHolder(inflater.inflate(R.layout.item_new_goods,parent));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FooterViewHolder){
            footerHolder = (FooterViewHolder) holder;
            footerHolder.footer.setText(footerText);
            footerHolder.footer.setVisibility(View.VISIBLE);
            return;
        }
        goodsHolder = (NewGoodsHolder) holder;
        NewGoodsBean newGoods = getItem(position);
        goodsHolder.tvTitle.setText(newGoods.getGoodsName());
        goodsHolder.tvPrice.setText(newGoods.getShopPrice());
        ImageUtils.setImage(newGoods.getGoodsImg(),goodsHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return newGoodsList == null ? 0 : newGoodsList.size();
    }


    class NewGoodsHolder extends RecyclerView.ViewHolder {
        NetworkImageView imageView;
        TextView tvTitle;
        TextView tvPrice;

        public NewGoodsHolder(View itemView) {
            super(itemView);
            imageView = (NetworkImageView) itemView.findViewById(R.id.iv_alubms);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }

}
