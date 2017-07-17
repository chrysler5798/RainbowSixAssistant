package com.khrys.r6assistant.chat;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 7/2/2017 [4:58 PM]
*/

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.khrys.r6assistant.R;

public class MenuChatActivity extends AppCompatActivity
{
    String pseudo;
    FirebaseAuth mAuth;
    EditText editPseudo;
    FirebaseUser user;
    AlertDialog dialog;
    InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_onlinemenu);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button btnTeam = (Button) findViewById(R.id.buttonTeam);
        Button btnPlayer = (Button) findViewById(R.id.buttonPlayer);
        Button btnChat = (Button) findViewById(R.id.buttonChat);

        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if(user == null)
        {
            showDialogToSign();
        }
        else
        {
            FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener()
            {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    String pseudoDb = dataSnapshot.child("users").child(getUid()).getValue(User.class).getPseudo();
                    String msgWelcome = String.format(getResources().getString(R.string.online_welcome), pseudoDb);
                    Toast.makeText(getApplicationContext(), msgWelcome, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError)
                {

                }
            });
        }

        btnTeam.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MenuChatActivity.this, MenuFindTeamActivity.class));
            }
        });

        btnPlayer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MenuChatActivity.this, MenuFindPlayerActivity.class));
            }
        });

        btnChat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MenuChatActivity.this, MenuChannelActivity.class));
            }
        });
    }

    void signInAno()
    {
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    FirebaseDatabase.getInstance()
                            .getReference("users/"+getUid())
                            .setValue(new User(pseudo,1));
                    welcomeMsg();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), R.string.online_fail, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void showDialogToSign()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_login, null))
                .setPositiveButton(R.string.online_signin, null)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                        finish();
                    }
                });

        dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener()
        {
            @Override
            public void onShow(DialogInterface dialogInterface)
            {
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

                Button posiButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                posiButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        String txtEdit = editPseudo.getText().toString();
                        if(txtEdit.length() <= 4)
                        {
                            Toast.makeText(getApplicationContext(), R.string.signin_online, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            dialog.dismiss();
                            pseudo = txtEdit;
                            closeKeyboard();
                            signInAno();
                        }
                    }
                });
            }
        });

        dialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);
        dialog.setTitle(R.string.enternick_online);
        dialog.show();

        editPseudo = (EditText) dialog.findViewById(R.id.editTextPseudo);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
            @Override
            public void onCancel(DialogInterface dialogInterface)
            {
                closeKeyboard();
                finish();
            }
        });
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

    void welcomeMsg()
    {
        String msgWelcome = String.format(getResources().getString(R.string.online_welcome), pseudo);
        Toast.makeText(getApplicationContext(), msgWelcome, Toast.LENGTH_LONG).show();
    }

    String getUid()
    {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    void closeKeyboard()
    {
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
    }
}