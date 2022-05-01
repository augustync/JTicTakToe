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
 * klasa odpowiedzialna za pole w meni exit
 * wylapuje zdarzenia zwiazane Exit
 */
public class Exit implements ActionListener{
    public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
 }
