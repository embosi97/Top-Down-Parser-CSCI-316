import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/** An object class that allows for the creation of objects (objectClass objects)
 *  that contain a string "classname" (the name of the class/super class) and 
 *  an integer "frequency" (the number of instances of a class within the data structure
 *  I devised in the Parser class (familyList).
 *  
 *  The setArg2 function creates an ArrayList of these objectClass objects which is then sorted
 *  by the frequency and printed onto text file (provided by argsv[2]), thanks to these modified compareTo
 *  and toString function in the objectClass class. setArg2 is called in Parser.main. 
 *  
 *  The Collections package is used to sort and find the frequency of classes within Parser's data structure.
**/

public class objectClass implements Comparable {
	
	public static HashMap<String, Integer> map = new HashMap<String, Integer>();
	
    private String classname;
    private int frequency;

    public objectClass(String classname, int frequency) {
         this.classname = classname;
         this.frequency = frequency;
    }

    public String getClassname() {
         return classname;
    }
    
    //necessary getters and setters
    public int getFrequency() {
	return frequency;
    }
    
    //modified compareTo
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		int freqDif = ((objectClass)o).getFrequency();
		return freqDif - frequency;
	}
	
	//modified toString
	@Override
	public String toString() {
		return "" + frequency + " objects of class " + classname + "";
	}
	
	
	
@SuppressWarnings("unchecked")
public static void setArg2(ArrayList<String> array) throws IOException {
	
	//Addressed the issue with project 1's setArg2's hard coding
	
	ArrayList<objectClass> arrObject = new ArrayList<objectClass>();
	
		for(String str: array) {
			map.put(str, Collections.frequency(array, str));
		}
		
		arrObject.add(new objectClass("Obj", Parser.objNum.intValue()));
		
	    Iterator it = map.entrySet().iterator();
	    
	    while (it.hasNext()) {
	    	
	        @SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry)it.next();
	        
	        String classname = (String) pair.getKey();
	        Integer frequency = (Integer) pair.getValue();
	        
	        arrObject.add(new objectClass(classname, frequency));
	        it.remove(); 
	    }
	    //sorting the arraylist of objectClass objects
	    Collections.sort(arrObject);
	    
	    for(objectClass obj: arrObject) {
	    	
	    	Interpreter.sb.append(obj.toString() + "\n" + "\n");
	    }
	    	Interpreter.sb.append("--------------------------------------------------------" + "\n");
	    	
	    	arrObject.clear();
		}

//For updating the graph
	public static void graphUpdate(StringBuilder strb, ArrayList<String> arr, String argv) throws IOException {
		
	
			File file = new File(argv);
			
			BufferedWriter buff = new BufferedWriter(new FileWriter(file));
			
			buff.write(strb.toString());
			
			System.out.print(strb);
			
			buff.close();		
		
	}
	}