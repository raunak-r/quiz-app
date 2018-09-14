import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

class MainPage implements ActionListener{
    JFrame mainpage = new JFrame();

    JButton b1=new JButton("Make Changes");
    JButton b4=new JButton("Generate");

    MainPage(){
        b1.setBounds(50,300,300,30);
        b4.setBounds(50,350,300,30);

        mainpage.add(b1);
        mainpage.add(b4);

        b1.addActionListener(this);
        b4.addActionListener(this);

       mainpage.setLayout(null);
       mainpage.setVisible(true);
       mainpage.setSize(400,600);    
    }

    public void actionPerformed(ActionEvent e){
        /*THESE THREE FUNCTIONS ARE DONE VIA SAME FRAME*/
        if(e.getSource()==b1){      //Subject
            new Subject();
            mainpage.setVisible(false);
        }
        
        if(e.getSource()==b4){
            new Subject();         //Generate
            mainpage.setVisible(false);
        }
        
    }

    public static void main(String args[]){
        new MainPage();
    }
}