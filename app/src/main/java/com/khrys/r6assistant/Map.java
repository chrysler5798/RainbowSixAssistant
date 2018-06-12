package com.khrys.r6assistant;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 4/10/2018 [9:23 PM]
*/

import java.io.Serializable;

class Map implements Serializable
{
    private String mapId;
    private String mapName;
    private int mapNameId;
    private int mapImgId;

    Map(String mapId, String mapName, int mapNameId, int mapImgId)
    {
        this.mapId = mapId;
        this.mapName = mapName;
        this.mapNameId = mapNameId;
        this.mapImgId = mapImgId;
    }

    String getMapName()
    {
        return mapName;
    }

    String getMapId()
    {
        return mapId;
    }

    int getMapNameId()
    {
        return mapNameId;
    }

    int getMapImgId()
    {
        return mapImgId;
    }
}
