import java.util.*;

abstract class E extends Obj
{
	Term term;

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <E>");
	}

	abstract Val Eval(HashMap<String,Val> state);
}