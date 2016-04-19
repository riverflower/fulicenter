package cn.ucai.fulicenter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.NetworkImageView;

import cn.ucai.fulicenter.D;
import cn.ucai.fulicenter.I;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.bean.Albums;
import cn.ucai.fulicenter.bean.GoodDetailsBean;
import cn.ucai.fulicenter.data.ApiParams;
import cn.ucai.fulicenter.data.GsonRequest;
import cn.ucai.fulicenter.utils.ImageUtils;
import cn.ucai.fulicenter.widget.FlowIndicator;
import cn.ucai.fulicenter.widget.SlideAutoLoopView;

public class GoodsDetailActivity extends BaseActivity {
    GoodDetailsBean goodDetails;
    ImageView iv_back;
    ImageView iv_cart;
    ImageView iv_collect;
    ImageView iv_share;
    TextView English_name,name,price;
    SlideAutoLoopView mslv;
    FlowIndicator fic;
    WebView wv_detail;

    GoodsDetailActivity mContext;

    int mCurrnetColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        int goodsId = getIntent().getIntExtra("goodsId",0);
        mContext =this;
        Log.e("main",goodsId+"");
        initView();
        initDate(goodsId);

    }

    private void initDate(int goodsId) {
        downloadGoodDetail(goodsId);
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.back);
        iv_cart = (ImageView) findViewById(R.id.add_cart);
        iv_collect = (ImageView) findViewById(R.id.collection);
        iv_share = (ImageView) findViewById(R.id.iv_share);
        English_name = (TextView) findViewById(R.id.English_name);
        name = (TextView) findViewById(R.id.name);
        price = (TextView) findViewById(R.id.tv_price);
        mslv = (SlideAutoLoopView) findViewById(R.id.slv_image);
        fic = (FlowIndicator) findViewById(R.id.fic_image);
        wv_detail = (WebView) findViewById(R.id.wv_detail);
        WebSettings webSetting = wv_detail.getSettings();
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSetting.setBuiltInZoomControls(true);
    }

    public void downloadGoodDetail(int goodsId) {
        try {
            String path = new ApiParams()
                    .with(I.CategoryGood.GOODS_ID,goodsId+"")
                    .getRequestUrl(I.REQUEST_FIND_GOOD_DETAILS);
            Log.e("main", path);
            executeRequest(new GsonRequest<GoodDetailsBean>(path,GoodDetailsBean.class,
                    responseDownloadGoodDetail(),errorListener()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response.Listener<GoodDetailsBean> responseDownloadGoodDetail() {
        return new Response.Listener<GoodDetailsBean>() {
            @Override
            public void onResponse(GoodDetailsBean goodDetailsBean) {
                if(goodDetailsBean!=null){
                    Log.e("main",goodDetailsBean.toString());
                    goodDetails = goodDetailsBean;
                    iv_back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                    English_name.setText(goodDetails.getGoodsEnglishName());
                    name.setText(goodDetails.getGoodsName());
                    price.setText(goodDetails.getShopPrice());
                    wv_detail.loadDataWithBaseURL(null,goodDetails.getGoodsBrief().trim(), D.TEXT_HTML,D.UTF_8,null);

                    initColorsBanner();
                }else{
                    Toast.makeText(mContext,"下载数据失败",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        };
    }

    private void initColorsBanner() {
        updateColor(0);
        for(int i=0;i<goodDetails.getProperties().length;i++){
            mCurrnetColor = i;
            View layout = View.inflate(mContext,R.layout.layout_property_color,null);
            final NetworkImageView ivColor = (NetworkImageView) layout.findViewById(R.id.ivColorItem);
            String colorImg = goodDetails.getProperties()[i].getColorImg();
            if(colorImg.isEmpty()){
                continue;
            }
            try {
                ImageUtils.setGoodDetailThumb(colorImg,ivColor);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void updateColor(int i) {
        Albums[] albums = goodDetails.getProperties()[i].getAlbums();
        String[] albumImgUrl = new String[albums.length];
        for(int j=0;j<albumImgUrl.length;j++) {
            albumImgUrl[j] = albums[j].getImgUrl();
        }
        mslv.startPlayLoop(fic,albumImgUrl,albumImgUrl.length);
    }

}
