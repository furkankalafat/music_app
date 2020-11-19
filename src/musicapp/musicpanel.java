package musicapp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class musicpanel extends JPanel {


    //Panel boyutlarý
    int width = 1024, height = 768;

    BufferedImage muzikler = null, playlist = null, back1 = null, back2 = null;
    //Paneli oluþturma metodu
    public musicpanel(){
        //Panel boyutlarýný ayarlar
        setSize(width, height);
        //paneli görünür yapar
        setVisible(true);

        yenile();

        //Ayrý bir iþlem parçasý olarak yürümesini söylüyoruz ki böylece program açýlýrken bir beyaz ekran görmeyelim
        new Thread(){
            @Override
            public void run() {
                super.run();

                //Müzikler fotoðrafýný yüklemek için bir InputStream yani okuma kanalý açar
                InputStream imgStream = musicapp.instance.getClass().getResourceAsStream("images/musics.png");
                try {
                    //okuma kanalýný resime okutunca resmimiz yüklenir
                    muzikler = ImageIO.read(imgStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                imgStream = musicapp.instance.getClass().getResourceAsStream("images/playlist.png");
                try {
                    playlist = ImageIO.read(imgStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                imgStream = musicapp.instance.getClass().getResourceAsStream("images/back1.png");
                try {
                    back1 = ImageIO.read(imgStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imgStream = musicapp.instance.getClass().getResourceAsStream("images/back2.png");
                try {
                    back2 = ImageIO.read(imgStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //Fare hareketlerini kontrol ettiðimizde belli bir alana girince efekt vermesini istediðimiz için kontrol deðiþkenleri oluþturduk.
    boolean muziklerefekt = false, playlistefekt = false, backefekt = false;

    //Sayfa sistemi
    boolean muziksayfa, playlistsayfa;

    int boyay = 0;

    //boyama metodu bu sayede istediðimiz gibi paneli kullanýcaz.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(muziksayfa){
            //TODO Müzik sayfasý
            //Arka plan
            g.setColor(Color.darkGray);
            g.fillRect(0,0, width, height);

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(Color.decode("#d19900"));
            g2.setFont(new Font("Century Gothic", Font.PLAIN, (int)(getWidth()/100*2.5f)));
            g2.drawString("Müzikler ", 30, 30);


            g2.drawString("Adý", 100, 80);
            g2.drawString("Sanatçý", 370, 80);
            g2.drawString("Albüm", 570, 80);

            int y = 0;
            g2.setFont(new Font("Century Gothic", Font.PLAIN, (int)(getWidth()/100*1.7f)));
            for(Map.Entry<Integer, music> m : music.musics.entrySet()){
                if(boyay == ((y+20)/20)){
                    g2.setColor(Color.gray);
                    g2.fillRect(0, 85+y, width, 20);
                }

                g2.setColor(Color.decode("#c2c9a5"));
                g2.drawString(m.getValue().getMuzikismi(), 100, 100+y);
                g2.drawString(m.getValue().getMuziksanatci(), 370, 100+y);
                g2.drawString(m.getValue().getMuzikalbum(), 570, 100+y);

                g2.setColor(Color.orange);
                g2.drawOval(850, 100+y - 15, 15, 15);
                if(users.playlist.contains(m.getValue())){
                    g2.setColor(Color.green);
                    g2.fillOval(850 + 2, (int)(100+y - 12.5f), 12, 12);
                }

                y+=20;
            }

            if(backefekt)
                g2.drawImage(back2, 20, height-110, null);
            else
                g2.drawImage(back1, 20, height-110, null);
        }else if(playlistsayfa){
            //TODO Playlist sayfasý
            //Arka plan
            g.setColor(Color.darkGray);
            g.fillRect(0,0, width, height);

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(Color.decode("#d19900"));
            g2.setFont(new Font("Century Gothic", Font.PLAIN, (int)(getWidth()/100*2.5f)));
            g2.drawString("Calma Listesi ", 30, 30);


            g2.drawString("Adý", 100, 80);
            g2.drawString("Sanatçý", 370, 80);
            g2.drawString("Albüm", 570, 80);

            int y = 0;
            g2.setFont(new Font("Century Gothic", Font.PLAIN, (int)(getWidth()/100*1.7f)));
            for(music m : users.playlist){
                if(boyay == ((y+20)/20)){
                    g2.setColor(Color.gray);
                    g2.fillRect(0, 85+y, width, 20);
                }

                g2.setColor(Color.decode("#c2c9a5"));
                g2.drawString(m.getMuzikismi(), 100, 100+y);
                g2.drawString(m.getMuziksanatci(), 370, 100+y);
                g2.drawString(m.getMuzikalbum(), 570, 100+y);

                g2.setColor(Color.red);
                g2.fillRect(850, 100+y - 15, 15, 15);

                y+=20;
            }

            if(backefekt)
                g2.drawImage(back2, 20, height-110, null);
            else
                g2.drawImage(back1, 20, height-110, null);
        }else{
            //TODO Anasayfa kýsmý
            //boyanýn rengini ayarlar
            g.setColor(Color.darkGray);
            //içi dolu bir kare çizdirir (X 0 ve Y 0 dan baþlar panelin sonuna kadar çizer)
            g.fillRect(0,0, width, height);

            //Müzikler alanýna girince o kýsmý parlak yapmak istiyoruz.
            if(muziklerefekt){
                g.setColor(Color.lightGray);
                g.fillRect(0, 0, width/2, height);

                //Playlist alanýna girince o kýsmý parlak yapmak istiyoruz.
            }else if(playlistefekt){
                g.setColor(Color.lightGray);
                g.fillRect(width/2, 0, width, height);
            }

            //Müzikler resmini çizmek istiyoruz
            g.drawImage(muzikler, 0, height/2 - 256, null);
            //Playlist resmini çizmek istiyoruz
            g.drawImage(playlist, width/2, height/2 - 256, null);
        }
    }

    //Görüntüyü her 16ms de bir yenileyecek yani yaklaþýk her saniye 60 kere yenilenecek bu da 60FPS demek.
    void yenile(){
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        };
        Timer t = new Timer();
        t.scheduleAtFixedRate(tt, 0, 16);
    }

}
