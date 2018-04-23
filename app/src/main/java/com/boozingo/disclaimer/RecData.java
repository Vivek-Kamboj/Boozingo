package com.boozingo.disclaimer;


import java.util.ArrayList;
import java.util.List;

public class RecData {

    public static String[] title = {"You must be 18 year of age or above to explore the contents of Boozingo.",
            "Boozingo contains information about alcohol and the places where it serves.",
            "You must be agreeing with our terms while passing through our terms page.",
            "Boozingo is not intended to promote alcohol but just to provide web information.",
            "Drinking alcohol is injurious to Health, Wealth & Wisdom.",
            "We assume that you fulfil all the required criteria to enter the world of BooZingo.",
            "Boozingo only provides data available on web & does not provide any guarantee of it accuracy."};
    public static List<String> list = new ArrayList<String>();

    public static List<String> retList() {

        for (int i = 0; i < title.length; i++) {
            String item = title[i];
            list.add(item);
        }
        return list;
    }

}
