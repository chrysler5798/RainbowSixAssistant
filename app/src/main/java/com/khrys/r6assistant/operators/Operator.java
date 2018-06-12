package com.khrys.r6assistant.operators;
/*
 * Created by Louis on 31/05/2017.
 * 
 * R6Assistant
*/

public class Operator
{
    private String id;
    private int idImg;
    private String txtNom;

    Operator(String id, int idImg, String txtNom)
    {
        this.id = id;
        this.idImg = idImg;
        this.txtNom = txtNom;
    }

    public String getId()
    {
        return id;
    }

    public int getImg()
    {
        return idImg;
    }

    public String getNom()
    {
        return txtNom;
    }
}
