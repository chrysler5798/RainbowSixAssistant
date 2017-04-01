package com.khrys.r6assistant;

import java.util.ArrayList;

/*
 * Created by Chrysler on 11/30/2016.
 * <p>
 * RainbowSixPartner
*/

class MapSwitch
{

    private ArrayList<Integer> pics, poscam, posmap;
    private int pos, posplanmap;

    MapSwitch(ArrayList<Integer> posmap, int posplanmap)
    {
        this.posmap = posmap;
        this.posplanmap = posplanmap;
    }

    ArrayList<Integer> SwitchPosPlansWithPos()
    {
        switch (posplanmap)
        {
            case 0:
                posmap.add(-1);
                posmap.add(2);
                posmap.add(R.drawable.map_bank__1);
                posmap.add(R.drawable.map_bank_f0);
                posmap.add(R.drawable.map_bank_f1);
                posmap.add(R.drawable.map_bank_f2);
                break;

            case 1:
                posmap.add(-1);
                posmap.add(2);
                posmap.add(R.drawable.map_chalet__1);
                posmap.add(R.drawable.map_chalet_f0);
                posmap.add(R.drawable.map_chalet_f1);
                posmap.add(R.drawable.map_chalet_f2);
                break;

            case 2:
                posmap.add(-1);
                posmap.add(2);
                posmap.add(R.drawable.map_clubhouse__1);
                posmap.add(R.drawable.map_clubhouse_f0);
                posmap.add(R.drawable.map_clubhouse_f1);
                posmap.add(R.drawable.map_clubhouse_f2);
                break;

            case 3:
                posmap.add(-1);
                posmap.add(2);
                posmap.add(R.drawable.map_consulate__1);
                posmap.add(R.drawable.map_consulate_f0);
                posmap.add(R.drawable.map_consulate_f1);
                posmap.add(R.drawable.map_consulate_f2);
                break;

            case 4:
                posmap.add(-1);
                posmap.add(3);
                posmap.add(R.drawable.map_hereford__1);
                posmap.add(R.drawable.map_hereford_f0);
                posmap.add(R.drawable.map_hereford_f1);
                posmap.add(R.drawable.map_hereford_f2);
                posmap.add(R.drawable.map_hereford_f3);
                break;

            case 5:
                posmap.add(-1);
                posmap.add(2);
                posmap.add(R.drawable.map_house__1);
                posmap.add(R.drawable.map_house_f0);
                posmap.add(R.drawable.map_house_f1);
                posmap.add(R.drawable.map_house_f2);
                break;

            case 6:
                posmap.add(0);
                posmap.add(3);
                posmap.add(R.drawable.map_kafe_f0);
                posmap.add(R.drawable.map_kafe_f1);
                posmap.add(R.drawable.map_kafe_f2);
                posmap.add(R.drawable.map_kafe_f3);
                break;

            case 7:
                posmap.add(0);
                posmap.add(3);
                posmap.add(R.drawable.map_kanal_f0);
                posmap.add(R.drawable.map_kanal_f1);
                posmap.add(R.drawable.map_kanal_f2);
                posmap.add(R.drawable.map_kanal_f3);
                break;

            case 8:
                posmap.add(-1);
                posmap.add(2);
                posmap.add(R.drawable.map_oregon__1);
                posmap.add(R.drawable.map_oregon_f0);
                posmap.add(R.drawable.map_oregon_f1);
                posmap.add(R.drawable.map_oregon_f2);
                break;

            case 9:
                posmap.add(-1);
                posmap.add(2);
                posmap.add(R.drawable.map_plane__1);
                posmap.add(R.drawable.map_plane_f0);
                posmap.add(R.drawable.map_plane_f1);
                posmap.add(R.drawable.map_plane_f2);
                break;

            case 10:
                posmap.add(-1);
                posmap.add(3);
                posmap.add(R.drawable.map_yatch__1);
                posmap.add(R.drawable.map_yatch_f0);
                posmap.add(R.drawable.map_yatch_f1);
                posmap.add(R.drawable.map_yatch_f2);
                posmap.add(R.drawable.map_yatch_f3);
                break;

            case 11:
                posmap.add(0);
                posmap.add(2);
                posmap.add(R.drawable.map_border_f0);
                posmap.add(R.drawable.map_border_f1);
                posmap.add(R.drawable.map_border_f2);
                break;

            case 12:
                posmap.add(0);
                posmap.add(2);
                posmap.add(R.drawable.map_favela_f0);
                posmap.add(R.drawable.map_favela_f1);
                posmap.add(R.drawable.map_favela_f2);
                break;

            case 13:
                posmap.add(0);
                posmap.add(3);
                posmap.add(R.drawable.map_sky_f0);
                posmap.add(R.drawable.map_sky_f1);
                posmap.add(R.drawable.map_sky_f2);
                posmap.add(R.drawable.map_sky_f3);
                break;
        }

        return posmap;
    }

