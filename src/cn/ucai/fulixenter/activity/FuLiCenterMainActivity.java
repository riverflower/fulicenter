package cn.ucai.fulixenter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import cn.ucai.fulixenter.R;

public class FuLiCenterMainActivity extends Activity {

    RadioButton[] group;
    RadioButton newGoods,boutique,category,cart,personalCenter;
    int currentIndex=0;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fulicenter_main);
        initView();
    }

    private void initView() {
        newGoods = (RadioButton) findViewById(R.id.btn_new_goods);
        boutique = (RadioButton) findViewById(R.id.boutuique);
        category = (RadioButton) findViewById(R.id.category);
        cart = (RadioButton) findViewById(R.id.cart);
        personalCenter = (RadioButton) findViewById(R.id.personal_center);
        group = new RadioButton[5];
        group[0] = newGoods;
        group[1] = boutique;
        group[2] = category;
        group[3] = cart;
        group[4] = personalCenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(index<0){
            index = 0;
        }
        setDefaultChecked(index);
    }

    public void setDefaultChecked(int index){
       for(int i=0;i<group.length;i++) {
           if(i==index){
               group[i].setChecked(true);
           }else {
               group[i].setChecked(false);
           }
       }
    }

    public void onCheckedChange(View view) {
        switch (view.getId()){
            case R.id.btn_new_goods:
                index = 0;
                break;
            case R.id.boutuique:
                index = 1;
                break;
            case R.id.category:
                index = 2;
                break;
            case R.id.cart:
                index = 3;
                break;
            case R.id.personal_center:
                index = 4;
                break;
        }
        if(currentIndex!=index){
            currentIndex = index;
        }
        setDefaultChecked(index);
    }
}
