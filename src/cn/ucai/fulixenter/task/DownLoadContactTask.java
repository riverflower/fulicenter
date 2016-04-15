package cn.ucai.fulixenter.task;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Response;

import java.util.HashMap;

import cn.ucai.fulixenter.FuLiCenterApplication;
import cn.ucai.fulixenter.activity.BaseActivity;
import cn.ucai.fulixenter.bean.ContactBean;
import cn.ucai.fulixenter.data.ApiParams;
import cn.ucai.fulixenter.data.GsonRequest;
import cn.ucai.fulixenter.utils.I;

/**
 * Created by sks on 2016/4/7.
 */
public class DownLoadContactTask extends BaseActivity {
    Context mContext;
    String username;
    int pageId;
    int pageSize;
    String path;

    public DownLoadContactTask(Context mContext, String username, int pageId, int pageSize) {
        this.mContext = mContext;
        this.username = username;
        this.pageId = pageId;
        this.pageSize = pageSize;
        initPath();
    }

    private void initPath() {
        try {
            path = new ApiParams()
                    .with(I.User.USER_NAME, username)
                    .with(I.PAGE_ID, pageId + "")
                    .with(I.PAGE_SIZE, pageSize + "")
                    .getRequestUrl(I.REQUEST_DOWNLOAD_CONTACTS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute() {

        executeRequest(new GsonRequest<ContactBean[]>(path,ContactBean[].class,
                responseListener(),errorListener()));
    }

    private Response.Listener<ContactBean[]> responseListener() {
        return new Response.Listener<ContactBean[]>() {
            @Override
            public void onResponse(ContactBean[] contacts) {
                if(contacts==null){
                    return;
                }
                HashMap<Integer, ContactBean> map = new HashMap<Integer, ContactBean>();
                for (ContactBean contact : contacts) {
                    map.put(contact.getCuid(), contact);
                }
                FuLiCenterApplication instance = FuLiCenterApplication.getInstance();
                HashMap<Integer,ContactBean> contactMap=instance.getContacts();
                contactMap.putAll(map);
                Intent intent = new Intent("update_contact");
                Log.e("main","intent="+intent);
                mContext.sendStickyBroadcast(intent);
            }
        };
    }
}
