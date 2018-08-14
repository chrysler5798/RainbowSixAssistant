package com.khrys.r6assistant.weapons;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 3/16/2017 []
*/

public class Operator implements ParentObject
{
    private List<Object> mChildrenList;
    private String id, name;
    private int image;

    public Operator(String id, String name, int image)
    {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }

    public String getId()
    {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }
}
