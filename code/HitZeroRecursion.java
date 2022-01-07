//Written by Jacinthe Beaudry 40126080

import java.io.FileWriter;
import java.io.BufferedWriter;

public class HitZeroRecursion {
	
	static int calls = 0;
	static int arr[] = {1,1,1,1,1,1,1,1,1,2,2,0};   //original array
	static int index = 0;    //starting index/position
	static Boolean  visited[] = new Boolean[arr.length];    //keeps track of visited arrays
	
	public static Boolean HitZeroTester(int arr[], int index) {
		calls++;
		if (arr.length == 0 && arr.length == 1) {
			return false;
		}
		else {
			try {
				if ((index) == (arr.length-1)) {
					 return true;
					}
				else if(visited[index]!=null) {
					return false;
				} 
				
				else {
					visited[index]=true;     //changes the current index to visited
					return (HitZeroTester(arr,index-arr[index])||HitZeroTester(arr,index+arr[index]));
				}
			}catch(ArrayIndexOutOfBoundsException e) {    //index is out of bounds
				return false;
			}
			
		}
	}
	
	public static void main(String[] args) {
		Boolean sequencePossible = false;	//becomes true if the last index is reachable
		long totalTime = 0;     //
			
	
		long start = System.currentTimeMillis();
		sequencePossible = HitZeroTester(arr, index);
		long end = System.currentTimeMillis();
		totalTime = totalTime+(end-start);
		
		
		//print to the file
		try{
			  FileWriter fstream = new FileWriter("log.txt",true);
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
		
	
		System.out.println(index);
		System.out.println("Total time taken: "+totalTime+", calls:"+calls);

		

	}

}
