/*
THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS. TAMER AVCI
*/ 
import java.util.Stack;

public class NQueens {
 
   	public static boolean isValid(int cp, Stack<Integer> stack) {
  		for (int i=0; i<stack.size(); i++)
        		if (stack.get(i)==cp || stack.get(i)-cp == i-stack.size() || cp-stack.get(i)==i-stack.size()) return false;
     		return true;
  	}

  	public static int solve(int n) {
  		Stack<Integer> stack = new Stack<Integer>();
    		int currentPos=0;
    		int numSol=0;
   		if (n==1) { //trivial solution
        		stack.push(0);
     	        	printSolution(stack);
      			numSol=1;
    		}
  		while (currentPos<n) {
        		if (isValid(currentPos, stack)) {
        			stack.push(currentPos);
        			currentPos=0;
       				continue;
      			}
      			else if(currentPos==n-1) {
        			if(stack.empty()) break;
        			else { 
        				currentPos=stack.get(stack.size()-1)+1;
        				if (currentPos==n) {
        					stack.pop();
        					if(stack.empty()) break;
        					currentPos=stack.get(stack.size()-1)+1;
	   				        stack.pop();
        				}
        				else
        					stack.pop();
        				continue;
        			}
      			}	
      			else if(stack.size()==n) {
        			numSol++;
        			printSolution(stack);
        			if(stack.get(stack.size()-1)==n-1){
          				stack.pop();
          				currentPos=stack.get(stack.size()-1)+1;
          				stack.pop();
        			}
        			else {
        				currentPos=stack.get(stack.size()-1)+1;
        				stack.pop();
        				continue;
        			}
      			}
      			currentPos++;
   		}	
      		return numSol;
    
	}//solve()
  
	private static void printSolution(Stack<Integer> s) {
		for (int i = 0; i < s.size(); i ++) {
      			for (int j = 0; j < s.size(); j ++) {
        			if (j == s.get(i))
          				System.out.print("Q ");
        			else
          				System.out.print("* ");
      			}//for
      			System.out.println();
   		 }//for
   		 System.out.println();  
  	}//printSolution()
  
  	// ----- the main method -----
  	// (you shouldn't need to change this method)
	public static void main(String[] args) {
  
  		int n = 15;
  
  	// pass in parameter n from command line
 		if (args.length == 1) {
   	        	n = Integer.parseInt(args[0].trim());
   			if (n < 1) {
      				System.out.println("Incorrect parameter");
      				System.exit(-1);
    			}//if   
  		}//if
  
  		int number = solve(n);
  		System.out.println("There are " + number + " solutions to the " + n + "-queens problem.");
 	}//main()
  
}
