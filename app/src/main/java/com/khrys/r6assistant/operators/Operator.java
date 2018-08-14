package com.khrys.r6assistant.operators;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 05/31/2017 []
*/

public class Operator
{
    private String id;
    private int imageId;
    private String name;

    Operator(String id, int imageId, String name)
    {
        this.id = id;
        this.imageId = imageId;
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public int getImageId()
    {
        return imageId;
    }

    public String getName()
    {
        return name;
    }
}
