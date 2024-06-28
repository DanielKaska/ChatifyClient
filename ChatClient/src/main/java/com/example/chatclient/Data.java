package com.example.chatclient;

import java.lang.invoke.StringConcatException;
import java.util.ArrayList;
import java.util.List;

public class Data {

    private String nick;
    private Server server;
    private List<String> messages = new ArrayList<String>();

    private static final Data Instance = new Data();

    public static Data GetInstance(){
        return Instance;
    }

    public void SetNick(String n){
        nick = n;

        System.out.println(nick);
    }

    public String GetNick(){
        return nick;
    }

    public void SetServer(Server s){
        server = s;
    }

    public Server GetServer(){
        return server;
    }

    public void AddMessage(String s){
        messages.add(s);
    }

    public List<String> GetMessages(){
        return messages;
    }


}
