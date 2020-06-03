import java.util.ArrayList;

/**
 * Binary Tree Data Structure Implementation
 * @author Deron Washington II
 * Date: 4/29/20
 *
 */
public class BinaryTree<T extends Comparable<T>>
{
	private Node<T> root;


	/**
	 * Method to search through the binary tree for the data parameter
	 * @param start = where we are currently in the tree
	 * @param data = data to find in the tree
	 * @return
	 * 				= true if the element is found
	 * 				= false otherwise
	 */
	private boolean search (Node<T> start, T data)
	{
		Node<T> current = start;

		if (current == null)
			return false;

		// found?
		if (current.data.compareTo(data) == 0)	
			return true;

		// current data larger than data?
		else if (current.data.compareTo(data) > 0)
			return search(current.lLink, data);

		// current data smaller than data?
		else if (current.data.compareTo(data) < 0)
			return search(current.rLink, data);



		return false;
	}

	/**
	 * Method to print the tree in the preOrder traversal fashion
	 * (root, left, right) ex. 6, 4, 2, 5, 8, 7, 12
	 */
	private void preOrderTraversal(Node<T> start)
	{
		if (start != null)
		{
			System.out.print(start + " ");
			preOrderTraversal(start.lLink);
			preOrderTraversal(start.rLink);
		}

	}

	/**
	 * Method to print the tree in the postOrder traversal fashion
	 * (left, right, root) ex. 2, 5, 4, 7, 12, 8, 6
	 */
	private void postOrderTraversal(Node<T> start)
	{
		if (start != null)
		{
			postOrderTraversal(start.lLink);
			postOrderTraversal(start.rLink);
			System.out.print(start + " ");
		}
	}


	/**
	 * Method to print the tree in the inOrder traversal fashion
	 * (left, root, right) ex. 2, 4, 5, 6, 7, 8, 12
	 */
	private void inOrderTraversal(Node<T> start)
	{
		if (start != null)
		{
			inOrderTraversal(start.lLink);
			System.out.print(start + " ");
			inOrderTraversal(start.rLink);
		}
	}


	/**
	 * Method to print the tree by level 
	 * (level1, level2, ..., level height) ex. 6, 4, 8, 2, 5, 7, 12, 1
	 * @param start = node to start at
	 */
	private void levelOrderTraversal(Node<T> start)
	{
		int level = 0;
		int height = getHeight(root);

		// print root
		if (start != null && level == 0)
		{
			System.out.print(start + " ");
		}

			
		ArrayList<Node<T>> LRNodes = new ArrayList<Node<T>>();
		Node<T> current = start;

		// print each level which corresponds with height
		for (int i = 0; i < height; i++)
		{			
			if (i == 0)
				LRNodes.addAll(printLR(current));

			if (LRNodes.get(0) != null)
				LRNodes.addAll(printLR(LRNodes.get(0)));

			if (LRNodes.get(1) != null)
				LRNodes.addAll(printLR(LRNodes.get(1)));

			// get rid of what we just printed
			LRNodes.remove(0);
			LRNodes.remove(0);

			level++;
		}

	}


	/**
	 * Method to return the height of the binary tree
	 * @param node = node that the height is being checked from
	 * @return
	 * 				= height of binary tree
	 */
	private int getHeight(Node<T> node)
	{
		// height = the number of nodes from root to the furthest leaf
		if (node == null)	
			return 0;

		if (node == root)
			return Integer.max(getHeight(node.lLink), getHeight(node.rLink));

		else
			return 1 + Integer.max(getHeight(node.lLink), getHeight(node.rLink));
	}


	/**
	 * Print left and right nodes of node
	 * @param node = "root" node
	 */
	private ArrayList<Node<T>> printLR(Node<T> node)
	{
		if (node == null)
			return null;
		
		ArrayList<Node<T>> leaves = new ArrayList<Node<T>>();

		if (node.lLink != null)
		{
			System.out.print(node.lLink + " ");
			leaves.add(node.lLink);
		}

		else 
			leaves.add(null);

		if (node.rLink != null)
		{
			System.out.print(node.rLink + " ");
			leaves.add(node.rLink);
		}

		else
			leaves.add(null);

		return leaves;
	}


