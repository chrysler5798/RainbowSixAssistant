package com.khrys.r6assistant.chat;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 7/17/2017 [12:10 PM]
*/

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.khrys.r6assistant.R;

public class PlayerActivity extends AppCompatActivity
{
    String pseudo, ownerId, player_id;
    ListView listOfMessages;
    AlertDialog alertLeave;
    Player player;
    boolean existDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_player);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if(isOwner())
        {
            if(isFirstTime())
            {
                AlertDialog.Builder alertFirstBuilder = new AlertDialog.Builder(this);

                alertFirstBuilder.setTitle(R.string.team_dialog_title);
                alertFirstBuilder.setMessage(R.string.player_dialog_msg);
                alertFirstBuilder.setIcon(android.R.drawable.ic_dialog_info);

                alertFirstBuilder.setPositiveButton(android.R.string.ok,null);

                AlertDialog alertFirst = alertFirstBuilder.create();
                alertFirst.show();
            }
        }

        pseudo = getIntent().getStringExtra("pseudo");
        ownerId = getIntent().getStringExtra("ownerId");
        player_id = "player_"+ownerId;
        dbExists();

        FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(!existDb)
                {
                    finish();
                }
                String idplayer, name, country;
                int rank, platform, looking;

                DataSnapshot dataInPlayer = dataSnapshot.child("players").child(player_id);

                if(dataInPlayer.getValue(Player.class) != null)
                {
                    idplayer = dataInPlayer.getValue(Player.class).getIdplayer();
                    name = dataInPlayer.getValue(Player.class).getName();
                    country = dataInPlayer.getValue(Player.class).getLanguage();
                    rank = dataInPlayer.getValue(Player.class).getRank();
                    platform = dataInPlayer.getValue(Player.class).getPlatform();
                    looking = dataInPlayer.getValue(Player.class).getLooking();

                    player = new Player(idplayer, name, country, platform, looking, rank);

                    setupView(idplayer, name, country, rank, platform, looking);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.button_player_msg);
        listOfMessages = (ListView) findViewById(R.id.list_msg_ActPlayer);

        FirebaseListAdapter<ChatMessage> adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class, R.layout.message, FirebaseDatabase.getInstance().getReference().child("players").child(player_id).child("msgs"))
        {
            @Override
            protected void populateView(View v, ChatMessage model, int position)
            {
                TextView messageText = v.findViewById(R.id.message_text);
                TextView messageUser = v.findViewById(R.id.message_user);
                TextView messageTime = v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(model.getMessageTime());
            }
        };

        listOfMessages.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                EditText input = (EditText)findViewById(R.id.input_msg_ActPlayer);

                String sender;
                if(isOwner())
                {
                    sender = getString(R.string.player_tag_admin)+" "+pseudo;
                }
                else
                {
                    sender = pseudo;
                }

                DatabaseReference dbPlayer = FirebaseDatabase.getInstance().getReference().child("players");
                String msgToSend = input.getText().toString();
                if(existDb && !msgToSend.isEmpty())
                {
                    dbPlayer.child(player_id).child("msgs")
                            .push()
                            .setValue(new ChatMessage(msgToSend, sender));

                    input.setText("");
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                checkIfOwnerWantToLeave();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed()
    {
        checkIfOwnerWantToLeave();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        removePlayer();
        finish();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        removePlayer();
    }

    void setupView(String idplayer, String name, String country, int rank, int platform, int looking)
    {
        ImageView imagePlatform = (ImageView) findViewById(R.id.imagePlatformActPlayer);
        ImageView imageRank = (ImageView) findViewById(R.id.imageRankActPlayer);

        setTitle(name+" ("+getString(R.string.team_mpmode_for)+" "+getString(getResources().getIdentifier("team_mpmode_"+String.valueOf(looking),"string", getPackageName()))+")");

        imagePlatform.setImageResource(getResources().getIdentifier("platform_"+platform, "drawable", getPackageName()));

        imageRank.setImageResource(getResources().getIdentifier("rankg_"+rank, "drawable", getPackageName()));
    }

    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBeforePlayer", false);
        if (!ranBefore) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBeforePlayer", true);
            editor.apply();
        }
        return !ranBefore;
    }

    void dbExists()
    {
        FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                existDb = dataSnapshot.child("players").hasChild(player_id);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

    void checkIfOwnerWantToLeave()
    {
        if(isOwner())
        {
            AlertDialog.Builder alertLeaveBuilder = new AlertDialog.Builder(this);

            alertLeaveBuilder.setTitle(R.string.team_leave_dialog_title);
            alertLeaveBuilder.setMessage(R.string.player_leave_dialog_msg);
            alertLeaveBuilder.setIcon(android.R.drawable.stat_sys_warning);

            alertLeaveBuilder.setPositiveButton(R.string.team_leave_dialog, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    removePlayer();
                    finish();
                }
            });
            alertLeaveBuilder.setNegativeButton(R.string.team_stay_dialog, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    alertLeave.cancel();
                }
            });

            alertLeave = alertLeaveBuilder.create();
            alertLeave.show();
        }
        else
        {
            finish();
        }
    }

    void removePlayer()
    {
        if(isOwner())
        {
            FirebaseDatabase.getInstance().getReference().child("players").child(player_id).removeValue();
        }
    }

    String getUid()
    {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    boolean isOwner()
    {
        return getUid().equals(ownerId);
    }
}