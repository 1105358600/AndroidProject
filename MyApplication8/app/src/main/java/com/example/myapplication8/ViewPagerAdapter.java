package com.example.myapplication8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private  int[] datas;

    public ViewPagerAdapter(Context context,int[] datas)
    {
        this.context=context;
        this.datas=datas;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View layout=layoutInflater.inflate(R.layout.viewpager_item,null);
        ImageView iv= layout.findViewById(R.id.imageView);
        iv.setImageResource(datas[position]);
        container.addView(layout);
        return layout;

    }
}
