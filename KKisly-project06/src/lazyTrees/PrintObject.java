package lazyTrees;

/**
 * 
 * @author Intrepid
 * * @param <E>
 * functor class PrintObject to traverse and print out data 
   from LazySearchTree
 */
class PrintObject<E> implements Traverser<E>
{
	public void visit(E x) {
		System.out.print(x + " ");
	}
}