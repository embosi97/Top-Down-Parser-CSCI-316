import java.util.*;

class SinglePrimary extends Term
{
	// Primary primary; inherited from Term

	SinglePrimary(Primary p)
	{
		primary = p;
	}

	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		primary.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String,Val> state)
	{
		return primary.Eval(state);
	}
}