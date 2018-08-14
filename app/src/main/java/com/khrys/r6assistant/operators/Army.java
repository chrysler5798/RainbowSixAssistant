package com.khrys.r6assistant.operators;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 05/31/2017 []
*/

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

public class Army implements ParentObject
{
    private List<Object> mChildrenList;
    private String name;
    private int imageId;

    Army(String name, int imageId)
    {
        this.name = name;
        this.imageId = imageId;
    }

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return imageId;
    }
}