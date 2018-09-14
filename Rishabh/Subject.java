import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

class Subject implements ActionListener{
    JFrame subject = new JFrame();
    static String subjectName;
    static String qT;
    static int ques;

    JLabel l1 = new JLabel("Choose subject");
   	JLabel l2 = new JLabel("Choose TYPE of question");
    Choice c1 = new Choice();
    Choice c2 = new Choice();

	JButton b1 = new JButton("Go");
	JButton b2 = new JButton("Back to Menu");
		
	Label q1 = new Label("Enter Number of Questions");
	TextField q2 = new TextField();
	JButton b3 = new JButton("Generate");

	Subject(){
        l1.setBounds(50,100,300,20);
        c1.setBounds(50,130,200,20);
        l2.setBounds(50,180,300,20);
        c2.setBounds(50,210,200,20);

        b1.setBounds(50,250,300,25);
        b2.setBounds(50,280,300,25);
  
        subject.add(l1);
        subject.add(l2);        
        subject.add(b1);
        subject.add(b2);

        subject.add(c1);
        c1.add("Phy");
        c1.add("Chem");
        subject.add(c2);
        c2.add("MCQ");
        c2.add("TrueFalse");
        c2.add("FillInTheBlanks");

        q1.setBounds(50,350,300,25);
        q2.setBounds(50,380,300,25);
        b3.setBounds(50,410,300,25);
        subject.add(q1);
        subject.add(q2);
        subject.add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        subject.setLayout(null);
        subject.setVisible(true);
        subject.setSize(400,600);
	}

	public void actionPerformed(ActionEvent e){
		subjectName = c1.getItem(c1.getSelectedIndex());
		qT = c2.getItem(c2.getSelectedIndex());
		
		if(e.getSource() == b1){	//Go Button
			new QuesBank();
			subject.setVisible(false);
		}
		else if(e.getSource() == b2){	//BACK TO MENU BUTTON
			new MainPage();
			subject.setVisible(false);
		}
		else if(e.getSource() == b3){	//BACK TO MENU BUTTON
			ques = Integer.parseInt(q2.getText());
			new Generate();
			subject.setVisible(false);
		}
	}

	public static void main(String args[]){
		new Subject();
	}

	public static String subjectName(){
		if(subjectName.equals("Phy"))
			subjectName = "P";
		if(subjectName.equals("Chem"))
			subjectName = "C";

		System.out.println(subjectName);
		return subjectName;
	}

	public static String qType(){
		if(qT.equals("TrueFalse"))
			qT = "T";
		if(qT.equals("FillInTheBlanks"))
			qT = "F";
		if(qT.equals("MCQ"))
			qT = "M";

		System.out.println(qT);
		return qT;
	}

	public static int GenQues(){
		return ques;
	}
}