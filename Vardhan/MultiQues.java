import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*; 

class MultiQues implements ActionListener{
    Frame mcq = new Frame();
    static String subject;

    Label topic = new Label("MULTIPLE CHOICE QUESTIONS");
    Label labelInsert = new Label("INSERT New Question here");
	TextField tfInsert = new TextField();

	Label labelModifyDelete = new Label("Modify or Delete from here.");
    Choice choiceQuesList = new Choice();
    
    Label labelOption = new Label("Write the options here.");
	TextField tfOption1 = new TextField(); 
	TextField tfOption2 = new TextField(); 
	TextField tfOption3 = new TextField(); 
	TextField tfOption4 = new TextField(); 

	Label labelAnswer = new Label("Write the correct answer here.");
	TextField tfAnswer = new TextField();

	Button buttonInsert = new Button("Insert");
	Button buttonModify = new Button("Modify");
	Button buttonDelete = new Button("Delete");
	Button bMenu = new Button("Back to Menu");

	MultiQues(){
		/*IDENTIFY THE SUBJECT*/
		subject = Insert.SubjectName();
		if(subject.equals("Phy"))
			subject = "P";
		else
			subject = "C";

		topic.setBounds(325,40,150,20);
        labelInsert.setBounds(50,100,300,20);
        tfInsert.setBounds(50,130,300,20);

        labelModifyDelete.setBounds(450,100,300,20);
        choiceQuesList.setBounds(450,130,300,20);
        
        labelOption.setBounds(50,250,300,20);
        tfOption1.setBounds(50,280,300,20);
        tfOption2.setBounds(50,310,300,20);
        tfOption3.setBounds(50,340,300,20);
        tfOption4.setBounds(50,370,300,20);

        labelAnswer.setBounds(450,250,300,20);
        tfAnswer.setBounds(450,280,300,20);

        buttonInsert.setBounds(220,500,80,25);
        buttonModify.setBounds(350,500,80,25);
        buttonDelete.setBounds(480,500,80,25);
        bMenu.setBounds(250,600,300,25);

  		mcq.add(topic);
        mcq.add(labelInsert);
        mcq.add(tfInsert);
        mcq.add(labelModifyDelete);
        mcq.add(choiceQuesList);
        addQuestionsToList();
        mcq.add(labelOption);
        mcq.add(tfOption1);
        mcq.add(tfOption2);
        mcq.add(tfOption3);
        mcq.add(tfOption4);
        
        mcq.add(labelAnswer);
        mcq.add(tfAnswer);
        
        mcq.add(buttonInsert);
        mcq.add(buttonModify);
        mcq.add(buttonDelete);
        mcq.add(bMenu);

        buttonInsert.addActionListener(this);
        buttonModify.addActionListener(this);
        buttonDelete.addActionListener(this);
        bMenu.addActionListener(this);

        mcq.setLayout(null);
        mcq.setVisible(true);
        mcq.setSize(800,800);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == buttonInsert){	//Insert Button
			Insertion();
			JOptionPane.showMessageDialog(null, "Saved");
			new Quiz();
			mcq.setVisible(false);
		}
		else if(e.getSource() == buttonModify){	//BACK TO MENU BUTTON
			Modification();
			JOptionPane.showMessageDialog(null, "Modified");
			new Quiz();
			mcq.setVisible(false);
		}
		else if(e.getSource() == buttonDelete){
			Deletion();
			JOptionPane.showMessageDialog(null, "Deleted");
			new Quiz();
			mcq.setVisible(false);
		}
		else if(e.getSource() == bMenu){
			new Quiz();
			mcq.setVisible(false);
		}
	}

	public static void main(String args[]){
		new MultiQues();
	}

	public void Insertion(){
		String newQues = tfInsert.getText();
		String option1 = tfOption1.getText();
		String option2 = tfOption2.getText();
		String option3 = tfOption3.getText();
		String option4 = tfOption4.getText();
		String answer = tfAnswer.getText();
		
			try{
				String filepath = "MCQ.txt";
				FileWriter fw = new FileWriter(filepath,true);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(subject + "," + newQues + "," + option1 + "," + option2 + "," + option3 + "," + option4 + "," + answer);
				pw.flush();
				pw.close();

				//JOptionPane.showMessageDialog(null, "Saved");
				Quiz.totalQuestion(1);	//to increment the question by one
			}catch(Exception ae){
				JOptionPane.showMessageDialog(null, "NOT Saved");
			}
	}

	public void Modification(){
		Deletion();
		Insertion();
	}

	public void Deletion(){
		String ques = choiceQuesList.getItem(choiceQuesList.getSelectedIndex());
		
	try{
		File inputFile = new File("MCQ.txt");
		File tempFile = new File("TempMCQ.txt");
		FileReader fread = new FileReader(inputFile);
		FileWriter fwrite = new FileWriter(tempFile);

		BufferedReader reader = new BufferedReader(fread);
		BufferedWriter writer = new BufferedWriter(fwrite);
		ques = subject + "," + ques;
		String currentLine;

		while((currentLine = reader.readLine()) != null) {
    		String trimmedLine = currentLine.trim();
    		if(trimmedLine.equals(ques)) {    			
    			continue;
    		}
    		writer.write(currentLine + System.getProperty("line.separator"));
		}

		//System.out.println("CLOSE ");
		try{
			writer.close(); 
			reader.close();
			fread.close();
			fwrite.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		inputFile.delete();
		tempFile.renameTo(inputFile);
		Quiz.totalQuestion(-1);	//to decrement the question by one
	}catch(Exception e){
			e.printStackTrace();
	}
		//JOptionPane.showMessageDialog(null, "Deleted");

	}

	/*ADD QUESTIONS TO THE LIST*/
	public void addQuestionsToList(){
		try{
			File f = new File("MCQ.txt");
			BufferedReader b = new BufferedReader(new FileReader(f));
			String readLine = "";
			while((readLine = b.readLine()) != null){
				String[] subStr = readLine.split(",");
				if(subStr[0].equals(subject)){
					String ques = subStr[1] + "," + subStr[2] + "," + subStr[3] + "," + subStr[4] + "," + subStr[5] + "," + subStr[6];
					choiceQuesList.add(ques);
				}
			}
			b.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}