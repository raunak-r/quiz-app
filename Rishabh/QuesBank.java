import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*; 

class QuesBank implements ActionListener{
    JFrame qb = new JFrame();
    static String subject;
    static String qType;

    JLabel labelInsert = new JLabel("Write New Question");
	JTextField tfInsert = new JTextField();

	JLabel labelModifyDelete = new JLabel("Choose any question.");
    Choice choiceQuesList = new Choice();
    
    JLabel labelOption = new JLabel("Fill the Options");
	JTextField tfOption1 = new JTextField(); 
	JTextField tfOption2 = new JTextField(); 
	JTextField tfOption3 = new JTextField(); 
	JTextField tfOption4 = new JTextField(); 

	JLabel labelAnswer = new JLabel("Fill the Answer");
	JTextField tfAnswer = new JTextField();

	JButton buttonInsert = new JButton("Insert");
	JButton buttonModify = new JButton("Modify");
	JButton buttonDelete = new JButton("Delete");
	JButton bMenu = new JButton("Menu");

	QuesBank(){
		/*IDENTIFY THE SUBJECT*/
		subject = Subject.subjectName();
		qType = Subject.qType();

        labelModifyDelete.setBounds(50,50,300,20);
        choiceQuesList.setBounds(50,80,300,20);

        labelInsert.setBounds(50,120,300,20);
        tfInsert.setBounds(50,150,300,20);

        labelAnswer.setBounds(50,190,300,20);
        tfAnswer.setBounds(50,210,300,20);
        
        labelOption.setBounds(50,240,300,20);
        tfOption1.setBounds(50,270,300,20);
        tfOption2.setBounds(50,300,300,20);
        tfOption3.setBounds(50,330,300,20);
        tfOption4.setBounds(50,360,300,20);

        

        buttonInsert.setBounds(50,400,80,25);
        buttonModify.setBounds(50,430,80,25);
        buttonDelete.setBounds(50,460,80,25);
        bMenu.setBounds(50,500,300,25);

        qb.add(labelInsert);
        qb.add(tfInsert);
        qb.add(labelModifyDelete);
        qb.add(choiceQuesList);
        addQuestions();
        qb.add(labelOption);
        qb.add(tfOption1);
        qb.add(tfOption2);
        qb.add(tfOption3);
        qb.add(tfOption4);
        
        qb.add(labelAnswer);
        qb.add(tfAnswer);
        
        qb.add(buttonInsert);
        qb.add(buttonModify);
        qb.add(buttonDelete);
        qb.add(bMenu);

        buttonInsert.addActionListener(this);
        buttonModify.addActionListener(this);
        buttonDelete.addActionListener(this);
        bMenu.addActionListener(this);

        qb.setLayout(null);
        qb.setVisible(true);
        qb.setSize(400,600);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == buttonInsert){	
			Insertion();
			JOptionPane.showMessageDialog(null, "Done");
			new MainPage();
			qb.setVisible(false);
		}
		else if(e.getSource() == buttonModify){	
			Modification();
			JOptionPane.showMessageDialog(null, "Done");
			new MainPage();
			qb.setVisible(false);
		}
		else if(e.getSource() == buttonDelete){
			Deletion();
			JOptionPane.showMessageDialog(null, "Done");
			new MainPage();
			qb.setVisible(false);
		}
		else if(e.getSource() == bMenu){
			new MainPage();
			qb.setVisible(false);
		}
	}

	public static void main(String args[]){
		new QuesBank();
	}

	public void Insertion(){
		String newQues = tfInsert.getText();
		String option1 = tfOption1.getText();
		String option2 = tfOption2.getText();
		String option3 = tfOption3.getText();
		String option4 = tfOption4.getText();
		String answer = tfAnswer.getText();
		
			try{
				String filepath = "QuesBank.txt";
				FileWriter fw = new FileWriter(filepath,true);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(subject + "," + qType + "," + newQues + "," + option1 + "," + option2 + "," + option3 + "," + option4 + "," + answer);
				pw.flush();
				pw.close();
				//MainPage.totalQuestion(1);	//to increment the question by one
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
		
		if(qType.equals("M"))
			ques = subject + "," + qType + "," + ques;
		else if(qType.equals("T")){
			String[] subStr = ques.split(",");
			ques = subject + "," + qType + "," + subStr[0] + ",T,F,,," + subStr[1];
		}
		else if(qType.equals("F")){
			String[] subStr = ques.split(",");
			ques = subject + "," + qType + "," + subStr[0] + ",,,,," + subStr[1];
		}
	
	try{
		File inputFile = new File("QuesBank.txt");
		File tempFile = new File("TempQB.txt");
		FileReader fread = new FileReader(inputFile);
		FileWriter fwrite = new FileWriter(tempFile);

		BufferedReader reader = new BufferedReader(fread);
		BufferedWriter writer = new BufferedWriter(fwrite);
		String currentLine;

		while((currentLine = reader.readLine()) != null) {
    		String trimmedLine = currentLine.trim();
    		if(trimmedLine.equals(ques)) {    			
    			continue;
    		}
    		writer.write(currentLine + System.getProperty("line.separator"));
		}
			writer.close(); 
			reader.close();
			fread.close();
			fwrite.close();
		inputFile.delete();
		tempFile.renameTo(inputFile);
		//MainPage.totalQuestion(-1);	//to decrement the question by one
	}catch(Exception e){
			e.printStackTrace();
	}

	}

	/*ADD QUESTIONS TO THE LIST*/
	public void addQuestions(){
		try{
			File f = new File("QuesBank.txt");
			BufferedReader b = new BufferedReader(new FileReader(f));
			String readLine = "";
			while((readLine = b.readLine()) != null){
				String[] subStr = readLine.split(",");
				if(subStr[0].equals(subject)){
					if(subStr[1].equals(qType) && qType.equals("M")){
						String ques = subStr[2] + "," + subStr[3] + "," + subStr[4] + "," + subStr[5] + "," + subStr[6] + "," + subStr[7];
						choiceQuesList.add(ques);
					}
					else if(subStr[1].equals(qType)){
						String ques = subStr[2] + "," + subStr[7];
						choiceQuesList.add(ques);
					}
				}
			}
			b.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}