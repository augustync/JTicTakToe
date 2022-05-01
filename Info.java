/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kolkokrzyzyk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Adas
 * klasa wylapuje zdarzenia dla pola Info w menu
 */
public class Info implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AboutMe().setVisible(true);
            }
        });
    }

}