    ArrayList<Integer> SwitchPosPlans()
    {
        switch (posplanmap)
        {
            case 0:
                posmap.add(-1);
                posmap.add(2);
                posmap.add(R.drawable.map_bank__1_pos);
                posmap.add(R.drawable.map_bank_f0_pos);
                posmap.add(R.drawable.map_bank_f1_pos);
                posmap.add(R.drawable.map_bank_f2_pos);
                break;

            case 1:
                posmap.add(-1);
                posmap.add(2);
                posmap.add(R.drawable.map_chalet__1_pos);
                posmap.add(R.drawable.map_chalet_f0_pos);
                posmap.add(R.drawable.map_chalet_f1_pos);
                posmap.add(R.drawable.map_chalet_f2_pos);
                break;

            case 2:
                posmap.add(-1);
                posmap.add(2);
                posmap.add(R.drawable.map_clubhouse__1_pos);
                posmap.add(R.drawable.map_clubhouse_f0_pos);
                posmap.add(R.drawable.map_clubhouse_f1_pos);
                posmap.add(R.drawable.map_clubhouse_f2_pos);
                break;

            case 3:
                posmap.add(-1);
                posmap.add(2);
                posmap.add(R.drawable.map_consulate__1_pos);
                posmap.add(R.drawable.map_consulate_f0_pos);
                posmap.add(R.drawable.map_consulate_f1_pos);
                posmap.add(R.drawable.map_consulate_f2_pos);
                break;

            case 4:
                posmap.add(-1);
                posmap.add(3);
                posmap.add(R.drawable.map_hereford__1_pos);
                posmap.add(R.drawable.map_hereford_f0_pos);
                posmap.add(R.drawable.map_hereford_f1_pos);
                posmap.add(R.drawable.map_hereford_f2_pos);
                posmap.add(R.drawable.map_hereford_f3_pos);
                break;

            case 5:
                posmap.add(-1);
                posmap.add(2);
                posmap.add(R.drawable.map_house__1_pos);
                posmap.add(R.drawable.map_house_f0_pos);
                posmap.add(R.drawable.map_house_f1_pos);
                posmap.add(R.drawable.map_house_f2_pos);
                break;

            case 6:
                posmap.add(0);
                posmap.add(3);
                posmap.add(R.drawable.map_kafe_f0_pos);
                posmap.add(R.drawable.map_kafe_f1_pos);
                posmap.add(R.drawable.map_kafe_f2_pos);
                posmap.add(R.drawable.map_kafe_f3_pos);
                break;

            case 7:
                posmap.add(0);
                posmap.add(3);
                posmap.add(R.drawable.map_kanal_f0_pos);
                posmap.add(R.drawable.map_kanal_f1_pos);
                posmap.add(R.drawable.map_kanal_f2_pos);
                posmap.add(R.drawable.map_kanal_f3_pos);
                break;

            case 8:
                posmap.add(-1);
                posmap.add(2);
                posmap.add(R.drawable.map_oregon__1_pos);
                posmap.add(R.drawable.map_oregon_f0_pos);
                posmap.add(R.drawable.map_oregon_f1_pos);
                posmap.add(R.drawable.map_oregon_f2_pos);
                break;

            case 9:
                posmap.add(-1);
                posmap.add(2);
                posmap.add(R.drawable.map_plane__1_pos);
                posmap.add(R.drawable.map_plane_f0_pos);
                posmap.add(R.drawable.map_plane_f1_pos);
                posmap.add(R.drawable.map_plane_f2_pos);
                break;

            case 10:
                posmap.add(0);
                posmap.add(2);
                posmap.add(R.drawable.map_favela_f0_pos);
                posmap.add(R.drawable.map_favela_f1_pos);
                posmap.add(R.drawable.map_favela_f2_pos);
                posmap.add(R.drawable.map_favela_f3_pos);
                break;

            case 11:
                posmap.add(0);
                posmap.add(2);
                posmap.add(R.drawable.map_border_f0_pos);
                posmap.add(R.drawable.map_border_f1_pos);
                posmap.add(R.drawable.map_border_f2_pos);
                break;

            case 12:
                posmap.add(-1);
                posmap.add(3);
                posmap.add(R.drawable.map_yatch__1_pos);
                posmap.add(R.drawable.map_yatch_f0_pos);
                posmap.add(R.drawable.map_yatch_f1_pos);
                posmap.add(R.drawable.map_yatch_f2_pos);
                posmap.add(R.drawable.map_yatch_f3_pos);
                break;

            case 13:
                posmap.add(0);
                posmap.add(3);
                posmap.add(R.drawable.map_sky_f0_pos);
                posmap.add(R.drawable.map_sky_f1_pos);
                posmap.add(R.drawable.map_sky_f2_pos);
                posmap.add(R.drawable.map_sky_f3_pos);
                break;
        }

        return posmap;
    }
}