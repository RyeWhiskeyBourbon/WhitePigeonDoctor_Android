package com.mydemo.zhongyujiaoyu.model;


import com.mydemo.zhongyujiaoyu.fragment.MeFragment;
import com.mydemo.zhongyujiaoyu.fragment.GetDoctorFragment;
import com.mydemo.zhongyujiaoyu.fragment.InformationFragment;
import com.mydemo.zhongyujiaoyu.fragment.CommunityFragment;
import com.mydemo.zhongyujiaoyu.R;

public class TabEntity {

    public static final TabEntity one = new TabEntity(R.string.one, R.drawable.btn_one,
            InformationFragment.class);

    public static final TabEntity two = new TabEntity(R.string.two, R.drawable.btn_two,
            GetDoctorFragment.class);

    public static final TabEntity three = new TabEntity(R.string.three, R.drawable.btn_three,
            CommunityFragment.class);

    public static final TabEntity four = new TabEntity(R.string.four, R.drawable.btn_four,
            MeFragment.class);
//
//    public static final TabEntity five = new TabEntity(R.string.five, R.drawable.btn_five,
//            MyFragment.class);

    private int stringId;
    private int imageId;
    private Class<?> clss;

    public TabEntity(int sID, int iId, Class<?> cCalss) {
        this.stringId = sID;
        this.imageId = iId;
        this.clss = cCalss;
    }

    public int getStringId() {
        return stringId;
    }

    public void setStringId(int stringId) {
        this.stringId = stringId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Class<?> getClss() {
        return clss;
    }

    public void setClss(Class<?> clss) {
        this.clss = clss;
    }
}
