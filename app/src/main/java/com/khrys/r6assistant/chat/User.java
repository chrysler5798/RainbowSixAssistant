package com.khrys.r6assistant.chat;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 7/8/2017 [11:09 AM]
*/

public class User
{
    String pseudo;
    int platform;

    public User()
    {
    }

    public User(String pseudo, int platform)
    {
        this.pseudo = pseudo;
        this.platform = platform;
    }

    public String getPseudo()
    {
        return pseudo;
    }

    public void setPseudo(String pseudo)
    {
        this.pseudo = pseudo;
    }

    public int getPlatform()
    {
        return platform;
    }

    public void setPlatform(int platform)
    {
        this.platform = platform;
    }
}