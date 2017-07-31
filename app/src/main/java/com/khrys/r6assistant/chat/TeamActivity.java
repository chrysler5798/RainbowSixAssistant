package com.khrys.r6assistant.chat;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 7/15/2017 [9:08 PM]
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.khrys.r6assistant.R;

public class TeamActivity extends AppCompatActivity
{
    String pseudo, ownerId, team_id;
    ListView listOfMessages;
    AlertDialog alertLeave;
    int playerneeded;
    Team team;
    private FirebaseListAdapter<ChatMessage> adapter;
    boolean existDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_team);

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
                alertFirstBuilder.setMessage(R.string.team_dialog_msg);
                alertFirstBuilder.setIcon(android.R.drawable.ic_dialog_info);

                alertFirstBuilder.setPositiveButton(android.R.string.ok,null);

                AlertDialog alertFirst = alertFirstBuilder.create();
                alertFirst.show();
            }
        }

        pseudo = getIntent().getStringExtra("pseudo");
        ownerId = getIntent().getStringExtra("ownerId");
        team_id = "team_"+ownerId;
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

                DataSnapshot dataInTeam = dataSnapshot.child("teams").child(team_id);

                if(dataInTeam.getValue(Team.class) != null)
                {
                    idplayer = dataInTeam.getValue(Team.class).getIdplayer();
                    name = dataInTeam.getValue(Team.class).getName();
                    country = dataInTeam.getValue(Team.class).getLanguage();
                    rank = dataInTeam.getValue(Team.class).getRank();
                    playerneeded = dataInTeam.getValue(Team.class).getPlayerneeded();
                    platform = dataInTeam.getValue(Team.class).getPlatform();
                    looking = dataInTeam.getValue(Team.class).getLooking();

                    team = new Team(idplayer, name, country, platform, looking, rank, playerneeded);

                    setupView(idplayer, name, country, rank, playerneeded, platform, looking);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
            }
        });

        ImageButton buttonMinusPlayer = (ImageButton) findViewById(R.id.buttonMinusPlayer);
        ImageButton buttonPlusPlayer = (ImageButton) findViewById(R.id.buttonPlusPlayer);
        if(!isOwner())
        {
            buttonMinusPlayer.setVisibility(View.GONE);
            buttonPlusPlayer.setVisibility(View.GONE);
        }

        buttonMinusPlayer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(playerneeded+1<4)
                {
                    playerneeded += 1;
                    team.setPlayerneeded(playerneeded);
                    FirebaseDatabase.getInstance().getReference().child("teams").child(team_id).setValue(team);
                }
            }
        });

        buttonPlusPlayer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(playerneeded-1>0)
                {
                    playerneeded -= 1;
                    team.setPlayerneeded(playerneeded);
                    FirebaseDatabase.getInstance().getReference().child("teams").child(team_id).setValue(team);
                }
                else if(playerneeded-1 == 0)
                {
                    AlertDialog.Builder alertFull = new AlertDialog.Builder(TeamActivity.this);

                    alertFull.setTitle(R.string.team_full_dialog_title);
                    alertFull.setMessage(R.string.team_full_dialog_msg);
                    alertFull.setIcon(android.R.drawable.ic_dialog_info);

                    alertFull.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            removeTeam();
                            finish();
                        }
                    });
                    alertFull.setNegativeButton(android.R.string.cancel, null);

                    AlertDialog alertFullDialog = alertFull.create();
                    alertFullDialog.show();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.button_team_msg);
        listOfMessages = (ListView) findViewById(R.id.list_msg_team);

        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class, R.layout.message, FirebaseDatabase.getInstance().getReference().child("teams_msgs").child(team_id))
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
                EditText input = (EditText)findViewById(R.id.input_msg_team);

                String sender;
                if(isOwner())
                {
                    sender = getString(R.string.team_tag_admin)+" "+pseudo;
                }
                else
                {
                    sender = pseudo;
                }

                DatabaseReference dbTeam = FirebaseDatabase.getInstance().getReference().child("teams_msgs");
                String msgToSend = input.getText().toString();
                if(existDb && !msgToSend.isEmpty())
                {
                    dbTeam.child(team_id)
                            .push()
                            .setValue(new ChatMessage(msgToSend, sender));

                    input.setText("");
                }

            }
        });
    }

    void setupView(String idplayer, String name, String country, int rank, int playerneeded, int platform, int looking)
    {
        ImageView imagePlatform = (ImageView) findViewById(R.id.imagePlatformTeam);
        ImageView imageRank = (ImageView) findViewById(R.id.imageRankTeam);
        TextView textNumberPlayers = (TextView) findViewById(R.id.textPlayerNeededTeam);

        setTitle(name+" ("+getString(R.string.team_mpmode_for)+" "+getString(getResources().getIdentifier("team_mpmode_"+String.valueOf(looking),"string", getPackageName()))+")");

        imagePlatform.setImageResource(getResources().getIdentifier("platform_"+platform, "drawable", getPackageName()));

        imageRank.setImageResource(getResources().getIdentifier("rankg_"+rank, "drawable", getPackageName()));

        String textPlayerNeed = String.valueOf(5-playerneeded)+"/5";
        textNumberPlayers.setText(textPlayerNeed);
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
        removeTeam();
        finish();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        removeTeam();
    }

    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBeforeTeam", false);
        if (!ranBefore) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBeforeTeam", true);
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
                existDb = dataSnapshot.child("teams").hasChild(team_id);
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
            alertLeaveBuilder.setMessage(R.string.team_leave_dialog_msg);
            alertLeaveBuilder.setIcon(android.R.drawable.stat_sys_warning);

            alertLeaveBuilder.setPositiveButton(R.string.team_leave_dialog, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    removeTeam();
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

    void removeTeam()
    {
        if(isOwner())
        {
            FirebaseDatabase.getInstance().getReference().child("teams").child(team_id).removeValue();
            FirebaseDatabase.getInstance().getReference().child("teams_msgs").child(team_id).removeValue();
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