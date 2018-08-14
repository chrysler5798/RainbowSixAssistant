package com.khrys.r6assistant.weapons;

/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 3/16/2017 []
*/

public class Weapon
{
    private int imageId;
    private String id, name;

    public Weapon(String id, int imageId, String name)
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