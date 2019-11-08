package Main;
import java.util.Random;

/*

- This program simulates customers waiting in line at a grocery store using a queue. 
- The Customer class was provided by the professor
- The driver simulates 60 minutes of activity at the store. Each iteration represents a minute

Each iteration:
    - the program checks if new customers have been added
    - there is a 25% chance a new customer will be added
    - current customer object at the front of the queue is updated
    - if customer has been completely serviced it is removed from the queue

*/

public class Main{

    public static void main(String[] args){
      
        Queue line = new Queue();
        
        //60 minutes in the Grocery Store
        for(int i = 0; i < 60; i++){
            
            //25% chance that a customer is added
            if(new Random().nextInt(4) + 1 == 3){
                
                //add new customer to Q
                Customer C = new Customer();
                line.enqueue(C);
                
                //print Q length and increase total customers
                System.out.println("New customer added!  Queue length is now " + line.getqLength());
                line.setTotalCustomers(line.getTotalCustomers() + 1);
            }
       
            //if line isnt empty do the following
            if(line.isEmpty() == false){
                
                Customer current = line.getFirst();

                //check service time, if 0 remove customer and print info
                if(current.getServiceTime() == 0){
                line.dequeue();
                System.out.println("Customer serviced and removed from the queue."
                                  + "  Queue length is now " + line.getqLength());

                }

                //if line is still not empty, adjust current service time    
                if(line.isEmpty() == false){
                    line.getFirst().decServiceTime();
                }
            }
        
            //check if linelength is a new max
            if(line.getqLength() > line.getMaxLineLength()){
                line.setMaxLineLength(line.getqLength());
            }
        
            //end of iteration
            System.out.println("----------------------------------------------------------------------");
        }
        
        //print final info
        System.out.println();
        System.out.println(line.toString());
 
    }
    
}




//////////////////////////////////////////////////////////////////////////////////
package Main;

//Linked List Queue 

public class Queue {
 
    //fields
    private Customer first;
    private Customer last;
    private int qLength;               
    private int maxLineLength; 
    private int totalCustomers;
    
    //constructor
    public Queue(){
        
        first = null;
        last = null;
        qLength = 0;
        maxLineLength = 0; 
        totalCustomers = 0;
        
    }
    
    //setters
    public void setqLength(int qLength){
        this.qLength = qLength;
    }

    public void setMaxLineLength(int maxLineLength){
        this.maxLineLength = maxLineLength;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }
    
    
    //getters
    public int getqLength(){
        return qLength;
    }

    public int getMaxLineLength(){
        return maxLineLength;
    }

    public Customer getFirst() {
        return first;
    }

    public Customer getLast() {
        return last;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }
    
    
    
    //METHODS
    public boolean isEmpty(){
        
        return first == null;
    }
    
    //add customer to the Q
    public void enqueue(Customer c){
    
        //if empty - set as first element, otherwise go to end of the line
        if (isEmpty()){
            first = c;
        } 
        
        else {
            last.setNext(c);
        }
        
        last = c;
        qLength++;
    }

    //remove customer from Q          
    public void dequeue(){
        
        //get first element, if Q is now empty - set last to null
        first = first.getNext();
        
        if(isEmpty()){
    
            last = null;
        }
        
        qLength--;
    }
    
    //print info 
    public String toString(){
        return "Total customers serviced: " + getTotalCustomers() + '\n'
                + "Max line length: " + getMaxLineLength(); 
    }
    
    
}
//////////////////////////////////////////////////////////////////////////////////

/*
	Program: CustomerQueue ~ Customer Class
	Professor: D. Jugan
	Date:
	Summary: Creates a Customer object to be used in the line queue. Holds the serviceTime for each customer. 
	Functionality: 
		Constructor: Random ServiceTime (1-5)
		Public Methods: getServiceTime, newMinute
*/

//DO NOT EDIT///////////////////////
package Main;
import java.util.Random;

public class Customer {
    
    private int serviceTime; 				// ServiceTime for this Customer
   private Customer next;
	
	/// Constructor
	public Customer() {										
		serviceTime = new Random().nextInt(5) + 1;	// Randomly assign required service time 1-5
      next = null;
	}
   
   // Getter for next Customer in list
   public Customer getNext(){
      return next;
   }
   
   // Setter for next reference
   public void setNext(Customer c){
      next = c;
   }
	
	/// Getter for ServiceTime
	public int getServiceTime() {							
		return serviceTime;
	}
	
	/// Decrement ServiceTime by 1
	public void decServiceTime() {		
		serviceTime--;
	}
}

///////////////////////////////////////////////////////////////////////////////

/****************************Output Example***************************
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
New customer added!  Queue length is now 1
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
Customer serviced and removed from the queue.  Queue length is now 0
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
New customer added!  Queue length is now 1
----------------------------------------------------------------------
Customer serviced and removed from the queue.  Queue length is now 0
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
New customer added!  Queue length is now 1
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
New customer added!  Queue length is now 2
----------------------------------------------------------------------
New customer added!  Queue length is now 3
Customer serviced and removed from the queue.  Queue length is now 2
----------------------------------------------------------------------
New customer added!  Queue length is now 3
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
Customer serviced and removed from the queue.  Queue length is now 2
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
Customer serviced and removed from the queue.  Queue length is now 1
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
Customer serviced and removed from the queue.  Queue length is now 0
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
New customer added!  Queue length is now 1
----------------------------------------------------------------------
Customer serviced and removed from the queue.  Queue length is now 0
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
New customer added!  Queue length is now 1
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
New customer added!  Queue length is now 2
----------------------------------------------------------------------
Customer serviced and removed from the queue.  Queue length is now 1
----------------------------------------------------------------------
----------------------------------------------------------------------
Customer serviced and removed from the queue.  Queue length is now 0
----------------------------------------------------------------------
New customer added!  Queue length is now 1
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
New customer added!  Queue length is now 2
----------------------------------------------------------------------
New customer added!  Queue length is now 3
Customer serviced and removed from the queue.  Queue length is now 2
----------------------------------------------------------------------
New customer added!  Queue length is now 3
Customer serviced and removed from the queue.  Queue length is now 2
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------

Total customers serviced: 13
Max line length: 3

**********************************************************************/