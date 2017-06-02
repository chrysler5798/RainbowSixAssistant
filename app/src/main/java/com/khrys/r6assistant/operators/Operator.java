package com.khrys.r6assistant.operators;
/*
 * Created by Louis on 31/05/2017.
 * 
 * R6Assistant
*/

public class Operator
{
    private int idImg;
    private String txtNom;

    Operator(int idImg, String txtNom)
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
