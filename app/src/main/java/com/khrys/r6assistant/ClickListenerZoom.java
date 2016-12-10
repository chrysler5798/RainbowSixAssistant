package com.khrys.r6assistant;

import android.view.View;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Louis on 06/12/2016.
 * <p>
 * RainbowSixAssistant
 */

class ClickListenerZoom implements View.OnClickListener {

    private int typeRequest;
    private PhotoViewAttacher mAttacher;

    ClickListenerZoom(int type, PhotoViewAttacher mAttacherTransfer)
    {
        typeRequest = type;
        mAttacher = mAttacherTransfer;
    }

    @Override
    public void onClick(View view)
    {
        float Scale = mAttacher.getScale();
        switch (typeRequest)
        {
            case 1:
                if(Scale > mAttacher.getMinimumScale())
                {
                    mAttacher.setScale(Scale-1.0F);
                }
                break;

            case 2:
                mAttacher.update();
                break;

            case 3:
                if(Scale < mAttacher.getMaximumScale())
                {
                    mAttacher.setScale(Scale+1.0F);
                }
                break;
        }
    }
}
