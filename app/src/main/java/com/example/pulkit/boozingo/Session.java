package com.example.pulkit.boozingo;


import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int mode=0;
    String prefname="SESSION";
    private String is_loggedin = "is_loggedin";

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


}


