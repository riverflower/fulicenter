package cn.ucai.fulicenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import cn.ucai.fulicenter.I;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.activity.GoodsDetailActivity;
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

    public boolean isMore = true;

    public void setMore(boolean more) {
        isMore = more;
    }

    public void setFooterText(String footer){
        footerText = footer;
        notifyDataSetChanged();
    }

    public void sort(int sort){
        switch (sort){
            case I.SORT_BY_ADDTIME_ASC:
                Collections.sort(newGoodsList, new Comparator<NewGoodsBean>() {
                    @Override
                    public int compare(NewGoodsBean lhs, NewGoodsBean rhs) {
                        return (int) (lhs.getAddTime()-rhs.getAddTime());
                    }
                });
                break;
            case I.SORT_BY_ADDTIME_DESC:
                Collections.sort(newGoodsList, new Comparator<NewGoodsBean>() {
                    @Override
                    public int compare(NewGoodsBean lhs, NewGoodsBean rhs) {
                        return (int) (rhs.getAddTime()-lhs.getAddTime());
                    }
                });
                break;
            case I.SORT_BY_PRICE_ASC:
                Collections.sort(newGoodsList, new Comparator<NewGoodsBean>() {
                    @Override
                    public int compare(NewGoodsBean lhs, NewGoodsBean rhs) {
                        return lhs.getShopPrice().compareTo(rhs.getShopPrice());
                    }
                });
                break;
            case I.SORT_BY_PRICE_DESC:
                Collections.sort(newGoodsList, new Comparator<NewGoodsBean>() {
                    @Override
                    public int compare(NewGoodsBean lhs, NewGoodsBean rhs) {
                        return rhs.getShopPrice().compareTo(lhs.getShopPrice());
                    }
                });
                break;
        }
        notifyDataSetChanged();
    }

    public NewGoodsAdapter(Context context, ArrayList<NewGoodsBean> newGoodsList) {
        this.context = context;
        this.newGoodsList = newGoodsList;
    }

    public void initGoodslist(ArrayList<NewGoodsBean> list){
        if(!(newGoodsList==null)&&!newGoodsList.isEmpty()){
            newGoodsList.clear();
        }
        addGoodslist(list);
    }

    public void addGoodslist(ArrayList<NewGoodsBean> list) {
        newGoodsList.addAll(list);
        notifyDataSetChanged();
    }

    public NewGoodsBean getItem(int position) {
        Log.e("main",newGoodsList.get(position).toString());
        return newGoodsList.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        if(position==getItemCount()-1){
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
            holder = new FooterViewHolder(inflater.inflate(R.layout.item_footer,parent,false));
        }else{
            holder = new NewGoodsHolder(inflater.inflate(R.layout.item_new_goods,parent,false));
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
        final NewGoodsBean newGoods = getItem(position);
        goodsHolder.tvTitle.setText(newGoods.getGoodsName());
        goodsHolder.tvPrice.setText(newGoods.getShopPrice());
        ImageUtils.setImage(newGoods.getGoodsImg(),goodsHolder.imageView);
        goodsHolder.layout_newGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsDetailActivity.class);
                intent.putExtra("goodsId",newGoods.getGoodsId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.e("main",newGoodsList.size()+"");
        return newGoodsList == null ? 0 : newGoodsList.size()+1;
    }


    class NewGoodsHolder extends RecyclerView.ViewHolder {
        NetworkImageView imageView;
        TextView tvTitle;
        TextView tvPrice;
        View layout_newGoods;

        public NewGoodsHolder(View itemView) {
            super(itemView);
            imageView = (NetworkImageView) itemView.findViewById(R.id.iv_alubms);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            layout_newGoods = itemView.findViewById(R.id.layout_newGoods);
        }
    }

}
