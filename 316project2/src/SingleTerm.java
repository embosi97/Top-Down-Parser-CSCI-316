import java.util.*;

class SingleTerm extends E
{
	// Term term; inherited from E

	SingleTerm(Term t)
	{
		term = t;
	}

	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		term.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String,Val> state)
	{
		return term.Eval(state);
	}
}