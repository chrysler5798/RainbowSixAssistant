package com.khrys.r6assistant.weapons;

/*
 * Created by Chrysler on 3/16/2017.
 * <p>
 * RainbowSixAssistant
*/

public class Weapon
{
    private int idImg;
    private String txtNom;

    public Weapon(int idImg, String txtNom)
    {
        this.idImg = idImg;
        this.txtNom = txtNom;
    }

    int getImg()
    {
        return idImg;
    }

    String getNom()
    {
        return txtNom;
    }
}
