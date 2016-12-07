package com.khrys.r6assistant;

import android.view.View;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Louis on 06/12/2016.
 * <p>
 * RainbowSixAssistant
 */

class ClickListenerPlan implements View.OnClickListener {

    private int typeRequest;
    private PhotoViewAttacher mAttacher;

    ClickListenerPlan(int type, PhotoViewAttacher mAttacherTransfer)
    {
        typeRequest = type;
        mAttacher = mAttacherTransfer;
    }

    @Override
    public void onClick(View view)
    {
        float Scale;
        switch (typeRequest)
        {
            case 1:
                Scale = mAttacher.getScale();
                if(Scale > 1F)
                {
                    mAttacher.zoomTo(Scale-1.0F,0,0);
                }
                break;

            case 2:
                mAttacher.update();
                break;

            case 3:
                Scale = mAttacher.getScale();
                if(Scale < 4F)
                {
                    mAttacher.zoomTo(Scale+1.0F,0,0);
                }
                break;
        }
    }
}
