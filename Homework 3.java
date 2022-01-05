//Jason Zhagui Homework 2
package buttonshomework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.Random;

public class ButtonsHomework {
    //Creates label and array of buttons, switches and random 
    static JLabel jl;
    static JButton[] buttons = new JButton[8];
    static boolean[] switches = new boolean[8];
    static Random random = new Random();
    
    public static void main(String[] args){
        //starts timer
        new Timer().start();
        //Creates the window 
        JFrame jf = new JFrame("BUTTONS!");
        JPanel jp = new JPanel();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400,400);
        //Creates the grid for buttons
        jp.setLayout(new GridLayout(4,2));
        jf.add(jp);
        //Fills the array of buttons
        for(int i = 0;i<buttons.length;i++ ){
            buttons[i] = new JButton("Button "+(i+1));  
        }
        //For each button a random color is created using random
        for(JButton button:buttons){
            int randomNum1 = random.nextInt(256);
            int randomNum2 = random.nextInt(256);
            int randomNum3 = random.nextInt(256);
            //Sets the background color so it correctly shows on macOS
            button.setBackground(new Color(randomNum1,randomNum2,randomNum3));
            button.setOpaque(true);
            button.setBorderPainted(false);
            //Creates the listener for each button
            button.addActionListener(new AnotherHandler());
            //adds each button to panel
            jp.add(button);
        }
        //Allows frame to be visible
        jf.setVisible(true);
    }
}
//This is the action listener 
class AnotherHandler implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        //Creates a new button
        JButton pressed;
        //New button is now the old the button
        pressed = (JButton)e.getSource();
        for(int i = 0;i<ButtonsHomework.buttons.length;i++ ){
            //If button is pressed toggle it off/on
            if(ButtonsHomework.buttons[i] == pressed){
                ButtonsHomework.switches[i]= !ButtonsHomework.switches[i];
            }
        }
    }
}
//This is the timer
class Timer extends Thread{
    //automitaclly runs when thread is started
    public void run(){
        //infinite loop for timer
        while(true){
            try {
                //waits 1 second
                sleep(1000);    
            } catch (InterruptedException ex) {}
            //Loop to look at which switches are toggled
            for(int i = 0;i<ButtonsHomework.switches.length;i++ ){
                if(ButtonsHomework.switches[i] == false){
                    //Change color for those that are toggled
                    int randomNum1 = ButtonsHomework.random.nextInt(256);
                    int randomNum2 = ButtonsHomework.random.nextInt(256);
                    int randomNum3 = ButtonsHomework.random.nextInt(256);
                    ButtonsHomework.buttons[i].setBackground(new Color(randomNum1,randomNum2,randomNum3));
                }
            }
        }
    }
}
       
