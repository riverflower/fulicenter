package cn.ucai.fulicenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import cn.ucai.fulicenter.DemoHXSDKHelper;
import cn.ucai.fulicenter.FuLiCenterApplication;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.bean.UserBean;
import cn.ucai.fulicenter.db.UserDao;
import cn.ucai.fulicenter.task.DownLoadContactListTask;
import cn.ucai.fulicenter.task.DownLoadContactTask;

/**
 * 开屏页
 *
 */
public class SplashActivity extends BaseActivity {
	SplashActivity mcontext;
	private RelativeLayout rootLayout;

	private static final int sleepTime = 2000;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.activity_splash);
		super.onCreate(arg0);
		mcontext = this;

		rootLayout = (RelativeLayout) findViewById(R.id.splash_root);
		AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
		animation.setDuration(1500);
		rootLayout.startAnimation(animation);
	}

	@Override
	protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            public void run() {
                if (DemoHXSDKHelper.getInstance().isLogined()) {
                    String username = FuLiCenterApplication.getInstance().getUserName();

                    UserDao dao = new UserDao(mcontext);
                    UserBean userbean = dao.findUserByUserName(username);
                    FuLiCenterApplication.getInstance().setUser(userbean);

                    new DownLoadContactTask(mcontext, username, 0, 20).execute();
                    new DownLoadContactListTask(mcontext, username, 0, 20).execute();

//
//
//					// ** 免登陆情况 加载所有本地群和会话
//					//不是必须的，不加sdk也会自动异步去加载(不会重复加载)；
//					//加上的话保证进了主页面会话和群组都已经load完毕
//					long start = System.currentTimeMillis();
//					long costTime = System.currentTimeMillis() - start;
//					//等待sleeptime时长
//					if (sleepTime - costTime > 0) {
//						try {
//							Thread.sleep(sleepTime - costTime);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//
                    //进入主页面
                    startActivity(new Intent(SplashActivity.this, FuLiCenterMainActivity.class));
                    finish();


                } else {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                    }
                    startActivity(new Intent(SplashActivity.this, FuLiCenterMainActivity.class));
                    finish();
                }

            }
        }).start();
    }
}
