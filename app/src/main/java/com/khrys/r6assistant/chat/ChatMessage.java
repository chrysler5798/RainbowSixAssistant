package com.khrys.r6assistant.chat;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 7/1/2017 [7:41 PM]
*/

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ChatMessage
{
    private String messageText;
    private String messageUser;
    private String messageTime;

    public ChatMessage()
    {
    }

    public ChatMessage(String messageText, String messageUser)
    {
        this.messageText = messageText;
        this.messageUser = messageUser;

        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        Date date = new Date();
        messageTime = dateFormat.format(date);
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
}