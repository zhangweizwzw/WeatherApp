package com.stu.zw.pot.weatherapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stu.zw.pot.weatherapp.fragment.MeFragment;
import com.stu.zw.pot.weatherapp.fragment.WeatherFragment;


public class MainActivity extends FragmentActivity implements OnClickListener{

    //定义3个Fragment的对象
    private WeatherFragment weatherFragment;
    private MeFragment settingFragment;
    //帧布局对象,就是用来存放Fragment的容器
    private FrameLayout flayout;
    //定义底部导航栏的三个布局
    private RelativeLayout weather_layout;
    private RelativeLayout setting_layout;
    //定义底部导航栏中的ImageView与TextView
    private ImageView weather_image;
    private ImageView setting_image;
    private TextView weather_text;
    private TextView setting_text;
    //定义要用的颜色值
    private int whirt = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int blue =0xFF0AB2FB;
    //定义FragmentManager对象
    FragmentManager fManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager = getSupportFragmentManager();

        initViews();
        setChioceItem(0);
    }


    //完成组件的初始化
    public void initViews() {
        weather_image = (ImageView) findViewById(R.id.weather_image);
        setting_image = (ImageView) findViewById(R.id.setting_image);
        weather_text = (TextView) findViewById(R.id.weather_text);
        setting_text = (TextView) findViewById(R.id.setting_text);

        weather_layout = (RelativeLayout) findViewById(R.id.weather_layout);
        setting_layout = (RelativeLayout) findViewById(R.id.setting_layout);
        weather_layout.setOnClickListener(this);
        setting_layout.setOnClickListener(this);
    }

    //重写onClick事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.weather_layout:
                setChioceItem(0);
                break;
            case R.id.setting_layout:
                setChioceItem(1);
                break;
            default:
                break;
        }

    }


    //定义一个选中一个item后的处理
    public void setChioceItem(int index) {
        //重置选项+隐藏所有Fragment
        FragmentTransaction transaction = fManager.beginTransaction();
        clearChioce();
        hideFragments(transaction);
        switch (index) {
            case 0:
                weather_image.setImageResource(R.mipmap.ic_tabbar_course_pressed);
                weather_text.setTextColor(blue);
                weather_layout.setBackgroundColor(getResources().getColor(R.color.tabbackground));
                if (weatherFragment == null) {
                    // 如果weatherFragment为空，则创建一个并添加到界面上
                    weatherFragment = new WeatherFragment();
                    transaction.add(R.id.content, weatherFragment);
                } else {
                    // 如果weatherFragment不为空，则直接将它显示出来
                    transaction.show(weatherFragment);
                }
                break;

            case 1:
                setting_image.setImageResource(R.mipmap.ic_tabbar_found_pressed);
                setting_text.setTextColor(blue);
                setting_layout.setBackgroundColor(getResources().getColor(R.color.tabbackground));
                if (settingFragment == null) {
                    // 如果settingFragment为空，则创建一个并添加到界面上
                    settingFragment = new MeFragment();
                    transaction.add(R.id.content, settingFragment);
                } else {
                    // 如果settingFragment不为空，则直接将它显示出来
                    transaction.show(settingFragment);
                }
                break;
        }
        transaction.commit();
    }

    //隐藏所有的Fragment,避免fragment混乱
    private void hideFragments(FragmentTransaction transaction) {
        if (weatherFragment != null) {
            transaction.hide(weatherFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }


    //定义一个重置所有选项的方法
    public void clearChioce() {
        weather_image.setImageResource(R.mipmap.ic_tabbar_course_normal);
        weather_layout.setBackgroundColor(whirt);
        weather_text.setTextColor(gray);
        setting_image.setImageResource(R.mipmap.ic_tabbar_found_normal);
        setting_layout.setBackgroundColor(whirt);
        setting_text.setTextColor(gray);
    }

}
