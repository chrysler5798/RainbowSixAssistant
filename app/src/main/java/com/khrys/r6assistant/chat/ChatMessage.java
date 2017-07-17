package com.khrys.r6assistant.chat;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 7/1/2017 [7:41 PM]
*/

import java.util.Calendar;
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

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        messageTime = String.format("%02d:%02d", c.get(Calendar.HOUR), c.get(Calendar.MINUTE));
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