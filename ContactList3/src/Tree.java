/**1.0
 * A binary tree for holding data
 * 2.0
 * Modified to be a generic type tree
 * @author Daniel Holt
 * @version 2.0
 * Due Date 4/14/24
*/
public class Tree<T> {
	
	Contact data;
	Tree<T> left;
	Tree<T> right;
	
	public Tree(T d) {
		data = (Contact)d;
		left = null;
		right = null;
	}
	
}
