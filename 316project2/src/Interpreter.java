import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;
import java.lang.StringBuilder;


public abstract class Interpreter extends Parser
{
	public static HashMap<String, Val> varState = new HashMap<String, Val>(); 
	              // program state, i.e., virtual memory for variables
	
	
	static AtomicInteger objCount = new AtomicInteger(0);

	
	public static StringBuilder sb = new StringBuilder();

		
	
	public static void main(String argv[])
	{
		
		
		//Using absolute path for args
//		/Users/emilbodo/eclipse-workspace/316project1/src/in15.txt
//		/Users/emilbodo/eclipse-workspace/316project1/src/out15.txt
//		/Users/emilbodo/eclipse-workspace/316project1/src/args15.txt
		
		
		// argv[0]: input file containing an assignment list
		// argv[1]: output file displaying the parse tree
		// argv[2]: output file displaying the numbers of constructed objects and
		//          the visited objects in order of the depth-first traversal
		
		setIO( argv[0], argv[1] );
		setLex();

		getToken();

		AssignmentList assignmentList = assignmentList(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + " : Syntax Error, unexpected symbol where id expected");
		else if ( ! errorFound )
		{
			assignmentList.printParseTree("");       // print the parse tree in linearly indented form in argv[1] file
			assignmentList.M(varState);              // interpret the assignment list
			//System.out.println(varState.toString()); // print the program state on the terminal
		}

		assignmentList.traverse(); // perform depth-first traversal from assignmentList
		Interpreter.sb.append("Total of " + Obj.graph.size() + " Obj objects have been visited" + "\n" + "\n"
				);
		Obj.graph.clear();
		
		
		try {
			objectClass.graphUpdate(sb, Obj.graph, argv[2]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		closeIO();
	}
	
}