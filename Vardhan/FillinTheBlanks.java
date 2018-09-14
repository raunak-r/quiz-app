import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*; 

class FillinTheBlanks implements ActionListener{
    Frame fitb = new Frame();
    static String subject;

    Label topic = new Label("FILL IN THE BLANKS");
    Label labelInsert = new Label("Write the New Question to be added here");
	TextField tfInsert = new TextField();

	Label labelModifyDelete = new Label("Modify or Delete from here.");
    Choice choiceQuesList = new Choice();
    
    Label labelAnswer = new Label("Write the Answer here.");
	TextField tfAnswer = new TextField();

	Button buttonInsert = new Button("Insert");
	Button buttonModify = new Button("Modify");
	Button buttonDelete = new Button("Delete");
	Button bMenu = new Button("Back to Menu");

	FillinTheBlanks(){
		/*IDENTIFY THE SUBJECT*/
		subject = Insert.SubjectName();
		if(subject.equals("Phy"))
			subject = "P";
		else
			subject = "C";

		topic.setBounds(300,40,200,20);
        labelInsert.setBounds(50,100,300,20);
        tfInsert.setBounds(50,130,300,20);

        labelModifyDelete.setBounds(450,100,300,20);
        choiceQuesList.setBounds(450,130,300,20);

        labelAnswer.setBounds(270,250,300,20);
        tfAnswer.setBounds(250,300,300,20);

        buttonInsert.setBounds(220,400,80,25);
        buttonModify.setBounds(350,400,80,25);
        buttonDelete.setBounds(480,400,80,25);
        bMenu.setBounds(250,600,300,25);
  		
  		fitb.add(topic);
        fitb.add(labelInsert);
        fitb.add(tfInsert);
        fitb.add(labelModifyDelete);
        fitb.add(choiceQuesList);
        addQuestionsToList();
        fitb.add(labelAnswer);
        
        fitb.add(tfAnswer);  
        
        fitb.add(buttonInsert);
        fitb.add(buttonModify);
        fitb.add(buttonDelete);
        fitb.add(bMenu);

        buttonInsert.addActionListener(this);
        buttonModify.addActionListener(this);
        buttonDelete.addActionListener(this);
        bMenu.addActionListener(this);

        fitb.setLayout(null);
        fitb.setVisible(true);
        fitb.setSize(800,800);
	}

	public void actionPerformed(ActionEvent e){

		if(e.getSource() == buttonInsert){	//Insert Button
			Insertion();
			JOptionPane.showMessageDialog(null, "Saved");
			new Quiz();
			fitb.setVisible(false);
		}
		else if(e.getSource() == buttonModify){	//BACK TO MENU BUTTON
			Modification();
			JOptionPane.showMessageDialog(null, "Modified");
			new Quiz();
			fitb.setVisible(false);
		}
		else if(e.getSource() == buttonDelete){
			Deletion();
			JOptionPane.showMessageDialog(null, "Deleted");
			new Quiz();
			fitb.setVisible(false);
		}
		else if(e.getSource() == bMenu){
			new Quiz();
			fitb.setVisible(false);
		}
	}

	public static void main(String args[]){
		new FillinTheBlanks();
	}

	public void Insertion(){
		String newQues = tfInsert.getText();
		String answer = tfAnswer.getText();
			try{
				String filepath = "FillinTheBlanks.txt";
				//int c =  QuesCount(filepath); c++;
				FileWriter fw = new FileWriter(filepath,true);
				PrintWriter pw = new PrintWriter(fw);

				pw.println(subject + "," + newQues + ","  + answer);
				pw.flush();
				pw.close();
				fw.close();
				//JOptionPane.showMessageDialog(null, "Saved");
				Quiz.totalQuestion(1);	//to increment the question by one
			}catch(Exception ae){
				JOptionPane.showMessageDialog(null, "ERROR: NOT Saved");
			}
	}

	public void Modification(){
		Deletion();
		Insertion();
	}

	public void Deletion(){
		String ques = choiceQuesList.getItem(choiceQuesList.getSelectedIndex());

	try{
		File inputFile = new File("FillinTheBlanks.txt");
		File tempFile = new File("TempFitb.txt");
		FileReader fread = new FileReader(inputFile);
		FileWriter fwrite = new FileWriter(tempFile);

		BufferedReader reader = new BufferedReader(fread);
		BufferedWriter writer = new BufferedWriter(fwrite);
		ques = subject + "," + ques;
		String currentLine;

		//System.out.println("Org " + ques);
		while((currentLine = reader.readLine()) != null) {
    		String trimmedLine = currentLine.trim();
    		//System.out.println("length " + currentLine.length() + "trimline" + trimmedLine.length());
    		if(trimmedLine.equals(ques)) {    			
    			continue;
    		}
    		writer.write(currentLine + System.getProperty("line.separator"));
		}

		System.out.println("CLOSE ");
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
			JOptionPane.showMessageDialog(null, "Not Deleted");
		}
		//JOptionPane.showMessageDialog(null, "Deleted");
	}

	/*ADD QUESTIONS TO THE LIST*/
	public void addQuestionsToList(){
		try{
			File f = new File("FillinTheBlanks.txt");
			BufferedReader b = new BufferedReader(new FileReader(f));
			String readLine = "";
			while((readLine = b.readLine()) != null){
				String[] subStr = readLine.split(",");
				if(subStr[0].equals(subject)){
					String ques = subStr[1].concat("," + subStr[2]);
					choiceQuesList.add(ques);
				}
			}
			b.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}