package com.khrys.r6assistant.chat;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 7/17/2017 [11:15 AM]
*/

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.khrys.r6assistant.R;

public class MenuFindPlayerActivity extends AppCompatActivity
{
    AlertDialog dialog;
    ProgressBar progressLoad;
    TextView msgWhenNothing;
    boolean existDb;
    String pseudoDb;
    FirebaseListAdapter<Team> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_players);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ListView listPlayers = (ListView) findViewById(R.id.listPlayer);
        Button buttonCreatePlayer = (Button) findViewById(R.id.buttonCreatePlayer);
        progressLoad = (ProgressBar) findViewById(R.id.progressBarLoadPlayer);
        msgWhenNothing = (TextView) findViewById(R.id.textMsgEmptyPlayer);
        msgWhenNothing.setVisibility(View.GONE);

        initPseudoDb();

        buttonCreatePlayer.setOnClickListener(new View.OnClickListener()
        {
            EditText editName;
            Spinner spinnerLanguage, spinnerMpMode, spinnerPlatform, spinnerRank;
            String[] modeMp = {getString(R.string.team_mpmode_1),getString(R.string.team_mpmode_2),getString(R.string.team_mpmode_3)}, platformsList = {"PC","Xbox ONE","PS4"}, ranksList = {getString(R.string.team_rank_1),getString(R.string.team_rank_2),getString(R.string.team_rank_3),getString(R.string.team_rank_4),getString(R.string.team_rank_5),getString(R.string.team_rank_6)};
            String[] languagesList = {getString(R.string.english),getString(R.string.simplified_chinese),getString(R.string.french),getString(R.string.german),getString(R.string.italian),getString(R.string.japenese),getString(R.string.korean),getString(R.string.polish),getString(R.string.portuguese),getString(R.string.russian),getString(R.string.spanish)};
            String[] languagesIdList = {"gb","zh","fr","de","it","jp","kr","pl","pt","ru","es"};

            @Override
            public void onClick(View view)
            {
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view.getContext());

                LayoutInflater layoutDialog = getLayoutInflater();

                dialogBuilder.setView(layoutDialog.inflate(R.layout.dialog_create_player, null))
                        .setPositiveButton(R.string.create_txt, null)
                        .setNegativeButton(android.R.string.cancel, null);

                dialog = dialogBuilder.create();
                dialog.setTitle(R.string.create_player);

                dialog.setOnShowListener(new DialogInterface.OnShowListener()
                {
                    @Override
                    public void onShow(DialogInterface dialogInterface)
                    {
                        editName = (EditText) dialog.findViewById(R.id.editNamePlayer);
                        editName.setText(pseudoDb);
                        spinnerLanguage = (Spinner) dialog.findViewById(R.id.spinnerLanguagePlayer);
                        spinnerMpMode = (Spinner) dialog.findViewById(R.id.spinnerLookingPlayer);
                        spinnerPlatform = (Spinner) dialog.findViewById(R.id.spinnerPlatformPlayer);
                        spinnerRank = (Spinner) dialog.findViewById(R.id.spinnerRankPlayer);

                        spinnerLanguage.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_spinner_dropdown_item, languagesList));
                        spinnerPlatform.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_spinner_dropdown_item, platformsList));
                        spinnerMpMode.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_spinner_dropdown_item, modeMp));
                        spinnerRank.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_spinner_dropdown_item, ranksList));

                        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                if(!dbExists())
                                {
                                    if(editName.getText().toString().length() > 4)
                                    {
                                        Task taskSend = FirebaseDatabase.getInstance().getReference().child("players").child("player_"+getUid())
                                                .setValue(new Player(getUid(),editName.getText().toString(),languagesIdList[spinnerLanguage.getSelectedItemPosition()],spinnerRank.getSelectedItemPosition()+1,spinnerPlatform.getSelectedItemPosition()+1,spinnerMpMode.getSelectedItemPosition()+1));
                                        taskSend.addOnSuccessListener(new OnSuccessListener()
                                        {
                                            @Override
                                            public void onSuccess(Object o)
                                            {
                                                switchToPlayerView(getUid(), pseudoDb);
                                                dialog.cancel();
                                            }
                                        });
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), R.string.player_name_error, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });
                    }
                });

                dialog.show();
            }
        });

        adapter = new FirebaseListAdapter<Team>(this, Team.class, R.layout.player, FirebaseDatabase.getInstance().getReference("players"))
        {
            String nameChann;

            @Override
            public void onDataChanged()
            {
                super.onDataChanged();
                progressLoad.setVisibility(View.GONE);
                if(adapter.getCount() < 1)
                {
                    msgWhenNothing.setVisibility(View.VISIBLE);
                }
                else
                {
                    msgWhenNothing.setVisibility(View.GONE);
                }
            }

            @Override
            protected void populateView(View v, final Team model, final int position)
            {
                TextView txtNom = v.findViewById(R.id.nomPlayer);
                ImageView imageLanguage = v.findViewById(R.id.imageLanguagePlayer);
                TextView txtLooking = v.findViewById(R.id.textLookingPlayer);
                ImageView imagePlatform = v.findViewById(R.id.imagePlatformPlayer);
                ImageView imageRank = v.findViewById(R.id.imageRankPlayer);

                nameChann = model.getName();
                txtNom.setText(nameChann);

                imageLanguage.setImageResource(getResources().getIdentifier("flag_"+model.getLanguage(),"drawable",getPackageName()));

                String msgLooking = getString(R.string.team_mpmode_for)+" "+getString(getResources().getIdentifier("team_mpmode_"+String.valueOf(model.getLooking()),"string", getPackageName()));
                txtLooking.setText(msgLooking);

                imagePlatform.setImageResource(getResources().getIdentifier("platform_"+model.getPlatform(), "drawable", getPackageName()));

                imageRank.setImageResource(getResources().getIdentifier("rankg_"+model.getRank(), "drawable", getPackageName()));

                v.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        switchToPlayerView(model.getIdplayer(), pseudoDb);
                    }
                });
            }
        };

        listPlayers.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void switchToPlayerView(String uid, String pseudo)
    {
        Intent playerAct = new Intent(MenuFindPlayerActivity.this, PlayerActivity.class).putExtra("ownerId", uid).putExtra("pseudo",pseudo);
        startActivity(playerAct);
    }

    String getUid()
    {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    void initPseudoDb()
    {
        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                pseudoDb = dataSnapshot.child("users").child(getUid()).getValue(User.class).getPseudo();
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

    boolean dbExists()
    {
        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                existDb = dataSnapshot.child("players").hasChild("player_"+getUid());
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
        return existDb;
    }
}