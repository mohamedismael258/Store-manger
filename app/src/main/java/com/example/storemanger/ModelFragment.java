package com.example.storemanger;

import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class ModelFragment {
    private Fragment fragment;
    private Integer image;
    private  String title;
    public ModelFragment(Fragment fragment,Integer image,String title)
    {
        this.fragment=fragment;
        this.image=image;
        this.title=title;
    }
    public Fragment getFragment() {
        return fragment;
    }
    public Integer getImage() {
        return image;
    }
    public String getTitle()
    {
        return title;
    }

}
