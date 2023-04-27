package com.example.showadvertisement.banner;

import java.util.ArrayList;

public class ShowList {


    private ArrayList<AdSource> arrayList;

    public ShowList(ArrayList<AdSource> arrayList) {
        this.arrayList = arrayList;
    }

    public void repeatTimes(int times, AdSource adSource) {
        for (int i = 0; i < times; i++) {
            arrayList.add(adSource);
        }
    }

    public void spanTimes(long spanTime, AdSource adSource) {
        adSource.setSpanTime(spanTime);
        arrayList.add(adSource);
    }


    class AdSource {
        private int type; //1图片 2 视频

        private String sourceUrl; //文件地址或资源链接

        private long spanTime; //单位秒

        public AdSource(int type, String sourceUrl) {
            this.type = type;
            this.sourceUrl = sourceUrl;
        }

        public int getType() {
            return type;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSpanTime(long spanTime) {
            this.spanTime = spanTime;
        }

        public long getSpanTime() {
            return spanTime > 0 ? spanTime:0;
        }
    }
}
