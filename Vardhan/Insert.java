import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

class Insert implements ActionListener{
    Frame insert = new Frame();
    static String subjectName;

    Label l1 = new Label("Choose a subject to INSERT/MODIFY/DELETE");
   	Label l2 = new Label("Choose TYPE of question");
    Choice c1 = new Choice();
    Choice c2 = new Choice();

	Button b1 = new Button("Go");
	Button b2 = new Button("Back to Menu");

	Insert(){
        l1.setBounds(65,250,300,20);
        c1.setBounds(75,280,200,20);
        l2.setBounds(520,250,300,20);
        c2.setBounds(500,280,200,20);

        b1.setBounds(250,400,300,25);
        b2.setBounds(250,430,300,25);
  
        insert.add(l1);
        insert.add(l2);        
        insert.add(b1);
        insert.add(b2);

        insert.add(c1);
        c1.add("Phy");
        c1.add("Chem");
       	//c1.add("Math");
        insert.add(c2);
        c2.add("MCQ");
        c2.add("TrueFalse");
        c2.add("FillInTheBlanks");

        b1.addActionListener(this);
        b2.addActionListener(this);

        insert.setLayout(null);
        insert.setVisible(true);
        insert.setSize(800,800);
	}

	public void actionPerformed(ActionEvent e){
		subjectName = c1.getItem(c1.getSelectedIndex());
		String qtype = c2.getItem(c2.getSelectedIndex());

		if(e.getSource() == b1){	//Go Button
			if(qtype.equals("MCQ") == true){
				new MultiQues();
				insert.setVisible(false);
			}
			else if(qtype.equals("TrueFalse") == true){
				new TrueFalse();
				insert.setVisible(false);
			}
			else if(qtype.equals("FillInTheBlanks") == true){
				new FillinTheBlanks();
				insert.setVisible(false);
			}
		}
	
		else if(e.getSource() == b2){	//BACK TO MENU BUTTON
			new Quiz();
			insert.setVisible(false);
		}
	}

	public static void main(String args[]){
		new Insert();
	}

	public static String SubjectName(){
		return subjectName;
	}
}