import java.util.*;

abstract class Primary extends Obj
{
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <primary>");
	}

	abstract Val Eval(HashMap<String,Val> state);
}