import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class SelectiveBubbleSort {


public static void main(String []args){
		
		BufferedReader br;
		try{
			File f = new File(args[0]);
			br = new BufferedReader(new FileReader(f));
			String next = br.readLine();
			while(next != null)
			{
				if(!next.trim().equals(""))
				{	
					printSelectivelySorted(next.trim());
				} 
				
				System.out.println();
				next=br.readLine();
			}
			br.close();
		}catch(FileNotFoundException e)
		{
			System.out.println("File Not Found!");
			
		}catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

private static void printSelectivelySorted(String next) {
	
	String elements[] = next.split("\\|");
	long itrCount = Long.parseLong(elements[1].trim());
	
	int input[] = StringToInt(elements[0].split(" "));
	
	selectiveBubbleSort(input, itrCount );
	
}

private static int[] StringToInt(String[] allNums) {
	
	int input[] = new int[allNums.length];
	int count = 0;
	for(String s:allNums)
	{
		input[count] = Integer.parseInt(s.trim());
		count++;
	}
	return input;
}

private static void selectiveBubbleSort(int[] input, long itrCount) {
  
	itrCount = itrCount<input.length? itrCount: input.length;
	for(int i=0;i<itrCount ; i++) {
			
		for(int j =1; j<input.length - i; j++)
		{
			if(input[j]<input[j-1])
			{
				int temp = input[j];
				input[j] = input[j-1];
				input[j-1] = temp;
			}
		}
	}
	
	printArray(input);
}

private static void printArray(int[] input) {
	
	for(int i:input)
	{
		System.out.print(i+" ");
	}
	
}

}
