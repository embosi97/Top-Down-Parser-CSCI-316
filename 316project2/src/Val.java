// value objects used for expression evaluation and returned by Eval function

abstract class Val extends Obj
{
	abstract Val cloneVal();
	abstract float floatVal(); // conversion of integer value to float value
	abstract boolean isZero();
}