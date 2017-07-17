package com.khrys.r6assistant.chat;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 7/17/2017 [11:08 AM]
*/

public class Player
{
    String idplayer, name, language;
    int rank, platform, looking;

    public Player()
    {
    }

    public Player(String idplayer, String name, String language,  int rank, int platform, int looking)
    {
        this.idplayer = idplayer;
        this.name = name;
        this.language = language;
        this.rank = rank;
        this.platform = platform;
        this.looking = looking;
    }

    public String getIdplayer()
    {
        return idplayer;
    }

    public void setIdplayer(String idplayer)
    {
        this.idplayer = idplayer;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public int getRank()
    {
        return rank;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }

    public int getPlatform()
    {
        return platform;
    }

    public void setPlatform(int platform)
    {
        this.platform = platform;
    }

    public int getLooking()
    {
        return looking;
    }

    public void setLooking(int looking)
    {
        this.looking = looking;
    }
}
