import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

class Generate implements ActionListener{
    Frame generate = new Frame();
    static String subjectName;
    static int totalQues;

    Label l1 = new Label("Choose the subject to generate questions from.");
   	//Label l2 = new Label("Choose TYPE of question");
    Choice c1 = new Choice();
   // Choice c2 = new Choice();


    Label labelAvail = new Label("Total Available Questions");
	TextField tfTotalQues = new TextField();

    Label labelQues = new Label("Number of Questions Required");
    TextField tfQues = new TextField();

	Button b1 = new Button("Generate");
	Button b2 = new Button("Back to Menu");

	Generate(){
		totalQues = Quiz.totalQuestion(0);
        
        l1.setBounds(80,250,300,20);
        c1.setBounds(50,280,300,20);
        //l2.setBounds(100,110,300,20);
        //c2.setBounds(50,140,300,20);

        labelAvail.setBounds(480,250,300,20);
        tfTotalQues.setBounds(510,280,40,20);
        labelQues.setBounds(250,350,300,20);
        tfQues.setBounds(250,380,300,20);

        b1.setBounds(250,500,300,25);
        b2.setBounds(250,530,300,25);
  
        generate.add(l1);
        //generate.add(l2);        
        generate.add(b1);
        generate.add(b2);
        generate.add(labelAvail);
        generate.add(tfTotalQues);
        tfTotalQues.setText(String.valueOf(totalQues));

        generate.add(labelQues);
        generate.add(tfQues);

        generate.add(c1);
        c1.add("All");
        c1.add("Phy");
        c1.add("Chem");
       	//c1.add("Math");
        /*generate.add(c2);
        c2.add("All");
        c2.add("MCQ");
        c2.add("TrueFalse");
        c2.add("FillInTheBlanks");*/

        b1.addActionListener(this);
        b2.addActionListener(this);

        generate.setLayout(null);
        generate.setVisible(true);
        generate.setSize(800,800);
	}

	public void actionPerformed(ActionEvent e){
		subjectName = c1.getItem(c1.getSelectedIndex());  //Get the Name of the subject from which ques are to be generated.
        subjectName = SubjectName();   //Call this method at Line100 to reduce it to 1 character

		//String qtype = c2.getItem(c2.getSelectedIndex());
        
		if(e.getSource() == b1){	//Generate Button
			int ques = Integer.parseInt(tfQues.getText());
			//System.out.println("b1 clicked:");
            /*if( tfQues.getText().trim().equals("")){ //If Textfield is empty
                JOptionPane.showMessageDialog(null, "Enter the number of Questions");
            }*/
            if( ques >= (totalQues+1)){
                JOptionPane.showMessageDialog(null, "Enter a value less than the total");
            }
            else{
                /*THIS ORDER OF EXECUTION IS IMPORTANT*/
                new ShowQuestion();     //Show this frame to display questions.
                ShowQuestion.generateQuestions(ques);   //Call method inside that to generate questions. 
                generate.setVisible(false);
            }
		}
	
        if(e.getSource() == b2){    //BACK TO MENU BUTTON
            //System.out.println("b2 clicked");
            new Quiz();
            generate.setVisible(false);
        }
		
	}

	public static void main(String args[]){
		new Generate();
	}

	public static String SubjectName(){
        if(subjectName.equals("Phy"))
            subjectName = "P";
        else    //for Chem
            subjectName = "C";
        //else let it be All

		return subjectName;
	}
}