	/**
	 * Method to determine the number of leaves in a binary tree
	 * @param node = node to count from
	 * @return
	 * 				= the number of leaves in a binary tree
	 */
	private int leavesCount(Node<T> node)
	{
		int numLeaves = 0;

		if (node != null)
		{

			if (node.lLink == null && node.rLink == null)
				numLeaves++;

			numLeaves += leavesCount(node.lLink);
			numLeaves += leavesCount(node.rLink);
		}

		return numLeaves;

	}


	/**
	 * Method to determine the number of nodes in a binary tree
	 * @param node = node to count from
	 * @return
	 * 				= num of nodes in the tree from node (parameter)
	 */
	private int nodesCount(Node<T> node)
	{		
		int numNodes = 0;

		if (node != null)
		{
			numNodes++;
			numNodes += nodesCount(node.lLink);
			numNodes += nodesCount(node.rLink);
		}

		return numNodes;
	}

	/**
	 * Method to house the recursive insert method
	 * @param data = data to insert into the binary tree
	 */
	private void recursiveInsertHouse(T data)
	{
		if (root == null)
		{
			root = new Node<T>(data, null, null);
			return;
		}

		recursiveInsert(root, data);
	}


	/**
	 * Method to house the iterative insert method
	 * @param data = data to insert into the binary tree
	 */
	private void iterativeInsertHouse(T data)
	{
		if (root == null)
		{
			root = new Node<T>(data, null, null);
			return;
		}

		iterativeInsert(root, data);
	}

	/**
	 * Insert a node into a binary tree a recursive fashion
	 * @param data = data to insert into the binary tree
	 * @return 
	 * 				= a node representing the last node accessed in the tree
	 */
	private Node<T> recursiveInsert(Node<T> _current, T data)
	{
		if (_current == null)
		{
			_current = new Node<T>(data, null, null);
			return _current;
		}

		// data to insert is greater than current data
		if (_current.data.compareTo(data) < 0)
			_current.rLink = recursiveInsert(_current.rLink, data);

		// data to insert is less than current data
		else if (_current.data.compareTo(data) > 0)
			_current.lLink = recursiveInsert(_current.lLink, data);

		return _current;

	}


	/**
	 * Insert a node into a binary tree in an iterative fashion
	 * @param data = data to insert into the tree
	 */
	private void iterativeInsert(Node<T> _current, T data)
	{
		if (root == null)
		{
			root = new Node<T>(null, null, null);
			root.changeData(data);
			return;
		}

		// keeps track of the previous spot that was accessed in the tree
		Node<T> previous = null;

		// keep track of which part of the tree was previously accessed
		boolean left = false;
		boolean right = false;

		// look for spot to insert in the binary tree
		while (_current != null)
		{

			// don't need to add it (already exists in the tree)
			if (_current.data.compareTo(data) == 0)
				return;

			// data to insert > current data?
			else if (_current.data.compareTo(data) < 0)
			{
				// yes, explore right subtree
				previous = _current;
				left = false;
				right = true;
				_current = _current.rLink;
				continue;
			}

			// data to insert < current data?
			else if (_current.data.compareTo(data) > 0)
			{
				// yes, explore left subtree
				previous = _current;
				left = true;
				right = false;
				_current = _current.lLink;
				continue;
			}

		}

		if (_current == null)
		{
			_current = new Node<T>(data, null, null);

			if (left == true)
				previous.lLink = _current;

			else if (right == true)
				previous.rLink = _current;

			return;
		}
	}


	/**
	 * Delete the data parameter in the binary tree if the data exists
	 * @param previous = last node in the tree that has been visited
	 * @param current = the current node being visited
	 * @param data = data to be deleted
	 */
	private void delete(Node<T> previous, Node<T> current, T data)
	{
		// tree is empty or node is null
		if (root == null || current == null)
			return;

		// found it
		if (current.data.compareTo(data) == 0)
		{
			// restructure the tree
			while (current.lLink != null || current.rLink != null)
			{
				// restructure the left side
				if (current.lLink != null)
				{
					T temp = current.data;
					current.data = current.lLink.data;
					current.lLink.data = temp;
					previous = current;
					current = current.lLink;
				}

				// restructure the right side
				else if (current.rLink != null)
				{
					T temp = current.data;
					current.data = current.rLink.data;
					current.rLink.data = temp;
					previous = current;
					current = current.rLink;
				}
				
			}

			// perform the deletion if the data to be deleted is a right child
			if (previous.rLink != null)
			{
				if (previous.rLink.data.compareTo(data) == 0)
					previous.rLink = null;
			}

			// perform the deletion if the data to be deleted is a left child
			if (previous.lLink != null)
			{
				if (previous.lLink.data.compareTo(data) == 0)
					previous.lLink = null;
			}
			
		}

		// DIDN'T FIND IT
		
		// current data is greater than data to delete
			delete(current, current.lLink, data);

		// current data is less than data to delete
			delete(current, current.rLink, data);

	}

