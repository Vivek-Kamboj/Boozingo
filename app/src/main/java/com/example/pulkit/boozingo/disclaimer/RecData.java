package com.example.pulkit.boozingo.disclaimer;


import java.util.ArrayList;
import java.util.List;

public class RecData {

    public static String [] title = {"You must follow any policies made available to you within the Services. ",
            "You may need a Google Account in order to use some of our Services. You may create your own Google Account, or your Google Account may be assigned to you by an administrator, such as your employer or educational institution.",
            "We respond to notices of alleged copyright infringement and terminate accounts of repeat infringers according to the process set out in the U.S. Digital Millennium Copyright Act.",
            "Some of our Services allow you to upload, submit, store, send or receive content. You retain ownership of any intellectual property rights that you hold in that content. In short, what belongs to you stays yours. ",
            "When a Service requires or includes downloadable software, this software may update automatically on your device once a new version or feature is available. ",
            "Some Services may let you adjust your automatic update settings."};

    public static List<String> list = new ArrayList<String>();

    public static List<String> retList(){

        for(int i=0; i<title.length ; i++){
            String item = title[i];
            list.add(item);
        }
        return list;
    }

}
