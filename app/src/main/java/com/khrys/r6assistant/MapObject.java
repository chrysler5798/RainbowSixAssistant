package com.khrys.r6assistant;

/*
 * Created by Louis on 26/03/2017.
 * R6Assistant
*/

public class MapObject {

    private String nom;
    private int camera;

    MapObject(String nom, int camera)
    {
        this.nom = nom;
        this.camera = camera;
    }

    public String getNom() {
        return nom;
    }

    public int getCamera() {
        return camera;
    }
}