import java.util.*;

abstract class AssignmentList extends Obj
{
	abstract void printParseTree(String indent);
	abstract void M(HashMap<String,Val> state); // function to interpret this assignment list
}