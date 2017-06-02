package com.khrys.r6assistant.operators;
/*
 * Created by Louis on 31/05/2017.
 * 
 * R6Assistant
*/

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

public class Country implements ParentObject
{
    private List<Object> mChildrenList;
    private String name;
    private int image;

    Country(String name, int image)
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
