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
    private String is_first = "is_first";
    private String is_app_installed = "is_app_installed";

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

    public void create_isappInstalled(){
        editor.putBoolean(is_app_installed,true);
        editor.commit();
    }

    public Boolean isappInstalled()
    {
        return pref.getBoolean(is_app_installed,false);
    }
}


