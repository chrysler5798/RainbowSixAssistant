package com.khrys.r6assistant.chat;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 7/1/2017 [7:24 PM]
*/

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.khrys.r6assistant.R;

public class MainChatActivity extends AppCompatActivity
{
    private FirebaseListAdapter<ChatMessage> adapter;
    String pseudo = "";
    int channel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        channel = getIntent().getIntExtra("channel",0)+1;
        displayChatMessages(channel);
        getPseudoDb();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    EditText input = (EditText)findViewById(R.id.input);

                    FirebaseDatabase.getInstance()
                            .getReference("channels/channel"+channel+"/msgs")
                            .push()
                            .setValue(new ChatMessage(input.getText().toString(), pseudo));

                    input.setText("");
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

    private void displayChatMessages(int channel)
    {
        setContentView(R.layout.activity_chat);

        ListView listOfMessages = (ListView) findViewById(R.id.list_of_messages);

        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class, R.layout.message, FirebaseDatabase.getInstance().getReference("channels/channel"+channel+"/msgs"))
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
    }

    void getPseudoDb()
    {
        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                pseudo = dataSnapshot.child("users").child(getUid()).getValue(User.class).getPseudo();
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

    String getUid()
    {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}