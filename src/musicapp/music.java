package musicapp;


import java.util.HashMap;

class music{

    public static HashMap<Integer, music> musics = new HashMap<Integer, music>();

    private String muzikismi = "";
    private String muziksanatci = "";
    private String muzikalbum = "";

    public music(String sarkiadi, String sanatci, String albumadi){
        this.muzikismi = sarkiadi;
        this.muziksanatci = sanatci;
        this.muzikalbum = albumadi;
        musics.put(musics.size()+1, this);
    }

    public String getMuzikismi() {
        return muzikismi;
    }

    public void setMuzikismi(String muzikismi) {
        this.muzikismi = muzikismi;
    }

    public String getMuzikalbum() {
        return muzikalbum;
    }

    public void setMuzikalbum(String muzikalbum) {
        this.muzikalbum = muzikalbum;
    }

    public String getMuziksanatci() {
        return muziksanatci;
    }

    public void setMuziksanatci(String muziksanatci) {
        this.muziksanatci = muziksanatci;
    }

}