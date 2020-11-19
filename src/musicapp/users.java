package musicapp;

import java.util.ArrayList;

public class users {

    public static ArrayList<music> playlist = new ArrayList<>();

    static void sarkiEkle(music m){
        playlist.add(m);
    }

    static void sarkiSil(music m){
        playlist.remove(m);
    }
}
