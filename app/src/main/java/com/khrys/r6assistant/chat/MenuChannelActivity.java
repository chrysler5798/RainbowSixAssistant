package com.khrys.r6assistant.chat;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 7/2/2017 [7:54 PM]
*/

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.khrys.r6assistant.R;

public class MenuChannelActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menurooms);

        ListView listRooms = (ListView) findViewById(R.id.roomList);

        FirebaseListAdapter<Channel> adapter = new FirebaseListAdapter<Channel>(this, Channel.class, R.layout.channel, FirebaseDatabase.getInstance().getReference("channels"))
        {
            String nameChann;

            @Override
            protected void populateView(View v, Channel model, final int position)
            {
                TextView txtNom = v.findViewById(R.id.nomChann);

                nameChann = model.getName();
                txtNom.setText(nameChann);

                v.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        startActivity(new Intent(MenuChannelActivity.this, MainChatActivity.class).putExtra("channel",position));
                    }
                });
            }
        };

        listRooms.setAdapter(adapter);
    }
}