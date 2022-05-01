/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**klasa nalezy do pakietu kolkokrzyzyk*/
package kolkokrzyzyk;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Adas
 * zawarta mala informacja o projekcje
 */
public class AboutMe extends JFrame{
    /** contener dla okna*/
    public JPanel panel;
    /** pole wpisanej wiadomosci*/
    public JTextArea pole;
    /** przycisk ok*/
    public JButton ok;
    /** contener dla przycisku*/
    public JPanel panel1;
    /** konstruktor klasy*/
    public AboutMe() {
        super("About me");
        java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        initComponents();
        setSize(200, 200);
        //getRootPane().setWindowDecorationStyle(6);
        setLocation((int)((int) dim.getWidth() / 3.55),(int) (dim.getHeight() / 4));
        pack();
    }
    /**metoda wywolana w Konstruktorze, zawarte sa wniej wszystkie componety potzrebne do zainicjalizowania */
    public void initComponents(){
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        panel= new JPanel();
        panel1= new JPanel();
        pole= new JTextArea();
        ok= new JButton("OK");
        panel.setLayout(new java.awt.BorderLayout());
        panel1.setLayout(new java.awt.FlowLayout());
        setLocationByPlatform(true);
        setResizable(false);
        //setUndecorated(true);
        pole.setText(" Autor gry: nonus25\n Tytuł gry: Kolko i krzyzyk \n Vesion 1.0\n Gra stworzona na potrzeby\n Przedmiotu:\n Programowanie Obiektowe\n\n\n\n chmielua@gmail.com");
        pole.setEditable(false);
        pole.setBackground(new java.awt.Color(0.3f,0.7f,0.6f,1f));
        panel.add(pole,java.awt.BorderLayout.CENTER );

        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.gc();
                dispose();
            }

        });
        panel.add(panel1,java.awt.BorderLayout.SOUTH);
        panel1.add(ok);
        panel1.setBackground(new java.awt.Color(0.3f,0.7f,0.6f,1f));
        getContentPane().add(panel, java.awt.BorderLayout.CENTER);
        
        pack();
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AboutMe().setVisible(true);
            }
        });
    }

}
