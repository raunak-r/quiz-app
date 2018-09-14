import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

class Generate implements ActionListener{
    JFrame generate = new JFrame();
    static String subject;
    static int ques;
    static int total;

    JButton b1 = new JButton("Exit");
    //ADD LABEL that output is done

	Generate(){
		subject = Subject.subjectName();	//subject 
		ques = Subject.GenQues();	//# of ques
		//System.out.println("Need " + ques);

		Output();
		//Write code to open output.txt
		
		b1.setBounds(50,400,300,50);
		generate.add(b1);
		b1.addActionListener(this);
        
        generate.setLayout(null);
        generate.setVisible(true);
        generate.setSize(400,600);
    }
	

	public void actionPerformed(ActionEvent e){

		if(e.getSource() == b1){	//exit
			System.exit(0);
		}
	}

	public static void main(String args[]){
		new Generate();
	}

	public static void Output(){	//write to file
		count();
		//System.out.println("total q are:" + total);
		int[] lines = new int[total];
		lines = gen(ques, total);

		try{
			File tempFile = new File("output.txt");
			FileWriter fwrite = new FileWriter(tempFile);
			BufferedWriter writer = new BufferedWriter(fwrite);

			File inputFile = new File("QuesBank.txt");
			FileReader fread = new FileReader(inputFile);
			LineNumberReader linereader  = new LineNumberReader(fread);

			String lineRead = "";
            while ((lineRead = linereader.readLine()) != null) {
            	if(linereader.getLineNumber()<=total){
	            	if(lines[linereader.getLineNumber()-1] == 1){	//mcq array 
	            		String trimmedLine = lineRead.trim();
	            		String[] subStr = trimmedLine.split(",");
	            		trimmedLine = subStr[2] + "," + subStr[7];

 		    			writer.write(trimmedLine+System.getProperty("line.separator"));
		    			//System.out.println(trimmedLine);
		    			writer.flush();
	            	}
            	}
            }
            fread.close();
            linereader.close();
            fwrite.close();
            writer.close();
            System.out.println("DONE" + "\n\n");
        }catch(IOException e){
        	e.printStackTrace();
        }
	}

	public static void count(){	//count total # of ques
		try{
            LineNumberReader reader  = new LineNumberReader(new FileReader("QuesBank.txt"));
            String lineRead = "";
            while ((lineRead = reader.readLine()) != null) {}
                total = reader.getLineNumber(); 
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
	}

	public static int[] gen(int q, int t){	//generate n lines
		//System.out.println("inside generateLines " + q + " " + t);
		int i=0,j;
		int[] arr= new int[t];
		for(i=0;i<t;i++){
			arr[i]=0;
		}
		i=0;
		while(i<q){
			j=ThreadLocalRandom.current().nextInt(0,t+1);
			
			if(j<t && arr[j]!=1){
				//System.out.println(j);
				i++;
				arr[j]=1;
			}
		}
		return arr;
	}


}