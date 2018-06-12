package com.khrys.r6assistant.weapons;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

/*
 * Created by Chrysler on 3/16/2017.
 * <p>
 * RainbowSixAssistant
*/

public class Operator implements ParentObject
{
    private List<Object> mChildrenList;
    private String name;
    private int image;

    public Operator(String name, int image)
    {
        setName(name);
        setImage(image);
    }

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }

    public void setName(String title) {
        this.name = title;
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
