import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class SequenceTransform {
static MatchObj matchMat[][];
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
					System.out.println(canBeTransformed(next.trim()));
				} 
				
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

private static String canBeTransformed(String next) {
	
	String [] elements = next.split(" ");
	
	String pattern = elements[0];
	String input = elements[1];
	
	matchMat = new MatchObj[pattern.length()][input.length()];
	return match(input.trim(),0,pattern.trim(),0)?"Yes":"No";
	
}

private static boolean match(String input,int ipIndex, String pattern, int patIndex) {
	
	
	if(pattern.length() > input.length() || pattern.length() == 0 || input.length() == 0 || pattern.length() >150 || input.length()> 1000)
		return false;

	//match the first ..and return rest of pattern and current pattern both
	//System.out.println(input +" "+pattern);
	char patChar = pattern.charAt(0);
	
		if((patChar == '0' && input.charAt(0) == 'A') || (patChar == '1' && (input.charAt(0) == 'A' || input.charAt(0) == 'B')))
		{
			if(input.length() == 1)
				{
					matchMat[patIndex][ipIndex] = new MatchObj();
					matchMat[patIndex][ipIndex].setIsMatch();
					return true;
				}
			if(pattern.length() == 1) //end of pattern
			{
				if(input.charAt(0) != input.charAt(1))
					return false;
				matchMat[patIndex][ipIndex+1] = new MatchObj();
				matchMat[patIndex][ipIndex+1].isMatch = match(input.substring(1),ipIndex+1,pattern,patIndex);
				
				return matchMat[patIndex][ipIndex+1].isMatch;
			}
			
			if(input.charAt(0) != input.charAt(1)) //character changes..always move to next char of pattern
			{	
				if(matchMat[patIndex+1][ipIndex+1]==null)
				{
					matchMat[patIndex+1][ipIndex+1] = new MatchObj();
					matchMat[patIndex+1][ipIndex+1].isMatch = match(input.substring(1),ipIndex+1,pattern.substring(1),patIndex+1);
				}
				return matchMat[patIndex+1][ipIndex+1].isMatch;
			}
		
		if(matchMat[patIndex][ipIndex+1]==null)
		{
			matchMat[patIndex][ipIndex+1] = new MatchObj();
			matchMat[patIndex][ipIndex+1].isMatch = match(input.substring(1),ipIndex+1,pattern,patIndex);
		}
		
		if(matchMat[patIndex+1][ipIndex+1] == null)
		{
			matchMat[patIndex+1][ipIndex+1] = new MatchObj();
			matchMat[patIndex+1][ipIndex+1].isMatch = match(input.substring(1),ipIndex+1,pattern.substring(1),patIndex+1);
		}
		
		return matchMat[patIndex][ipIndex+1].isMatch || matchMat[patIndex+1][ipIndex+1].isMatch; //if either returns true..there is a match
		
	} else
	{
		matchMat[patIndex][ipIndex] = new MatchObj();
		return false;
	}
		
	
}

private static class MatchObj{
	
	boolean isMatch = false;
	
	void setIsMatch(){
		this.isMatch = true;
	}

}

}
