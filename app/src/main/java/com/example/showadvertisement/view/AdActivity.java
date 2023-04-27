package com.example.showadvertisement.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.showadvertisement.R;
import com.example.showadvertisement.banner.ChangeBanner;
import com.example.showadvertisement.banner.adapter.MediaVideoBannerAdapter;
import com.example.showadvertisement.banner.manager.BannerVideoManager;
import com.example.showadvertisement.bean.ResourceBean;
import com.example.showadvertisement.bean.DataBean;
import com.example.showadvertisement.bean.SettingTab;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnPageChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Unbinder;

public class AdActivity extends FragmentActivity implements OnPageChangeListener{

    final String TAG = "AdActivity.class";
    private List<ResourceBean> dataList;
    private MediaVideoBannerAdapter mAdapter;
    private BannerVideoManager mBannerVideoManager;

    private Unbinder unbinder;

    private String srcPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        srcPath = Environment.getExternalStorageDirectory().toString();
        initDataList();
        initBanner();
        //禁止锁屏
        KeyguardManager keyguardManager = (KeyguardManager)this.getSystemService(Activity.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
        lock.disableKeyguard();//关闭系统锁屏

    }

    private void initDataList() {
        dataList = new ArrayList<>();
        ResourceBean bean = new ResourceBean();
        bean.setType(1);
        bean.setUrl("https://model-player.oss-cn-beijing.aliyuncs.com/bg_banner_pink.png");
        dataList.add(bean);
        bean = new ResourceBean();
        bean.setType(1);
        bean.setUrl("https://model-player.oss-cn-beijing.aliyuncs.com/bg_banner_pink.png");
        dataList.add(bean);
        bean = new ResourceBean();
        String srcPath = Environment.getExternalStorageDirectory().getPath() + "/advertise/" + "big_buck_bunny.mp4";
        bean.setUrl(srcPath);
        bean.setType(2);
        dataList.add(bean);

    }

    private void initBanner() {
        ChangeBanner banner = findViewById(R.id.ad_banner);
        mAdapter = new MediaVideoBannerAdapter(this, dataList);
        banner.isAutoLoop(false);

        banner.setAdapter(mAdapter)
                .setIndicator(new CircleIndicator(this))
                .setIndicatorGravity(IndicatorConfig.Direction.CENTER);

        mBannerVideoManager = new BannerVideoManager(this, banner, mAdapter, dataList);
        mBannerVideoManager.setPageChangeMillis(5000);
        mBannerVideoManager.setVideoPlayLoadWait(500);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_DOWN://向下
                // TODO: 2023/4/12
                Toast.makeText(this,"弹出设置",Toast.LENGTH_SHORT).show();
                showBottomSetting(this);
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void updateDataSource() {
        List<DataBean> dataBeans = DataBean.getTestData3();
        dataList.clear();
        for (DataBean dataBean:
             dataBeans) {
            dataList.add(new ResourceBean(1,dataBean.imageUrl));
        }
        mAdapter.updateDataList(dataList);
    }

    private void showBottomSetting(Activity context) {
        final MyDialog bottomDialog = new MyDialog(this);
        RecyclerView recyclerView = new RecyclerView(this);
        ArrayList<SettingTab> settingTabs = new ArrayList<>();
        settingTabs.add(new SettingTab(R.drawable.setting,"1设置"));
        settingTabs.add(new SettingTab(R.drawable.setting,"2设置"));
        DialogAdapter dialogAdapter = new DialogAdapter(this,settingTabs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(dialogAdapter);
        bottomDialog.setContentView(recyclerView);
        Window window = bottomDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        bottomDialog.show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 自定义布局，下面是常见的图片样式，更多实现可以看demo，可以自己随意发挥
     */
    public class ImageAdapter extends BannerAdapter<DataBean, ImageAdapter.BannerViewHolder> {

        public ImageAdapter(List<DataBean> mDataS) {
            //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
            super(mDataS);
        }

        //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
        @Override
        public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(parent.getContext());
            //注意，必须设置为match_parent，这个是viewpager2强制要求的
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return new BannerViewHolder(imageView);
        }

        @Override
        public void onBindView(BannerViewHolder holder, DataBean data, final int position, int size) {
            //holder.imageView.setImageResource(data.imageRes);
            Glide.with(holder.imageView.getContext()).load((String)data.imageUrl).into(holder.imageView);
            holder.itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //点击事件
                            Toast.makeText(v.getContext(),"点击了"+position,Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }

        class BannerViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public BannerViewHolder(@NonNull ImageView view) {
                super(view);
                this.imageView = view;
            }
        }
    }


    public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.TabHolder> {

        private Context context;
        private ArrayList<SettingTab> settingTabs;

        public DialogAdapter(Context context,ArrayList<SettingTab> settingTabs) {
            this.context = context;
            this.settingTabs = settingTabs;
        }

        @NonNull
        @Override
        public DialogAdapter.TabHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).
                    inflate(R.layout.item_setting_tab,parent,false);
            return new TabHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DialogAdapter.TabHolder holder, int position) {
            if (settingTabs.size() > 0) {
                holder.imageView.setBackgroundResource(R.drawable.setting);
                holder.textView.setText(settingTabs.get(position).getTextContent());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"点击" + holder.getAdapterPosition(),Toast.LENGTH_SHORT).show();
                        updateDataSource();
                    }
                });
                holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        Toast.makeText(context,"选择" + holder.getAdapterPosition(),Toast.LENGTH_SHORT).show();
                        if (hasFocus) {
                            holder.textView.setTextColor(getResources().getColor(R.color.search_opaque));

                        } else {
                            holder.textView.setTextColor(getResources().getColor(R.color.background_gradient_end));

                        }
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return settingTabs.size();
        }

        class TabHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;

            public TabHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.tab_image);
                textView = itemView.findViewById(R.id.tab_content);
            }
        }
    }

    public class MyDialog extends Dialog{

        private  Timer mTimer;           // 计时器，每1秒执行一次任务
        private  long mLastActionTime;   // 上一次操作时间

        public MyDialog(@NonNull Context context) {
            super(context);
            startTimer();
        }
        /**
         *  启动计时任务
         */
        private void startTimer() {
            mTimer = new Timer();
            // 计时任务，判断是否未操作时间到达ns
            MyTimerTask mTimerTask = new MyTimerTask();
            // 初始化上次操作时间为登录成功的时间
            mLastActionTime = System.currentTimeMillis();
            // 每过1s检查一次
            mTimer.schedule(mTimerTask, 0, 1000);
        }

        /**
         * 点击屏幕时重载计时
         * @param event
         * @return
         */
        @Override
        public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
            mLastActionTime = System.currentTimeMillis();
            return super.onKeyDown(keyCode, event);
        }

        /**
         * 停止计时
         */
        public void stopTimer() {
            mTimer.cancel();
        }

        /**
         * 计时任务管理
         */
        private class MyTimerTask extends TimerTask {
            @Override
            public void run() {
                // 1分钟未操作停止计时并退出登录 60s*1min
                if (System.currentTimeMillis() - mLastActionTime > 1000 * 10 * 1) {
                    stopTimer();// 停止计时任务
                    dismiss();  // 关闭弹窗
                }
                Log.d(TAG, "run: " + (System.currentTimeMillis() - mLastActionTime) / 1000);
            }
        }

        @Override
        public void dismiss() {
            super.dismiss();
            stopTimer();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBannerVideoManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBannerVideoManager.onPause();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mBannerVideoManager.onDetachedFromWindow();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbinder.unbind();
    }
}