//Jason Zhagui Homework 2
package windowhomework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.Random;

public class WindowHomework {
    //Creates label and array of buttons and random 
    static JLabel jl;
    static JButton[] buttons = new JButton[8];
    static Random random = new Random();
    
    public static void main(String[] args){
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
            //adds each button to oanel
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
        //Changes each color of the button except the button that has been pressed
        for(JButton button:WindowHomework.buttons){
            if (button!=pressed){
                int randomNum1 = WindowHomework.random.nextInt(256);
                int randomNum2 = WindowHomework.random.nextInt(256);
                int randomNum3 = WindowHomework.random.nextInt(256);
                button.setBackground(new Color(randomNum1,randomNum2,randomNum3));
            }
        }     
    }
}
