/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kolkokrzyzyk;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Adas
 * klasa odpowiedzialna jest za interfejs uzytkownika aplikacji
 * jak za razem troszki pomaga klasie logika gry
 */
public class PanelKolkoKrzyzyk extends JPanel implements MouseListener{
    /**zmienna mowi czyja tura*/
    public boolean turn=false;
    /**zmienna ktora pozwala uruchomic gre*/
    public boolean start=false;
   /**zmienna pomaga przy sprawdzaniu warunkow wygrania zmana jej nastepuje w metodzie checkWin()*/
    public boolean state=true;
     /**zmienia zmienia sie po skonczonej grze zmieniajac zaczynajacego gre*/
    public boolean takeTurns=false;
    /**tablica zawierajaca puste obrazki i zarazem w tej tablicy trzymany jest
     * stan dla gry(jesli chodzi o wizualna czesc)
     */
    public Image pusta[][]= new Image[LogikaGry.SIZEPLANSZA][LogikaGry.SIZEPLANSZA];
    /** obrazki z kolkiem*/
    public Image o[][]= new Image[LogikaGry.SIZEPLANSZA][LogikaGry.SIZEPLANSZA];
    /** obrazki z krzyzykiem*/
    public Image x[][]= new Image[LogikaGry.SIZEPLANSZA][LogikaGry.SIZEPLANSZA];
    /** obrazek wyswietlany na starcie gry*/
    public Image demo;
    /** wywolanie klasy logika gry*/
    public LogikaGry l;

    /**konstrukto klasy*/
    public PanelKolkoKrzyzyk() {
        setPreferredSize(new Dimension(320, 240));
        setLayout(null);
        addMouseListener(this);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setBackground(new java.awt.Color(0, 0, 0));
        //readPicture();
        l= new LogikaGry();
    }// koniec konstruktora
    /**metoda rysujaca obiekty w oknie aplikacji*/
    @Override
     public void paint(Graphics g){
         Graphics2D g2=(Graphics2D) g;
         g2.clearRect(0, 0, getWidth(), getHeight());
         g2.setColor(new java.awt.Color(0,0,0));
         g2.fillRect(0, 0, getWidth(), getHeight());
         showPicture(g2, pusta);

    }// koniec paint

    /**metoda wczytuje wszystkie obrazki do tablic i do zmiennej*/
    public void readPicture(){
        try{
        demo= new ImageIcon(this.getClass().getResource("/kolkokrzyzyk/demo.png")).getImage();
        }catch(Exception e){
            System.out.println("Blad wczytywania planszy startowej "+e.getMessage());
        }
        int z=1;
        try{
        for (int i = 0; i < pusta.length; i++) {
            for (int j = 0; j < pusta[i].length; j++) pusta[i][j]= new ImageIcon(this.getClass().getResource("/kolkokrzyzyk/pusta/p"+(z++)+".jpg")).getImage();
        }
        }catch(Exception e){
            System.out.println("Blad wczytywania planszy pustej "+e.getMessage());
        }
        z=1;
        try{
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) x[i][j]= new ImageIcon(this.getClass().getResource("/kolkokrzyzyk/x/x"+(z++)+".jpg")).getImage();
        }
        }catch(Exception e){
            System.out.println("Blad wczytywania planszy z X "+e.getMessage());
        }
        z=1;
        try{
        for (int i = 0; i < o.length; i++) {
            for (int j = 0; j < o[i].length; j++) o[i][j]= new ImageIcon(this.getClass().getResource("/kolkokrzyzyk/o/o"+(z++)+".jpg")).getImage();
        }
        }catch(Exception e){
            System.out.println("Blad wczytywania planszy z O "+e.getMessage());
        }
    }// koniec redPicters()
    /**metoda odpowiedzialna za pozycje wyswietlania obrazkow*/
    @SuppressWarnings("empty-statement")
    public void showPicture(Graphics2D g2,Image picter[][]){
        int r=75, c=65;
        if(!start) g2.drawImage(demo, 0, -5, null);
        else{
         for (int i = 0; i < picter.length; i++) {
            for (int j = 0; j < picter[i].length; j++){
                if(i==0 && j==1) r+=58;
                else if(i==0 && j==2) r+=59;
                else if(i==1 && j==0){
                    r=53;
                    c+=27;
                }else if(i==1 && j==1) r+=76;
                else if(i==1 && j==2) r+=71;
                else if(i==2 && j==0){
                    r=31;
                    c+=36;
                }else if(i==2 && j==1) r+=90;
                else if(i==2 && j==2) r+=88;
                g2.drawImage(picter[i][j], r, c, null);
            }
        }
        }
    }// koniec showPicters
/**metoda odpowiedzialna za zmiane obrazkow*/
    public void switchPicture( int nur, int nuc){
        if(!turn){
            pusta[nur][nuc]=null;
            pusta[nur][nuc]=o[nur][nuc];
            turn=true;
        }else if(turn){
            pusta[nur][nuc]=null;
            pusta[nur][nuc]=x[nur][nuc];
            turn=false;
        }
    }// koniec swich pictures()