	/**
	 * Constructor for binary tree to be created with
	 * a root node
	 * @param _root = root of the binary tree
	 */
	private void BinaryTree(Node<T> _root)
	{
		root = _root;
	}

	/**
	 * Constructor for binary tree to create
	 * a brand new node
	 */
	private void BinaryTree()
	{
		root = null;
	}


	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		Node<Integer> node = new Node<Integer>(null, null, null);
		Node<Integer> node2 = new Node<Integer>(8, null , null);

		node.changeData(6);
		//node2.changeData(8);
		System.out.print(node.getData() + "\n");
		System.out.print(node2.getData() + "\n");

		node = (Node<Integer>) node2.clone();
		System.out.print(node.getData() + "\n");
		System.out.print(node2.getData() + "\n");

		node.changeData(10);
		System.out.print(node.getData() + "\n");
		System.out.print(node2.getData() + "\n");


		//		bt.recursiveInsertHouse(6);
		//		bt.recursiveInsertHouse(8);
		//		bt.recursiveInsertHouse(4);
		//		bt.recursiveInsertHouse(2);
		//		bt.recursiveInsertHouse(5);
		//		bt.recursiveInsertHouse(7);
		//		bt.recursiveInsertHouse(12);


		bt.iterativeInsertHouse(6);
		bt.iterativeInsertHouse(8);
		bt.iterativeInsertHouse(4);
		bt.iterativeInsertHouse(2);
		bt.iterativeInsertHouse(5);
		bt.iterativeInsertHouse(7);
		bt.iterativeInsertHouse(12);
		bt.iterativeInsertHouse(1);

		System.out.println("\n Preorder Traversal --------------------");
		bt.preOrderTraversal(bt.root);

		System.out.println("\n\n\n Postorder Traversal --------------------");
		bt.postOrderTraversal(bt.root);

		System.out.println("\n\n\n Inorder Traversal --------------------");
		bt.inOrderTraversal(bt.root);

		System.out.println("\n\n\n Level Order Traversal --------------------");
		bt.levelOrderTraversal(bt.root);

		System.out.printf("\n\n\n There are %d nodes in the binary tree.", bt.nodesCount(bt.root));

		System.out.printf("\n\n\n There are %d leaves in the binary tree.", bt.leavesCount(bt.root));

		System.out.println("\n\n\n 12 is in the binary tree.   ");
		System.out.print(bt.search(bt.root, 12));

		System.out.println("\n\n\n 34 is in the binary tree.   ");
		System.out.print(bt.search(bt.root, 34));


		bt.delete(bt.root, bt.root, 6);

		System.out.println("\n\nAfter Deleting 6 \nPreorder Traversal --------------------");
		bt.preOrderTraversal(bt.root);

		System.out.println("\n\n\n Postorder Traversal --------------------");
		bt.postOrderTraversal(bt.root);

		System.out.println("\n\n\n Inorder Traversal --------------------");
		bt.inOrderTraversal(bt.root);

		System.out.println("\n\n\n Level Order Traversal --------------------");
		bt.levelOrderTraversal(bt.root);

		bt.delete(bt.root,  bt.root, 5);
		
		System.out.println("\n\n\n After Deleting 5 \nPreorder Traversal --------------------");
		bt.preOrderTraversal(bt.root);

		System.out.println("\n\n\n Postorder Traversal --------------------");
		bt.postOrderTraversal(bt.root);

		System.out.println("\n\n\n Inorder Traversal --------------------");
		bt.inOrderTraversal(bt.root);

		System.out.println("\n\n\n Level Order Traversal --------------------");
		bt.levelOrderTraversal(bt.root);
	}

}
