
/**
 * Node of a binary tree
 * @author Deron Washington II
 *  Date: 4/29/20
 */
public class Node<T extends Comparable<T>> implements Cloneable
{
	public T data;
	public Node<T> lLink;
	public Node<T> rLink;

//	/**
//	 * Default Constructor 
//	 */
//	public Node()
//	{
//		data = T();
//		lLink = new Node();
//		rLink = new Node();
//	}
	
	/**
	 * Parameterized Constructor
	 * @param _data = data of the node
	 * @param left = left link
	 * @param right = right link
	 */
	public Node(T _data, Node<T> left, Node<T> right)
	{
		data = _data;
		lLink = left;
		rLink = right;
	}
	
	/**
	 * Only change the data field
	 * @param _data = data of the node
	 */
	public void changeData(T _data)
	{
		data = _data;
	}
	
	/**
	 * Only change the left link of the node
	 * @param left = left link to the next node
	 */
	public void changeLeft(Node<T> left)
	{
		lLink = left;
	}
	
	/**
	 * Only change the right link of the node
	 * @param right = right link to the next node
	 */
	public void changeRight(Node<T> right)
	{
		rLink = right;
	}
	
	/**
	 * Return the data from the field
	 * @return the data field
	 */
	public T getData()
	{
		return data;
	}
	
	/**
	 * Return the left node
	 * @return lLink
	 */
	public Node<T> getLeft()
	{
		return lLink;
	}
	
	/**
	 * Return the right node
	 * @return rLink
	 */
	public Node<T> getRight()
	{
		return rLink;
	}
	
	/**
	 * Method to return a copy of the
	 * "this" object
	 */
	@Override
	protected Object clone()
	{
		
		return new Node<T>(data, lLink, rLink);
		
	}
	
	public String toString()
	{
		return data.toString();
	}
	
	}
