import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

abstract class Obj {
	boolean visited = false; // indicates if this object has been visited by traverse() function
	public static ArrayList<String> graph = new ArrayList<String>();

	Obj() {

		// this is here for testing purposes
//	System.out.println("The Class "+ "'" + this.getClass().getName() + "'" + 
//			" is a Descendant of " + "'" + this.getClass().getSuperclass().getName() + "'.");

		/**
		 * Parser's ArrayList is being filled with the name and super class names of an
		 * object every time the Obj class is invoked.
		 * 
		 * An atomic integer is being incremented to count the number of instances of an
		 * Obj.
		 */
		Parser.objNum.getAndIncrement();
		Parser.familylist.add(this.getClass().getName());
		Parser.familylist.add(this.getClass().getSuperclass().getName());

	}
	
	
	//Change to traverse() to fit the requirements more
	public void traverse() { //DFS

		try {
			
			// If graph hasn't yet been populated 
			// Print project 1's setArg2 class (modified heavily from Project 1)
			if (graph.size() == 0) {
				//call setArg2
				objectClass.setArg2(Parser.familylist);
				
				//counting the amount of test runs
				Interpreter.objCount.getAndIncrement();
				//AtomicInteger that counts the test run
				Interpreter.sb.append("Test Run #" + Interpreter.objCount.intValue() + ": " + "\n" + "\n");
			}
			
			// Declared Fields of this's SuperClasses (Public, Private, and Protected)
			Field[] superfields = this.getClass().getSuperclass().getDeclaredFields();

			// Declared Fields of this's class
			Field[] cfields = this.getClass().getDeclaredFields();

			// The current object
			Object current = this;

			// If this current object hasn't been visited, visit it and set its .visited to true
			
			if (current.getClass() != null || ((Obj) current).visited == false) {

				// set current object to visited (true), so we don't revisit it during the recursion process
				((Obj) current).visited = true;

				// If the current object is NOT an instance of Int, Id, or Floatp add to the graph without a .get Object
				if (!(current instanceof Int || (current instanceof Id) || (current instanceof Floatp))) {
					
					graph.add(current.getClass().getName() + "\n");
					Interpreter.sb.append(current.getClass().getName() + "\n");

				}
				
				//Iterating through the reference links of superfields
				for (int i = 0; i < superfields.length; i++) {
					
					//The referenced object in superfields
					Object sreference = superfields[i].get(current);
					
					//System.out.print(current.getClass().getName() + " SUPER ---->" + sreference.toString() + "\n");
					
					// If it is an instanceof Obj, proceed with the recursion
					if (((Obj) sreference).visited == false && (sreference instanceof Obj)) {

						((Obj) sreference).traverse();

					}

				}
				
				//Iterating through the reference links of fields
				for (int j = 0; j < cfields.length; j++) {
					
					//The referenced object in cfields
					Object reference = cfields[j].get(current);

					if ((reference instanceof java.lang.Float || reference instanceof java.lang.Integer
							|| reference instanceof java.lang.String) && !(current instanceof Assignment)) {
						
						// If current object IS an instance of Int, Id, Floatp
						// Print it with the referenced Object
						if(current instanceof Int || current instanceof Id || current instanceof Floatp) {

						Interpreter.sb.append(current.toString() + "\n");
						
						// Thanks to new Int, Id, and Floatp classes
						graph.add(current.toString() + reference.toString() + "\n");
						
						}


					}
					
					//Testing stuff
					//System.out.print(current.getClass().getName() + " not super ---->" +  reference.toString() + "\n");
					
					// If it is an instanceof Obj, proceed with the recursion
					if ((reference instanceof Obj) && ((Obj) reference).visited == false) {

						((Obj) reference).traverse();

					}

				}

			}

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
