import java.util.*;

class Id extends Primary
{
	String id;

	Id(String ident)
	{
		id = ident;
	}

	public String toString()
	{
		return this.getClass().getName() + " : " + id;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent+" ";

		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " " + id);
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val idVal = state.get(id);
		if ( idVal != null )
			return idVal.cloneVal();
		else
		{
			System.out.println( "variable "+id+" does not have a value" );
			return null;
		}
	}
}