import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

class ShowQuestion implements ActionListener{
    Frame pane = new Frame();
    static String subject;
    static int m, t, f; //To STORE THE NUMBER OF QUESTION IN MCQ, TRUEFALSE, FILLINTHEBLANKS
    static int a,b,c;	//To know that 1st a ques in output.txt are of mcq, next b of TF ..

    Label topic = new Label("QUESTION PANEL");
    Label labelQues = new Label("Question will be shown here");
	static JTextArea tfQues = new JTextArea();
	static JScrollPane sp = new JScrollPane(tfQues);

	Button output = new Button("Export Questions");
	Button solution = new Button("Export Answers");
	Button bMenu = new Button("Quit and go Back to Menu");

	ShowQuestion(){
		/*IDENTIFY THE SUBJECT*/
		subject = Insert.SubjectName(); //Will be either {P,C} or All.
		countMCQ();	//count # of ques in mcq
		countTF();
		countFITB();
	
		topic.setBounds(325,40,150,20);
        labelQues.setBounds(300,100,300,20);
        

        output.setBounds(250,450,300,25);
        solution.setBounds(250,480,300,25);
        bMenu.setBounds(250,600,300,25);

  		pane.add(topic);
        pane.add(labelQues);
        //pane.add(tfQues);
        //sp = new JScrollPane(tfQues);
        
        sp.setBounds(50,130,700,300);
        //pane.getContentPane().add(sp);
        sp.setMinimumSize(new Dimension(300,300));
        pane.add(sp);

        pane.add(bMenu);
        pane.add(output);
        pane.add(solution);

        output.addActionListener(this);
        solution.addActionListener(this);
        bMenu.addActionListener(this);

        pane.setLayout(null);
        pane.setVisible(true);
        pane.setSize(800,800);
        
	}

	public void actionPerformed(ActionEvent e){

		if(e.getSource() == bMenu){
			new Quiz();
			pane.setVisible(false);
		}
		else if(e.getSource() == output){
			try{
				File file = new File("output.txt");
				FileWriter fw = new FileWriter(file);
				BufferedWriter w = new BufferedWriter(fw);
    			tfQues.write(w);
				Desktop.getDesktop().open(new java.io.File("output.txt"));
				w.close();
				fw.close();
			}catch(IOException ae){
				JOptionPane.showMessageDialog(null, "Failed");
			}
		}
		else if(e.getSource() == solution){
			try{
				Desktop.getDesktop().open(new java.io.File("solution.txt"));
			}catch(IOException ae){
				JOptionPane.showMessageDialog(null, "Failed");
			}
		}
	}

	public static void main(String args[]){
		new ShowQuestion();
		
	}

