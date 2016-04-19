package cn.ucai.fulicenter.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.fragment.BoutiqueFragment;
import cn.ucai.fulicenter.fragment.NewGoodsFragment;

public class FuLiCenterMainActivity extends BaseActivity {
    NewGoodsFragment newGoodsFragment;
    BoutiqueFragment mBoutiqueFragment;
    Fragment[] fragments;

    RadioButton[] group;
    RadioButton newGoods,boutique,category,cart,personalCenter;
    int currentIndex=0;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fulicenter_main);
        initView();
        initFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, newGoodsFragment)
                .commit();
    }

    private void initFragment() {
        newGoodsFragment = new NewGoodsFragment();
        mBoutiqueFragment = new BoutiqueFragment();
        fragments = new Fragment[5];
        fragments[0] = newGoodsFragment;
        fragments[1] = mBoutiqueFragment;
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

    public void setFragmentshow(int index){
        FragmentManager manager = getSupportFragmentManager();
//        ft.show(mBoutiqueFragment).commit();
//        FragmentTransaction ft1 = manager.beginTransaction();
//        ft1.hide(newGoodsFragment).commit();
        for(int i=0;i<fragments.length;i++){
            FragmentTransaction ft = manager.beginTransaction();
            if(i==index){
                ft.show(fragments[i]).commit();
            }
            ft.hide(fragments[i]).commit();
        }
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
        setFragmentshow(index);
    }
}
