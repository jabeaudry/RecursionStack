//Written by Jacinthe Beaudry 40126080


import java.io.BufferedWriter;
import java.io.FileWriter;

public class HitZeroVersion2 {


	static int calls = 0;
	static int arr[] = {1,1,1,1,1,1,1,1,0};   //original array
	static Boolean  visited[] = new Boolean[arr.length];    //keeps track of visited arrays
	static int index = 0;         //starting index/position
	
	public static Boolean HitZeroTester(int array[], int index) {
		Stack s = new Stack(); //call stack
		Boolean isTheSequencePossible = false;    //will only turn true if the final index is reached
		
		
		//array input is too small
		if (array.length == 0 && array.length == 1) {
			return false;
		}
		
		else {
			s.push(index);    //inserts Array[index] in the stack
			//loops until the stack is: //empty (no value found) OR isTheSequencePossible true: reached the last index
			while ((!(s.isEmpty()))&& !(isTheSequencePossible)){     
				try {
					calls++;      //call counter
					index = s.peek();      //sets the index to the top stack value
				  
					if ((index) == (array.length-1)) {    //last index is reached, return true
						 isTheSequencePossible = true;
						}
					else if(visited[index]!=null) {   //index has already been visited, remove index from stack
						s.pop();
					} 
					
					else {     //the index is texted for its next 2 values
						visited[index]=true;     //changes the current index to visited
						s.push(index-array[index]);    //adds the next left value
						s.push(index+array[index]);      //adds the next right value
					}
				  
				}catch(ArrayIndexOutOfBoundsException e) {    //index is out of bounds
					s.pop();    //removes the index from the stack
				}
			}
			
		}
		return isTheSequencePossible;
	}
	
	public static void main(String[] args) {

		long totalTime = 0;     //

		long start = System.currentTimeMillis();
		Boolean sequencePossible = HitZeroTester(arr, index);
		long end = System.currentTimeMillis();
		totalTime = totalTime+(end-start);
		System.out.println(index);

			System.out.println("Total time taken: "+totalTime+", calls:"+calls);
		
			//print to the file
			try{
				  FileWriter fstream = new FileWriter("logStack.txt",true);
				  BufferedWriter out = new BufferedWriter(fstream);
				  out.write("Array: { ");
				  for (int p = 0; arr.length-1 >= p; p++) {
					  if (p==arr.length-1) {
						  out.write(arr[p]+"}");
					  }
					  else {
					  out.write(arr[p]+", ");
					  }
				  }
				  out.write("\nInput used: "+index);
					if (sequencePossible) {
						out.write("\nThe sequence is possible.\n\n");
					}
					else {
						out.write("\nThe sequence is not possible.\n\n");
					}
					 out.close();
				 
			}catch (Exception e){
				 System.err.println("Error while writing to file: " +
			          e.getMessage());
			}
			
		

	}


}
