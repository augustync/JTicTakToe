/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kolkokrzyzyk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UnsupportedLookAndFeelException;
/**
 *
 * @author Adas
 * glowna klasa projektu
 * ustala rozmiar okna,
 * i tzryma wszystkie dodane do niej komponety
 */
public class KolkoKrzyzyk extends JFrame {
    /** zmiena dla klasy PanelKolkoKzyzyk*/
    public PanelKolkoKrzyzyk p;
    /** zmienna paska menu czyli calego bara gdzie jest mozliwy wybor*/
    public JMenuBar jMenuBar1;
    /** trzyma 1 menu cyli 'Menu' */
    public JMenu jMenu1;
    /** pola dostepne po otworzeniu 'Menu' */
    public JMenuItem jMenuItem1;
    public JMenuItem jMenuItem2;
    /** pola dostepne po otworzeniu 'Info' */
    public JMenu jMenu2;
    public JMenuItem jMenuItem3;

//
    /** konstruktor klasy*/
    public KolkoKrzyzyk(){
        super("Kolko i krzyzyk");
        initComponents();
        p.readPicture();
        this.setSize(340,240);
        pack();
    }

    @SuppressWarnings("unchecked")
    private void initComponents(){
        p= new PanelKolkoKrzyzyk();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        //java.util.Properties
        //System.getProperty("/kolkokrzyzyk/cross.gif");
        //f.setIconImage(null);
        //f.setIconImage(new ImageIcon(this.getClass().getResource("/kolkokrzyzyk/cross.gif")).getImage());
        //java.net.URL url = kolkokrzyzyk.KolkoKrzyzyk.class.getResource("cross.bmp");
        //java.awt.Toolkit kit = java.awt.Toolkit.getDefaultToolkit();
        //Image img = kit.createImage(url);
        this.setIconImage(new ImageIcon(getClass().getResource("/kolkokrzyzyk/cross.gif")).getImage());
        setResizable(false);
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KolkoKrzyzyk.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(KolkoKrzyzyk.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(KolkoKrzyzyk.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(KolkoKrzyzyk.class.getName()).log(Level.SEVERE, null, ex);
        }
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(jMenuBar1, java.awt.BorderLayout.NORTH);
        getContentPane().add(p,java.awt.BorderLayout.CENTER);
        
        jMenu1.setText("Menu");

        jMenuItem1.setText("Nowa gra");
        jMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // tworzymy AudioStream obiekt
                //AudioStream as = null;
                 p.newGame();
                 
                /*
                try { 
                    //java.net.URL url = ClassLoader.getSystemResource("/kolkokrzyzyk/audio/ding.au");
                    InputStream in = new FileInputStream("/kolkokrzyzyk/audio/return.au");
                    as = new AudioStream(in);
                    // uzywamy statycznej klasy "player" z klasy AudioPlayer i play
                    // clip.
                    AudioPlayer.player.start(as);
                    // tak samo, do stop .
                    AudioPlayer.player.stop(as);
                    // to do appleta tez nie dziala play(getCode, "audio/ding.au");
                } catch (IOException ex) {
                    System.out.println("nie wczytano dzwieku");
                    System.out.println(ex.getMessage()+" "+ex.getLocalizedMessage());
                } finally {
                    try {
                        as.close();
                    } catch (IOException ex) {
                       System.out.println(ex.getMessage()+" "+ex.getLocalizedMessage());
                    }
                }
                 */
            }
        });
        
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Zakoncz");
        jMenuItem2.addActionListener(new Exit());
        jMenu1.add(jMenuItem2);
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Info");

        jMenuItem3.setText("About this");
        jMenuItem3.addActionListener(new Info());
        jMenu2.add(jMenuItem3);
        jMenuBar1.add(jMenu2);

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setJMenuBar(jMenuBar1);
        java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            //style okienka jawy musi byc ustawiona w tym przypadku setUndecorated(false)
        setLocation((int)((int) dim.getWidth() / 2.5),(int) (dim.getHeight() / 4));
        javax.swing.SwingUtilities.updateComponentTreeUI(this);
        pack();
    }
 
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                         new KolkoKrzyzyk().setVisible(true);
            }
        });
    }
}
