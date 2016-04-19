package cn.ucai.fulicenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import cn.ucai.fulicenter.I;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.activity.GoodsDetailActivity;
import cn.ucai.fulicenter.bean.Bouique;
import cn.ucai.fulicenter.utils.ImageUtils;

/**
 * Created by sks on 2016/4/19.
 */
public class BoutiqueAdapter extends RecyclerView.Adapter {
    ArrayList<Bouique> bouiquelist;
    Context context;

    public boolean isMore = true;
    String footerText;
    BoutiqueHolder boutiqueHolder;
    FooterViewHolder footerholder;

    public void setMore(boolean more) {
        isMore = more;
    }

    public void setFooterText(String footerText) {
        this.footerText = footerText;
        notifyDataSetChanged();
    }

    public BoutiqueAdapter(ArrayList<Bouique> bouiquelist, Context context) {
        this.bouiquelist = bouiquelist;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        LayoutInflater inflater = LayoutInflater.from(context);
        if(viewType==I.TYPE_FOOTER){
            holder = new FooterViewHolder(inflater.inflate(R.layout.item_footer, parent, false));
        }else {
            holder = new BoutiqueHolder(inflater.inflate(R.layout.item_boutique, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FooterViewHolder){
            footerholder = (FooterViewHolder) holder;
            footerholder.footer.setVisibility(View.VISIBLE);
            return;
        }
        boutiqueHolder = (BoutiqueHolder) holder;
        final Bouique bouique = bouiquelist.get(position);
        boutiqueHolder.tv_title.setText(bouique.getTitle());
        boutiqueHolder.tv_name.setText(bouique.getName());
        boutiqueHolder.tv_desc.setText(bouique.getDescription());
        ImageUtils.setImage(bouique.getImageurl(),boutiqueHolder.niv_image);
        boutiqueHolder.layout_boutique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsDetailActivity.class).putExtra("id",bouique.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if(position==getItemCount()-1){
            return I.TYPE_FOOTER;
        }else{
            return I.TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return bouiquelist==null?0:bouiquelist.size()+1;
    }

    public void initBoutiquelist(ArrayList<Bouique> bouiques1) {
        if(!(bouiquelist==null)||!bouiquelist.isEmpty()){
            bouiquelist.clear();
        }
        addBoutiquelist(bouiques1);
    }

    public void addBoutiquelist(ArrayList<Bouique> bouiques1) {
        bouiquelist.addAll(bouiques1);
        notifyDataSetChanged();
    }

    class BoutiqueHolder extends RecyclerView.ViewHolder{
        NetworkImageView niv_image;
        TextView tv_title;
        TextView tv_name;
        TextView tv_desc;
        LinearLayout layout_boutique;
        public BoutiqueHolder(View itemView) {
            super(itemView);
            niv_image = (NetworkImageView) itemView.findViewById(R.id.niv_image);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_desc = (TextView) itemView.findViewById(R.id.tv_desc);
            layout_boutique = (LinearLayout) itemView.findViewById(R.id.layout_boutique);
        }
    }
}
