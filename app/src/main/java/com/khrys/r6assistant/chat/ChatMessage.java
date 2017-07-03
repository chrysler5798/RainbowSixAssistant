package com.khrys.r6assistant.chat;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 7/1/2017 [7:41 PM]
*/

import java.util.Calendar;

public class ChatMessage
{
    private String messageText;
    private String messageUser;
    private String messageTime;
    private String uplay;

    public ChatMessage(String messageText, String messageUser)
    {
        this.messageText = messageText;
        this.messageUser = messageUser;

        // Initialize to current time
        Calendar c = Calendar.getInstance();
        messageTime = c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE);
        uplay = "test";
    }

    public ChatMessage()
    {

    }

    public String getMessageText()
    {
        return messageText;
    }

    public void setMessageText(String messageText)
    {
        this.messageText = messageText;
    }

    public String getMessageUser()
    {
        return messageUser;
    }

    public void setMessageUser(String messageUser)
    {
        this.messageUser = messageUser;
    }

    public String getMessageTime()
    {
        return messageTime;
    }

    public void setMessageTime(String messageTime)
    {
        this.messageTime = messageTime;
    }

    public String getUplay()
    {
        return uplay;
    }

    public void setUplay(String uplay)
    {
        this.uplay = uplay;
    }
}