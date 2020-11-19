package musicapp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class musicapp extends JFrame implements MouseListener, MouseMotionListener {

    static musicapp instance;
    public static void main(String args[]) {
        new musicapp();
    }

    musicpanel panel;

    public musicapp(){
        instance = this;

        //�er�eve
        setSize(1024, 768);
        //uygulama a��ld���nda ekran�n ortas�na gelecek
        setLocationRelativeTo(null);
        //uygulama k���lt�l�nce g�z�kecek resim
        setIconImage(new ImageIcon(getClass().getResource("images/logo.png")).getImage());
        //uygulaman�n �stte g�z�ken ismi
        setTitle("M�zik Uygulamas�");
        //�arp�ya bas�nca uygulamay� tamamen kapat�r.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Boyut de�i�tirmeyi engeller.
        setResizable(false);
        //uygulamay� g�r�n�r yap
        setVisible(true);

        //M�zikleri ekle
        muzikleriOlustur();

        //�� g�r�nt�
        //�er�evenin i�ini doldurmam�z i�in kulland���m�z panel
        panel = new musicpanel();
        //paneli �er�eveye ekliyoruz b�ylece �er�evenin i�inde istedi�imiz g�r�nt� olacak
        this.add(panel);

        //Mouse hareketlerini kontrol i�in MouseListener implements ediliyor
        this.addMouseListener(this);
        //Mouse hareketlerini kontrol i�in MouseMotionListener implements ediliyor
        this.addMouseMotionListener(this);

    }

    void muzikleriOlustur(){
        //M�zikler ekleniyor
        new music("Baguettes in the Face", "Mustard", "Perfect Ten");
        new music("Escalate", "Tsar B", "Tsar B");
        new music("How Do You Sleep?", "Say Smith", "How Do You Sleep?");
        new music("Memories", "Maroon 5", "Memories");
        new music("All Eyez On Me", "2Pac", "All Eyez On Me");
        new music("Shot", "Ufo361", "WAVE");
        new music("The London", "Young Thug", "The London");
        new music("Feels", "Calvin Harris", "Funk Wav Bounces Vol.1");
        new music("CAN'T STOP THE FEEL�NG!", "Justin Timberlake", "CAN'T STOP THE FEEL�NG!");
        new music("Nothing Breaks Like A Heart", "Miley Cyrus", "Nothing Breaks Like A Heart");
        new music("Blinding Lights", "The Weeknd", "Blinding Lights");
        new music("Ambitionz Az A Ridah", "2Pac", "All Eyez On Me");
        new music("In the Name of Love", "Martin Garrix", "In the Name of Love");
        new music("Ric Flair Drip", "Offset", "Without Warning");
        new music("Me, Myself & I", "G-Eazy", "When It's Dark Out");
        new music("Look What You Made Me Do", "Taylor Swift", "reputation");
        new music("Let You Love Me", "Rita Ora", "Phoenix");
        new music("Walk", "Kwabs", "Walk");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!panel.muziksayfa && !panel.playlistsayfa){
            if(e.getX() >= panel.getWidth()/2){
                panel.playlistsayfa = true;
            }else{
                panel.muziksayfa = true;
            }
        }else if(panel.muziksayfa){
            panel.boyay = (e.getY()-100)/20;
            if(panel.boyay >= 1 && panel.boyay <= 18){
                music m = music.musics.get(panel.boyay);
                if(users.playlist.contains(m)){
                    users.playlist.remove(m);
                }else{
                    users.playlist.add(m);
                }
            }
            if(e.getX() >= 20 && e.getX() <= 65
                    && e.getY() >= panel.height-80 && e.getY() <= panel.height-40){
                panel.muziksayfa = false;
            }
        }else{
            panel.boyay = (e.getY()-100)/20;
            if(panel.boyay >= 1 && panel.boyay <= 18){
                int say = 1;
                for(music m : users.playlist){
                    if(panel.boyay == say){
                        users.playlist.remove(m);
                        return;
                    }
                    say+=1;
                }
            }
            if(e.getX() >= 20 && e.getX() <= 65
                    && e.getY() >= panel.height-80 && e.getY() <= panel.height-40){
                panel.playlistsayfa = false;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseexit = false;
    }

    boolean mouseexit;
    @Override
    public void mouseExited(MouseEvent e) {
        panel.muziklerefekt = false;
        panel.playlistefekt = false;
        panel.repaint();
        mouseexit = true;
        panel.backefekt = false;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(mouseexit)
            return;
        if(panel.muziksayfa){
            panel.boyay = (e.getY()-100)/20;
            panel.backefekt = false;
            if(e.getX() >= 20 && e.getX() <= 65
            && e.getY() >= panel.height-80 && e.getY() <= panel.height-40){
                panel.backefekt = true;
            }
            return;
        }
        if(panel.playlistsayfa){
            panel.boyay = (e.getY()-100)/20;
            panel.backefekt = false;
            if(e.getX() >= 20 && e.getX() <= 65
                    && e.getY() >= panel.height-80 && e.getY() <= panel.height-40){
                panel.backefekt = true;
            }
            return;
        }
        panel.playlistefekt = false;
        panel.muziklerefekt = false;
        if(e.getX() >= panel.getWidth()/2){
            panel.playlistefekt = true;
        }else{
            panel.muziklerefekt = true;
        }
        panel.repaint();
    }

}