	public static void printOnScreen(){
		int count = 1;
		tfQues.setText(" ");
		try{
			File f = new File("output.txt");
			BufferedReader b = new BufferedReader(new FileReader(f));
			String readLine = "";
			while((readLine = b.readLine()) != null){
				String[] subString = readLine.split(",");
				if(subString.length == 5){
					String ques = "Ques " + count + " -> " + subString[0] + "\n\tOptions are: " + subString[1] + "," + subString[2] + "," + subString[3] + "," + subString[4];
					tfQues.append(ques + "\n");
				}
				else if(subString.length == 3){
					String ques = "Ques " + count + " -> " + subString[0] + "\n\tOptions are: " + subString[1] + "," + subString[2];
					tfQues.append(ques + "\n");
				}
				else if(subString.length == 1){
					String ques = "Ques " + count + " -> " + subString[0];
					tfQues.append(ques + "\n");
				}
				count++;
			}
			b.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void generateQuestions(int n){
		//x = MCQ
		int x, y, z, i =1;
		while(true){
			if(m<n)
				x = ThreadLocalRandom.current().nextInt(0,m+1);
			else 
				x=ThreadLocalRandom.current().nextInt(0,n+1);
			
			i++;
			if(x+t >= n)
				y = ThreadLocalRandom.current().nextInt(0,n-x+1);
			else
				y = ThreadLocalRandom.current().nextInt(0,t+1);
			
			z = n-x-y;
			if(z<=f)
				break;
		}
		a = x;
		b = y;
		c = z;
		System.out.println("generated: " + x+" " +y+ " "+ z);
		System.out.println("total ques m,t,f " + m + " " + t + " " + f);
		
		WriteToFile(x,y,z);	//# ques req in MCQ, # ques req in TF, # ques req in Fitb
	}

	/*Method to read from each of the file and then store in output.txt*/
	public static void WriteToFile(int x, int y, int z){
		//System.out.println("inside WriteToFile: " + x+" " +y+ " "+ z);
		int[] mcq = new int[m];	//make this array to store information about the lines of mcq.txt
		int[] tf = new int[t];	//for truefalse
		int[] fitb = new int[f];	
		mcq = generateLines(x,m);	//call this function to generate random lines from mcq.txt
		tf = generateLines(y,t);
		fitb = generateLines(z,f);

		try{
			/*generate only QUESTIONS */
			File tempFile = new File("output.txt");
			FileWriter fwrite = new FileWriter(tempFile);
			BufferedWriter writer = new BufferedWriter(fwrite);

			/*Generate Solutions seperately */
			File tempFileSol = new File("solution.txt");
			FileWriter fwriteSol = new FileWriter(tempFileSol);
			BufferedWriter writerSol = new BufferedWriter(fwriteSol);

			/*GET RANDOM LINES FROM MCQ.txt*/
			File inputFile = new File("MCQ.txt");
			FileReader fread = new FileReader(inputFile);
			LineNumberReader linereader  = new LineNumberReader(fread);

			String lineRead = "";
            while ((lineRead = linereader.readLine()) != null) {
            	if(linereader.getLineNumber()<=m){
	            	if(mcq[linereader.getLineNumber()-1] == 1){	//mcq array 
	            		String trimmedLine = lineRead.trim();
	            		String[] subStr = trimmedLine.split(",");
	            		trimmedLine = subStr[1] + "," + subStr[2] + "," + subStr[3] + "," + subStr[4] + "," + subStr[5];
	            		
	            		writerSol.write(subStr[6]+System.getProperty("line.separator"));
		    			writer.write(trimmedLine+System.getProperty("line.separator"));
		    			System.out.println(trimmedLine);
		    			writer.flush();
		    			writerSol.flush();
	            	}
            	}
            }
            fread.close();
            linereader.close();
            System.out.println();
            
            /*GET RANDOM LINES FROM TF.txt*/
			File inputFile1 = new File("TF.txt");		//FOR TF ARRAY
			FileReader fread1 = new FileReader(inputFile1);
			LineNumberReader linereader1 = new LineNumberReader(fread1);
			lineRead = "";
            while ((lineRead = linereader1.readLine()) != null) {
            	if(linereader1.getLineNumber()<=t){	//FOR TF ARRAY
	            	if(tf[linereader1.getLineNumber()-1] == 1){		//FOR TF ARRAY
	            		String trimmedLine = lineRead.trim();
	            		String[] subStr = trimmedLine.split(",");
	            		trimmedLine = subStr[1] + ",T,F";

	            		writerSol.write(subStr[2]+System.getProperty("line.separator"));
		    			writer.write(trimmedLine+System.getProperty("line.separator"));
		    			System.out.println(trimmedLine);
		    			writer.flush();
		    			writerSol.flush();		    			
	            	}
            	}
            }
            fread1.close();
            linereader1.close();
            System.out.println();

            /*GET RANDOM LINES FROM FillinTheBlanks.txt*/
            File inputFile2 = new File("FillinTheBlanks.txt");
			FileReader fread2 = new FileReader(inputFile2);
			LineNumberReader linereader2 = new LineNumberReader(fread2);
			lineRead = "";
            while ((lineRead = linereader2.readLine()) != null) {
            	if(linereader2.getLineNumber()<=f){
	            	if(fitb[linereader2.getLineNumber()-1] == 1){		//FOR FITB ARRAY
	            		String trimmedLine = lineRead.trim();
	            		String[] subStr = trimmedLine.split(",");
	            		trimmedLine = subStr[1];
	            		writerSol.write(subStr[2]+System.getProperty("line.separator"));
		    			writer.write(trimmedLine+System.getProperty("line.separator"));
		    			System.out.println(trimmedLine);
		    			writer.flush();
		    			writerSol.flush();		    			
	            	}
            	}
            }
            fread2.close();
            linereader2.close();
			
			/*fwriteSol.close();
			writerSol.close();            
            fwrite.close();
            writer.close();*/
        }catch(Exception e){
            	e.printStackTrace();
        }
        System.out.println("Generated.\n\n\n\n");
        printOnScreen();
	}

	/*GENERATE RANDOM LINES IN A FILE*/
	public static int[] generateLines(int x,int n){	//x = to generate || n = size of file
		System.out.println("inside generateLines " + x + " " + n);
		int i=0,j;
		int[] arr= new int[n];
		for(i=0;i<n;i++){
			arr[i]=0;
		}
		i=0;
		while(i<x){
			j=ThreadLocalRandom.current().nextInt(0,n+1);
			
			if(j<n && arr[j]!=1){
				System.out.println(j);
				i++;
				arr[j]=1;
			}
		}
		return arr;
	}

	    /*COUNT THE NUMBER OF QUESTIONS PRESENT IN EACH QUESTION FILE*/
	public static void countMCQ(){
		try{
            LineNumberReader reader  = new LineNumberReader(new FileReader("MCQ.txt"));
            String lineRead = "";
            while ((lineRead = reader.readLine()) != null) {}
                m = reader.getLineNumber(); 
            reader.close();
	       }catch(IOException e){
            e.printStackTrace();
        }
	}

	public static void countTF(){
		try{
            LineNumberReader reader  = new LineNumberReader(new FileReader("TF.txt"));
            String lineRead = "";
            while ((lineRead = reader.readLine()) != null) {}
                t = reader.getLineNumber(); 
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
	}

	public static void countFITB(){
		try{
            LineNumberReader reader  = new LineNumberReader(new FileReader("FillinTheBlanks.txt"));
            String lineRead = "";
            while ((lineRead = reader.readLine()) != null) {}
                f = reader.getLineNumber(); 
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
	}	
	
}