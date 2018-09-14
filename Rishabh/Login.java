import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Login implements ActionListener{
    
    JFrame f=new JFrame();

    JLabel l1=new JLabel("Username:");
    JLabel l2=new JLabel("Password:");
    
    JTextField t1=new JTextField();
    JTextField t2=new JTextField();
    
    JButton b1=new JButton("Login");
    
    Login()
    {
        l1.setBounds(50,100,300,20);
        t1.setBounds(50,150,300,20);

        l2.setBounds(50,200,300,20);
        
        
        t2.setBounds(50,250,300,20);
        //t2.setEchoChar('*');
        
        b1.setBounds(50,300,300,20);
        
        f.add(l1);
        f.add(l2);
        
        f.add(t1);
        f.add(t2);
        
        f.add(b1);
        
        b1.addActionListener(this);
        
        f.setLayout(null);
        f.setVisible(true);
        f.setSize(400,400);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b1 ){
            if(t1.getText().equals("Rishabh") && t2.getText().equals("Rishabh") )
            {
                new MainPage();
                f.setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Enter details");
            }
        }

    }
    
    public static void main(String args[]){
        new Login();
    }
}