package cn.ucai.fulicenter.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;

import java.util.ArrayList;
import java.util.Arrays;

import cn.ucai.fulicenter.I;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.activity.FuLiCenterMainActivity;
import cn.ucai.fulicenter.adapter.BoutiqueAdapter;
import cn.ucai.fulicenter.adapter.NewGoodsAdapter;
import cn.ucai.fulicenter.bean.Bouique;
import cn.ucai.fulicenter.data.ApiParams;
import cn.ucai.fulicenter.data.GsonRequest;
import cn.ucai.fulicenter.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoutiqueFragment extends Fragment {
    LinearLayoutManager manager;
    RecyclerView mRcv;
    SwipeRefreshLayout msrl;
    TextView mtvHint;

    ArrayList<Bouique> bouiquelist = new ArrayList<>();

    BoutiqueAdapter mAdapter;
    int mAction;
    FuLiCenterMainActivity mcontext;

    public BoutiqueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mcontext = (FuLiCenterMainActivity) getActivity();
        View inflate = inflater.inflate(R.layout.fragment_boutique, container, false);
        initDate();
        initView(inflate);
        setListener();
        return inflate;
    }
    private void setListener() {
//        mRcv.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            int lastposition;
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                mAdapter.setFooterText(getString(R.string.wallet_base_loading));
//                if(newState == RecyclerView.SCROLL_STATE_IDLE&&lastposition==manager.getItemCount()-1&&mAdapter.isMore){
//                    msrl.setRefreshing(true);
//                    pageId ++;
//                    mAction = I.ACTION_PULL_UP;
//                    downloadDate();
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                lastposition=manager.findLastVisibleItemPosition();
//                int topPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
//                msrl.setEnabled(topPosition >= 0);
//                msrl.setEnabled(manager.findFirstCompletelyVisibleItemPosition() == 0);
//            }
//        });
        msrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mtvHint.setVisibility(View.VISIBLE);
                mAction=I.ACTION_PULL_DOWN;
                downloadDate();
                msrl.setEnabled(manager.findFirstCompletelyVisibleItemPosition() == 0);
            }
        });
    }
    private void initView(View layout) {
        mRcv = (RecyclerView) layout.findViewById(R.id.btq_rcv);
        msrl = (SwipeRefreshLayout) layout.findViewById(R.id.btq_srl);
        msrl.setColorSchemeResources(
                android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mtvHint = (TextView) layout.findViewById(R.id.refesh_hint);
        manager = new LinearLayoutManager(mcontext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter = new BoutiqueAdapter(bouiquelist,mcontext);
        mRcv.setLayoutManager(manager);
        mRcv.setAdapter(mAdapter);
    }

    public void initDate(){
        mAction = I.ACTION_DOWNLOAD;
        downloadDate();
    }

    private void downloadDate() {
        try {
            String path = new ApiParams()
                    .getRequestUrl(I.REQUEST_FIND_BOUTIQUES);
            mcontext.executeRequest(new GsonRequest<Bouique[]>(path,Bouique[].class,responseBoutiqueListener(),mcontext.errorListener()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response.Listener<Bouique[]> responseBoutiqueListener() {
        return new Response.Listener<Bouique[]>() {
            @Override
            public void onResponse(Bouique[] bouiques) {
                ArrayList<Bouique> bouiques1 = Utils.array2List(bouiques);
                Log.e("main", Arrays.toString(bouiques));
                for(Bouique bouique:bouiques){
                    Log.e("main", bouique.toString());
                }
                if(mAction==I.ACTION_DOWNLOAD||mAction==I.ACTION_PULL_DOWN){
                    mAdapter.initBoutiquelist(bouiques1);
                }else{
                    mAdapter.addBoutiquelist(bouiques1);
                }
            }
        };
    }

}
