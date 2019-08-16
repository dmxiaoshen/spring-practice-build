package com.xyz.designpatterns.structure.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzhsg on 2018/5/7.
 */
public class ClubFactory {
    private static  final Map<String,Club> clubMap = new HashMap<>();

    public static Club get(String name){
        Club club = clubMap.get(name);
        if(club==null){
            club = new Club(name);
            clubMap.put(name,club);
        }
        return club;
    }

    public static int getSize(){
        return clubMap.size();
    }
}
