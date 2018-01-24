package com.example.pulkit.boozingo;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Session {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int mode=0;
    String prefname="SESSION";
    private String is_loggedin = "is_loggedin";
    private String is_first = "is_first";
    private String city_list = "city_list";

    public Session(Context context)
    {
        this._context=context;
        pref = _context.getSharedPreferences(prefname,mode);
        editor = pref.edit();
    }

    public void create_oldusersession()
    {
        editor.putBoolean(is_loggedin,true);
        editor.commit();
    }


    public Boolean isolduser()
    {
        return pref.getBoolean(is_loggedin,false);
    }

    public void create_isfirst()
    {
        editor.putBoolean(is_first,false);
        editor.commit();
    }


    public Boolean isfirst()
    {
        return pref.getBoolean(is_first,true);
    }

    public void create_city_list(String [] list){
        Set<String > mySet = new HashSet<String>(Arrays.asList(list));
        editor.putStringSet(city_list, mySet);
        editor.commit();
    }

    public String[] get_city_list(){
        Set<String> set = pref.getStringSet(city_list, null);
        if(set!=null)
            return set.toArray(new String[0]);
        else
            return null;
    }
}


