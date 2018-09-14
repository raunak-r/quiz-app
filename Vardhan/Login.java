import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
class Login implements ActionListener{
	
    //Declaring Objects
    Frame f=new Frame();

    Label l1=new Label("Username:");
    Label l2=new Label("Password:");
    
    TextField t1=new TextField();
    TextField t2=new TextField();
    
    Button b1=new Button("Login");
    Button b2=new Button("Exit");
    
    Login()
    {
        //Giving Coordinates
        l1.setBounds(50,100,100,20);
        l2.setBounds(50,140,100,20);
        
        t1.setBounds(200,100,100,20);
        t2.setBounds(200,140,100,20);
        t2.setEchoChar('*');
        
        b1.setBounds(170,200,50,20);
        b2.setBounds(170,250,50,20);
        
        //Adding components to the frame
        f.add(l1);
        f.add(l2);
        
        f.add(t1);
        f.add(t2);
        
        f.add(b1);
        f.add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        f.setLayout(null);
        f.setVisible(true);
        f.setSize(400,400);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b1 ){
            if(t1.getText().equals("Hey") && t2.getText().equals("yup") )
            {
                new Quiz();
                f.setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please enter correct login details");
            }
        }
        if(e.getSource() == b2 ){
            System.exit(0);
        }
    }
    
    public static void main(String args[]){
        new Login();
    }
}