package com.example.showadvertisement.bean;


import java.util.ArrayList;
import java.util.List;

public class DataBean {
    public Integer imageRes;
    public String imageUrl;
    public String title;
    public int viewType;

    public DataBean(Integer imageRes, String title, int viewType) {
        this.imageRes = imageRes;
        this.title = title;
        this.viewType = viewType;
    }

    public DataBean(String imageUrl, String title, int viewType) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.viewType = viewType;
    }

    public static List<DataBean> getTestData3() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean("https://imgs.aixifan.com/Flbdj6o4eyYWLb2mnG41JoR8KMnH", null, 1));
        list.add(new DataBean("https://imgs.aixifan.com/FkLmqRl4egVQGqwhAk7Vii_QBe-5", null, 1));
        list.add(new DataBean("https://imgs.aixifan.com/FkDIju10GasyPs643nwk6OPzUSKp", null, 1));
        list.add(new DataBean("https://imgs.aixifan.com/Frzs4t6YIpbaw6qnJoB1nbqe7tAE", null, 1));
        list.add(new DataBean("https://pica.zhimg.com/v2-8c6500b908db2f37cf427596e9cfdcf0_r.jpg?source=1940ef5c", null, 1));
        return list;
    }

}

