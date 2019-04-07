package com.example.ui;

public class ItemBean {
    //都为公有，可以访问的
    public int ItemImageId;
    public String ItemTitle;
    //创建一个构造方法
    ItemBean(int itemImageId, String itemTitle){
        ItemImageId=itemImageId;
        ItemTitle=itemTitle;
    }
}
