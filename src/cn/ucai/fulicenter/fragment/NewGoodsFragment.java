package cn.ucai.fulicenter.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;

import java.util.ArrayList;

import cn.ucai.fulicenter.I;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.activity.FuLiCenterMainActivity;
import cn.ucai.fulicenter.activity.MainActivity;
import cn.ucai.fulicenter.adapter.NewGoodsAdapter;
import cn.ucai.fulicenter.bean.NewGoodsBean;
import cn.ucai.fulicenter.data.ApiParams;
import cn.ucai.fulicenter.data.GsonRequest;
import cn.ucai.fulicenter.utils.HttpUtils;
import cn.ucai.fulicenter.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewGoodsFragment extends Fragment {
    NewGoodsAdapter mAdpater;
    RecyclerView.LayoutManager manager;
    RecyclerView mRcv;
    SwipeRefreshLayout msrl;
    TextView mtvHint;
    int pageId = 0;
    final static int PAGE_SIZE=10;

    ArrayList<NewGoodsBean> goodslist = new ArrayList<>();

    FuLiCenterMainActivity mcontext;

    int mAction;

    public NewGoodsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_new_goods, container, false);
        mcontext = (FuLiCenterMainActivity) getActivity();
        initDate();
        initView(layout);
        setListener();
        return layout;
    }

    private void setListener() {
        mRcv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastposition;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE&&lastposition==manager.getItemCount()-1){
                    pageId ++;
                    downloadNewGoods(pageId,PAGE_SIZE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastposition = manager.getItemCount()-1;
                int topPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                msrl.setEnabled(topPosition >= 0);
            }
        });
        msrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mtvHint.setVisibility(View.VISIBLE);
                pageId=0;
                downloadNewGoods(pageId,PAGE_SIZE);
            }
        });
    }


    private void initDate() {
        mAction = I.ACTION_DOWNLOAD;
        downloadNewGoods(pageId,PAGE_SIZE);
    }

    private  void downloadNewGoods(int pageid,int pageSize){
        try {
            String path = new ApiParams()
                    .with(I.CategoryGood.CAT_ID,0+"")
                    .with(I.PAGE_ID,pageid*pageSize+"")
                    .with(I.PAGE_SIZE,pageSize+"")
                    .getRequestUrl(I.REQUEST_DOWNLOAD_NEW_GOOD);
            mcontext.executeRequest(new GsonRequest<NewGoodsBean[]>(path,NewGoodsBean[].class,responseListener(),mcontext.errorListener()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response.Listener<NewGoodsBean[]> responseListener() {
        return new Response.Listener<NewGoodsBean[]>() {
            @Override
            public void onResponse(NewGoodsBean[] newGoodsBeen) {
                ArrayList<NewGoodsBean> newGoodslist = Utils.array2List(newGoodsBeen);
                if( mAction == I.ACTION_DOWNLOAD||mAction==I.ACTION_PULL_DOWN){
                    mAdpater.initGoodslist(newGoodslist);
                }if(mAction == I.ACTION_PULL_UP){
                    mAdpater.addGoodslist(newGoodslist);
                }
            }
        };
    }

    private void initView(View layout) {
        mRcv = (RecyclerView) layout.findViewById(R.id.rcv);
        msrl = (SwipeRefreshLayout) layout.findViewById(R.id.srl);
        mtvHint = (TextView) layout.findViewById(R.id.tv_hint);
        manager = new GridLayoutManager(mcontext,2);
        mAdpater = new NewGoodsAdapter(mcontext,goodslist);
        mRcv.setLayoutManager(manager);
        mRcv.setAdapter(mAdpater);
    }

}

