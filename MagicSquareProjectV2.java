/* 

This program asks the user for the name of a file
The user enters the name of a text file who's contents appear like

17 24 1 8 15
23 5 7 14 16
4 6 13 20 22
10 12 19 21 3
11 18 25 2 9

The program then reads the file into a 2D array and determines 
whether the matrix is a magic square (no repeating numbers, all diagonals, horizontal and vertical lines add up to the same number)

if the program is given a proper magic square then the output appears like so:

######################################################################################################################
Enter a file to open: 
myfile.txt

The combination of numbers 

17 24 1 8 15 
23 5 7 14 16 
4 6 13 20 22 
10 12 19 21 3 
11 18 25 2 9 

is a magic square of order 5. The magic constant is 65.
######################################################################################################################

if the matrix given is not a magic square the output appears like this:

######################################################################################################################
Enter a file to open: 
myfile.txt

The combination of numbers 

17 24 1 8 15 
23 5 7 14 16 
4 6 13 20 22 
10 12 19 21 3 
11 18 25 25 9 

is not a magic square.
######################################################################################################################
*/


package Main;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MagicSquareRedo {

    public static void main(String[] args)throws IOException {
        
       //prompt user for file
       Scanner reader = new Scanner(System.in);  
       System.out.println("Enter a file to open: ");
       String fileName = reader.nextLine(); 
       reader.close();
       
       //make matrix from file
       ArrayList<Integer> numList = arrOfNums(fileName);
       MagicSquare magicSquare = new MagicSquare(make2Darray(numList));
       
       if(magicSquare.checkRows() == true && magicSquare.checkCols() == true 
               && magicSquare.checkDiag() == true && magicSquare.checkAllNumsUniq() == true){
           magicSquare.printTrue();
       }
       
       else{magicSquare.printFalse();}
       
       
    }
    
    //method gets nums from file
   public static ArrayList arrOfNums(String file) throws IOException {
       
      FileInputStream fileByteStream;
      Scanner matrixFile;
      int currentNum;
      ArrayList<Integer> numList = new ArrayList<>();
      
      //open
      fileByteStream = new FileInputStream(file);
      matrixFile = new Scanner(fileByteStream);

      //add all numbers from file into ArrayList
      while (matrixFile.hasNextInt()) {
         currentNum = matrixFile.nextInt();
         numList.add(currentNum);
      }
      
      //close
      fileByteStream.close(); 
      return numList;
   }
   
   public static int [][] make2Darray(ArrayList list){
       //get dimensions
       int dim = (int)(Math.sqrt(list.size()));
       int [][] matrix = new int [dim][dim];
       int element = 0;
       //populate 2D Array with numbers from ArrayList
       for (int i = 0; i < dim; i++) {

            for (int j = 0; j < dim; j++) {

              matrix[i][j] = (int)list.get(element);
              element++;
            }
        }
       
       return matrix;
   }
   
}

package Main;
import java.util.*;

public class MagicSquare {

    //fields
    int [][] arr2D;
    int dim;
    int magicNum;
    boolean magicOrNot;
    
    public MagicSquare(int [][] mat){
        this.arr2D = mat;
        this.dim = mat.length;
        
        //get magicNum
        int tempNum = 0;
        for (int i = 0; i < dim; i++){
           tempNum += mat[0][i]; 
       }
        this.magicNum = tempNum;
    }

    
    
    public void printMatrix(){
        for (int i = 0; i < arr2D.length; i++) {
            for (int j = 0; j < arr2D[i].length; j++) {
                System.out.print(arr2D[i][j] + " ");
            }
        System.out.println();
        }
    }
    
    
///////TESTS///////////////////////////////////////////////////////////////////////
    //check COLUMNS
    public boolean checkCols(){
           //sum of each column 
	   for(int j = 0; j < dim; j++){
	      int colSum = 0;
              
                for(int i = 0; i < dim; i++){
                   colSum += arr2D[i][j];
                }
                
                //check sum
                if(colSum != magicNum)   
                    return false;
	  }
           return true;
    }
    
    //check ROWS
    public boolean checkRows(){
           //sum of each row 
	   for(int i = 0; i < dim; i++) {
	      int rowSum = 0;
              
                for (int j = 0; j < dim; j++){
                   rowSum += arr2D[i][j];
                   
                }  
                
                //check sum
                if (rowSum != magicNum)
                    return false;
	   }
           return true;
   }
    //check DIAGONAL
    public boolean checkDiag(){
        int total = 0;
            //find nums in diagonal
            for(int i = 0; i < dim; i++){
                int diagNum = 0;
            
                for(int j = 0; j < dim; j++){
                    
                    if(i == j){ diagNum += arr2D[i][j]; }
                    
                }
                //total diagonal
                total += diagNum;
            }
            
            //check sum
            if(total != magicNum)
                return false;
            
            
            return true;
            
    }
    //check that all numbers in the matrix unique
    public boolean checkAllNumsUniq(){
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++){
                set.add(arr2D[i][j]);
            } 
        }
        
        if(set.size() != arr2D.length*arr2D.length)
            return false;
        
        return true;
    }
    
    //Print result
    //true
    public void printTrue(){
        System.out.print("The combination of numbers " + '\n' + '\n');
            printMatrix();
            System.out.println('\n' + "is a magic square of order " + 
                             dim + "." + " The magic constant is " +
                             magicNum + ".");
    
    }
    //false
    public void printFalse(){
        System.out.print("The combination of numbers " + '\n');
            printMatrix();
            System.out.println('\n' + "is not a magic square.");
    
    }
}




