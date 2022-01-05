//Jason Zhagui
//Homework 4
package client;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Thread.sleep;

public class Client {
    
    static Socket s;
    static Scanner sin;
    static PrintStream sout;
    static String ip = "1";
    static String name;
    static boolean connected = false;
    
    static JTextArea messageArea = new JTextArea("Type in Name: ",25, 40);
    static JTextField textField = new JTextField(40);

    
    public static void main(String[] args) {
        //creates GUI
        JFrame jf = new JFrame("ChatBox");        
        JPanel top = new JPanel();
        
        JPanel bottom = new JPanel();
        bottom.setLayout(new  GridLayout(2,1));
        
        JButton send = new JButton("Send"); 
        send.addActionListener(new AnotherHandler());     
       
        bottom.add(textField);
        bottom.add(send);
        
        messageArea.setEditable(false);
        top.add(messageArea, BorderLayout.CENTER);
        
        jf.add(top);
        jf.add(bottom, BorderLayout.SOUTH);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500,500);
        jf.setVisible(true);
        
        //Trys connecting to server until it connects
        //Default ip to 1 to prevent from connecting
        while(true){
            try{
                
                if(ip!="1"){
                    messageArea.append("Trying to connect to "+ip+"...\n");
                }
                //Tries to connect with ip sometimes takes a while
                s = new Socket(ip,5190);
                if (s.isConnected()){
                    //let client know we  connected and enable textfield to chat
                    connected=true;
                    textField.setEditable(true);
                    
                    sin = new Scanner(s.getInputStream());
                    sout = new PrintStream(s.getOutputStream());
                    //sends first thing to server which is name
                    sout.println(name);

                    while (sin.hasNextLine()){
                        var line = sin.nextLine();
                        System.out.println(line);
                        messageArea.append(line+"\n");
                    }
                    s.close();
                    break;
                }
                //If closed resets ip to 1
                ip="1";
            } catch (IOException ex){
                //If invalid ip reset to 1
                if(ip!="1"){
                    messageArea.append("Welp.... Couldn't connect!\n");
                    messageArea.append("Type in Server IP: ");
                }
                textField.setEditable(true);
                ip="1";
                //prevents a lot of spam but stills spams :/
                try {
                    sleep(2000);
                } catch (InterruptedException ex1) {
                }     
                continue;
            }
        }  
    }
}

class AnotherHandler implements ActionListener{
    String text;
    @Override
    public void actionPerformed(ActionEvent e){
        //Get name first 
        if(Client.name==null){
            text = Client.textField.getText();
            Client.name = text;
            Client.textField.setText("");
            Client.messageArea.append(text+"\n"); 
            Client.messageArea.append("Type in Server IP: ");
        }
        //Once name is written get an ip from user
        else if(Client.ip == "1"){
            text = Client.textField.getText();
            Client.ip = text;
            Client.textField.setText("");
            Client.messageArea.append(text+"\n");
            //Once it gets ip from user disbale textfield until a response from
            //client is made (connection=true)
            Client.textField.setEditable(false);
        }
        //Once name is ip is valid it sends data to server 
        else if (Client.connected){
            text = Client.textField.getText();
            Client.sout.println(text);
            Client.textField.setText("");
        } 
    }
}


