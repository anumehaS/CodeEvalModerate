import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class PassTriangle {


public static void main(String []args){
		
		BufferedReader br;
		try{
			File f = new File(args[0]);
			br = new BufferedReader(new FileReader(f));
			String next = br.readLine();
			int size = 1;
			int curLevel[];
			int prevLevel[] = null;
			while(next != null)
			{
				if(!next.trim().equals(""))
				{	
					curLevel = StringToInt(next.trim());
					if(prevLevel!= null)
						prevLevel = generateMaxSumLevel(prevLevel,curLevel);
					else
						prevLevel = curLevel;
					
				} 
				
				next=br.readLine();
			}
			
			printMax(prevLevel);
			br.close();
		}catch(FileNotFoundException e)
		{
			System.out.println("File Not Found!");
			
		}catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

private static void printMax(int[] prevLevel) {
	if (prevLevel== null)
		return;
	int max=Integer.MIN_VALUE;
	for(int i:prevLevel){
		max=i>max?i:max;
	}
	System.out.print(max);	
}

private static int[] generateMaxSumLevel(int[] prevLevel, int[] curLevel) {
	
	curLevel[0] += prevLevel[0];
	
	for(int i =1;i< curLevel.length-1; i++) {
		
		int leftPar = i-1;
		int rightPar = i;
		
		if(prevLevel[leftPar] > prevLevel[rightPar])
			curLevel[i] += prevLevel[leftPar];
		else
			curLevel[i] += prevLevel[rightPar];
	}
	
	curLevel[curLevel.length-1] += prevLevel[prevLevel.length-1];
	
	return curLevel;
}

private static int[] StringToInt(String next) {
	
	String allNums[] = next.split(" ");
	int input[] = new int[allNums.length];
	int count = 0;
	for(String s:allNums)
	{
		input[count] = Integer.parseInt(s.trim());
		count++;
	}
	return input;
}

}
