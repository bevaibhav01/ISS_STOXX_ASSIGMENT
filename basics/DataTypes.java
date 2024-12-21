package basics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataTypes {
	private int instanceVariable;
	
	private static String staticVariable="static variable";
	
	//constant variable
	private static final double CONSTANT_VARIABLE=3.14;
	
	// Logger instance
    private static final Logger logger = LoggerFactory.getLogger(DataTypes.class);
	
    //Default constructor will intitialise data to default value 0
    DataTypes(){
    	this.instanceVariable=0;
    	 logger.info("Default Constructor Called - Instance Variable Initialized to 0");
    }
    

    // Parameterized Constructor
    public DataTypes(int instanceVariable) {
        this.instanceVariable = instanceVariable;
        logger.info("Parameterized Constructor Called - Instance Variable Initialized to {}", instanceVariable);
    }
    
    // Method to display variable values
    public void displayValues() {
        // Local variable (limited scope to method)
        int localVar = 10;

        // Basic Data Types 
        boolean booleanVariable = true; // store Boolean type value (true or false)
        float floatVariable = 3.14f; // store Float type (fraction numbers)
        char charVariable = 'A'; // store Char type any single character 

        // Logging variable values
        logger.info("Displaying Values - Instance Var: {}, Static Var: {}, Constant Var:{}, Local Var: {}, Boolean Var: {}, Float Var: {}, Char Var: {}", 
                    instanceVariable, staticVariable,CONSTANT_VARIABLE, localVar, booleanVariable, floatVariable, charVariable);
    }
    
    
public static void main(String []args) {
	  // Creating an object using the parameterized constructor
    DataTypes obj = new DataTypes(42);
    
    // Display values
    obj.displayValues();

   
}

}
