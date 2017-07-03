package com.khrys.r6assistant.chat;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 7/2/2017 [4:58 PM]
*/

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.khrys.r6assistant.R;

public class MenuChatActivity extends AppCompatActivity
{
    FirebaseAuth mAuth;
    EditText editPseudo;
    String pseudoUser = "";
    FirebaseUser user;
    AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_onlinemenu);

        Button btnChat = (Button) findViewById(R.id.buttonChat);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if(user == null)
        {
            showDialogToSign();
        }

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
                    if(user != null)
                    {
                        UserProfileChangeRequest changeP = new UserProfileChangeRequest.Builder().setDisplayName(pseudoUser).build();
                        user.updateProfile(changeP);
                    }
                    Toast.makeText(getApplicationContext(), "Welcome "+pseudoUser+" to R6 Assistant Online", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void showDialogToSign()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.activity_login, null))
                .setPositiveButton("Sign in", null)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
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
                Button posiButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                posiButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        pseudoUser = editPseudo.getText().toString();
                        if(pseudoUser.length() > 15)
                        {
                            Toast.makeText(getApplicationContext(), "This nickname is way too long !", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            dialog.dismiss();
                            signInAno();
                        }
                    }
                });
            }
        });

        dialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);
        dialog.setTitle("Enter your nickname :");
        dialog.show();

        editPseudo = (EditText) dialog.findViewById(R.id.editTextPseudo);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
            @Override
            public void onCancel(DialogInterface dialogInterface)
            {
                finish();
            }
        });
    }
}