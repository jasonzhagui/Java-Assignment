//Jason Zhagui
//Homework 4
package server;
import java.io.*;
import java.util.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
  

public class Server {
    static int portNum=5190;
    static PrintStream[] psLst = new PrintStream[0];
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(portNum);
            System.out.println("Now listening on port "+portNum);
            int i=0;
            while (true){
                Socket clientSock = ss.accept(); //Blocking system call
                ProcessConnection pc = new ProcessConnection(clientSock,++i);
                pc.start();          
            }
        } catch (IOException ex) {
            System.out.println("Could not bind to port.");
        }   
    }  
}
class ProcessConnection extends Thread{
    Socket clientSock;
    int clientID;
    String name;
    int index;
    ProcessConnection(Socket newSock, int newID){
        clientSock = newSock;
        clientID = newID;
    }
    public void run(){
        try{
            System.out.println("Got a connection from "+clientSock.getInetAddress()+" clientid: "+clientID);
            Scanner sin = new Scanner(clientSock.getInputStream());
            //adds new printStream to array for new client
            Server.psLst = Arrays.copyOf(Server.psLst,Server.psLst.length+1);
            index = Server.psLst.length - 1;
            //gets the printstream for this client
            Server.psLst[Server.psLst.length - 1] = new PrintStream(clientSock.getOutputStream());
      
            String line = "";
            //Gets the first thing recieved from client which is name and sends
            PrintStream thisPrintStream =  Server.psLst[index];
            name=sin.nextLine();
            thisPrintStream.println(name);
            //Reads until exit command is sent 
            while (!line.equalsIgnoreCase("EXIT")){
                //Gets new line
                line=sin.nextLine();
                if(!line.equalsIgnoreCase("EXIT")){
                    //Sends next line to all clients
                    for(PrintStream ps: Server.psLst){
                        ps.println(name+" said: "+line);     
                    }
                    System.out.println(name+" said: "+line); 
                }    
            }
            System.out.println(name+" left");
            clientSock.close();
        }
        catch(IOException ex){}
    }
}