/**metoda odpowiedzialna za wyczyszczenie pol i wczytanie na nowo obrazkow*/
    public void newGame(){
        turn=false;
        start=true;
        state=false;
        readPicture();
        l.makeBoardEmpty();
        repaint();
        if(takeTurns){
            turn=true;
            l.setGracz((l.getGracz() == 'o') ? 'x' : 'o');
            move(l.getGracz(), 0, 0);
            repaint();
        }
    }
/**metoda odpowiada za ruch gracz i komputera*/
    public void move(char gracz,int nur, int nuc){
        char tab[][]=l.getBoard();
        
        if(!turn && gracz=='o'){
            if(tab[nur][nuc]==' '){
            tab[nur][nuc]='o';
            switchPicture(nur, nuc);
            l.setBoard(nur, nuc, gracz);
            }
        }else{
            
            l.comp();
            if(((l.getWi()>=0)&&(l.getWi()<=2))&&((l.getWj()>=0)&&(l.getWj()<=2))&& (tab[l.getWi()][l.getWj()]==' ')){
                tab[l.getWi()][l.getWj()]='x';
                l.setBoard(l.getWi(), l.getWj(), gracz);
                switchPicture(l.getWi(), l.getWj());
            }
        }
        gracz = (gracz == 'o') ? 'x' : 'o';
        l.setGracz(gracz);
    }
/**metoda sprawdza warunki wygrania lub remisu*/
    public boolean checkWin(){
        boolean how=false;
         if(l.howWin('x') && !how){
             state=true;
             how=true;
         }
         if(state && how){
            javax.swing.JOptionPane.showMessageDialog(this,"Wygral Krzyzyk","WYGRAL",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource("/kolkokrzyzyk/cross.gif")));
            how=false;
            takeTurns=(takeTurns )? false : true;
         }
        if(l.howWin('o') && !how){
             state=true;
             how=true;
         }
         if(state && how){
            javax.swing.JOptionPane.showMessageDialog(this,"Wygralo Kolko","WYGRAL",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource("/kolkokrzyzyk/not.gif")));
            how=false;
            takeTurns=(takeTurns )? false : true;
         }
         if(l.draw() && !how){
             state=true;
             how=true;
         }
         if(state && how){
             javax.swing.JOptionPane.showMessageDialog(this,"Niestety nikt nie wyglral mamy remis","REMIS",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource("/kolkokrzyzyk/draw.gif")));
             takeTurns=(takeTurns )? false : true;
         }
         return state;
    }
/**metoda obsluguje zdarzenia wywolane przez myszke*/
    @SuppressWarnings("deprecation")
    public void mouseClicked(MouseEvent evt){
        int numberRow=0, numberCol=0;
        int wx=evt.getX();
        int wy=evt.getY();
        boolean ruch=false;
        
        if(wx>=88 && wx<=131 && wy>=65 && wy <89 && !state){
           numberRow=0;
           numberCol=0;
           ruch=true;
        }else if(wx>131 && wx<=183 && wy>=65 && wy <89 && !state){
            numberRow=0;
            numberCol=1;
            ruch=true;
        }else if(wx>183 && wx<=227 && wy>=65 && wy <89 && !state){
            numberRow=0;
            numberCol=2;
            ruch=true;
        //===============================================
        }else if(wx>74 && wx<=127 && wy>=89 && wy <126 && !state){
            numberRow=1;
            numberCol=0;
            ruch=true;
        }else if(wx>127 && wx<=189 && wy>=89 && wy<126 && !state){
            numberRow=1;
            numberCol=1;
            ruch=true;
        }else if(wx>189 && wx<=240 && wy>=89 && wy<126 && !state){
            numberRow=1;
            numberCol=2;
            ruch=true;
        //================================================
        }else if(wx>53 && wx<=119 && wy>=126 && wy<=165 && !state){
            numberRow=2;
            numberCol=0;
            ruch=true;
        }else if(wx>119 && wx<=198 && wy>=126 && wy<=165 && !state){
            numberRow=2;
            numberCol=1;
            ruch=true;
        }else if(wx>198 && wx<=262 && wy>=126 && wy<=165 && !state){
            numberRow=2;
            numberCol=2;
            ruch=true;

        }else if(wx>32 && wx<=111 && wy>=126 && wy<=165 && !state){
            numberRow=2;
            numberCol=0;
            ruch=true;
        }else if(wx>109 && wx<=206 && wy>=126 && wy<=165 && !state){
            numberRow=2;
            numberCol=1;
            ruch=true;
        }else if(wx>206 && wx<=282 && wy>=126 && wy<=165 && !state){
            numberRow=2;
            numberCol=2;
            ruch=true;
        }
        if(!state && ruch){
            move(l.getGracz(),numberRow, numberCol);
            if(!state)
               move(l.getGracz(),numberRow, numberCol);
            repaint();
            state=checkWin();
        }
    }
    /** metody abstrakcyjne*/
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}

}
