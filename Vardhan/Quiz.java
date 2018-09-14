import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class Quiz implements ActionListener{
	//Declaring Objects
    static int totalQues;// = totalQuestion(0);    //Initialize with 0
	Frame quiz = new Frame();

	Button b1=new Button("Insert");
    Button b2=new Button("Modify");
    Button b3=new Button("Delete");
    Button b4=new Button("Generate");
    Button b5=new Button("Exit");

    Quiz(){
        totalQues = totalQuestion(0);    	//COUNT TOTAL QUESTIONS AT START
    	
    	b1.setBounds(220,250,360,40);
        b2.setBounds(220,300,360,40);
        b3.setBounds(220,350,360,40);
        b4.setBounds(220,400,360,40);
        b5.setBounds(220,450,360,40);

        quiz.add(b1);
        quiz.add(b2);
        quiz.add(b3);
        quiz.add(b4);
        quiz.add(b5);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
	    b4.addActionListener(this);
        b5.addActionListener(this);

	   quiz.setLayout(null);
	   quiz.setVisible(true);
	   quiz.setSize(800,800);    
    }

	public void actionPerformed(ActionEvent e){
        /*THESE THREE FUNCTIONS ARE DONE VIA SAME FRAME*/
		if(e.getSource()==b1){		//Insert
            new Insert();
            quiz.setVisible(false);
        }
            
        if(e.getSource()==b2){		//Modify
            new Insert();
            quiz.setVisible(false);
        }
        
        if(e.getSource()==b3){
            new Insert();			//Delete
            quiz.setVisible(false);
        }
        
        if(e.getSource()==b4){
            new Generate();			//Generate
            quiz.setVisible(false);
        }
        
        if(e.getSource()==b5){
            System.exit(0);
        }
	}

	public static void main(String args[]){
		new Quiz();
	}

    /*COUNT THE NUMBER OF QUESTIONS PRESENT IN EACH QUESTION FILE*/
    public static int totalQuestion(int c){
        int count = 0;
        if(c == 0){
            totalQues = c;
            /*COUNT QUESTIONS ONE BY ONE*/
        try{
            LineNumberReader reader  = new LineNumberReader(new FileReader("TF.txt"));
            String lineRead = "";
            while ((lineRead = reader.readLine()) != null) {}
                count = reader.getLineNumber(); 
            reader.close();
            totalQues = totalQues + count;
        }catch(IOException e){
            e.printStackTrace();
        }
            count = 0;
        try{
            LineNumberReader reader  = new LineNumberReader(new FileReader("FillinTheBlanks.txt"));
            String lineRead = "";
            while ((lineRead = reader.readLine()) != null) {}
                count = reader.getLineNumber(); 
            reader.close();
            totalQues = totalQues + count;
        }catch(IOException e){
            e.printStackTrace();
        }

            count = 0;
        try{
            LineNumberReader reader  = new LineNumberReader(new FileReader("MCQ.txt"));
            String lineRead = "";
            while ((lineRead = reader.readLine()) != null) {}
                count = reader.getLineNumber(); 
            reader.close();
            totalQues = totalQues + count;
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("QUiz contains " + totalQues + " Questions.");
    }
        else{
            totalQues = totalQues + c;
            System.out.println(totalQues);
        }
        return totalQues;
    }
}