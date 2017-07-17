package com.khrys.r6assistant.chat;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 7/10/2017 [11:20 AM]
*/

public class Team
{
    String idplayer, name, language;
    int rank, playerneeded, platform, looking;

    public Team()
    {
    }

    public Team(String idplayer, String name, String language, int platform, int looking, int rank, int playerneeded)
    {
        this.idplayer = idplayer;
        this.name = name;
        this.language = language;
        this.rank = rank;
        this.playerneeded = playerneeded;
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

    public int getPlayerneeded()
    {
        return playerneeded;
    }

    public void setPlayerneeded(int playerneeded)
    {
        this.playerneeded = playerneeded;
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