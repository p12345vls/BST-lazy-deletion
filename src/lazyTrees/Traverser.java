
package lazyTrees;

/**
 * The Interface Traverser.
 *
 * @param <E> the element type
 */
public interface Traverser<E> {
	
	/**
	 * Visit.
	 *
	 * @param x the x
	 */
	public void visit(E x);
}
