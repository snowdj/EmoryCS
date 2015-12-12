import java.util.Scanner;
    
  /* This program calculates the volume and surface area of a cylinder */
   
  public class Cylinder   {
     public static void main(String [] args) {
   
	// declaration of the variables
	double radius, height, pi = 3.14;
      
	// create a Scanner
    	Scanner input = new Scanner(System.in);
	// Enter radius
	System.out.print("provide radius");      
	// Read input from terminal and store it in the appropriate variable    
      	radius = input.nextDouble();
	// Enter height
	System.out.println("provide height"); 
	// Read input from terminal and store it in the appropriate variable
	height = input.nextDouble();
	// define a variable for pi
     
	//compute the volume of the cylinder and store it in appropriate variable
	double baseArea = pi * radius * radius;	
	double volume = baseArea * height;
	double surfaceArea = 2 * baseArea + (2 * radius * pi) * height;
	

	//computer the surface area and store it in appropriate variable.
      
	// print out the result
	System.out.print("The volume of the cylinder with radius "+radius+" and height "+height+" is "+volume+" and its surface area is "+surfaceArea);

      } 
}
