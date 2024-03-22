/*
File: ContactsBST.java
Author: Ram Longman
Date: 8/3/2023
Description: A binary search tree implementation of contacts
*/
public class ContactsBST {

	private Tree root; //the root of the tree
	
	public ContactsBST() {
		root = null; //empty tree
	}
	
	public Contact Search(String name) {
		
		if (root == null) {	//empty tree
			
			//TODO
		}
		else {
			//TODO: Search for the name. Start at the root.
			//Compare the name the current contact's name.
			//If they are equal, contact found.
			//If the name is greater, move to the right subtree
			//If it's smaller move to the left subtree
		}
	}
	
	public void Insert(Contact d) { 
		
		if (root == null) {	//empty tree, insert the first element
			root = new Tree(d);//create root node from the first element
		}
		else {//call the insert method
			//insert in left subtree
			if (d.getName().compareTo(root.data.getName()) < 0) {
				//insert new TreeNode
				if(root.left == null) {
					root.left = new Tree(d);
				}
				else {//continue traversing left subtree recursively
					left.Insert(d);
				}
			}
			//insert in right subtree
			else if(d.getName().compareTo(root.data.getName()) > 0) {
				//insert new TreeNode
				if(root.right == null) {
					root.right = new Tree(d);
				}
				else {//continue traversing right subtree recursively
					right.insert(d);
				}
			}
			//Search for correct location to insert the new contact
			//TODO: similar to the search, but need to keep track of the parent
			//to be able to add the element as a child to the parent
			
			
			//insert new Tree in correct location
			//TODO
		}
	}
	
	public void Print() {
		//TODO: Display all the contacts in alphabetic order
		//Hint: use the PrintInOrder function
	}
	
	private void PrintInOrder(Tree n) {
		//TODO: Display all the contacts in-order
		//Hint: Recursion
	}
	
	public Contact Remove(String name) {//DO NOT MODIFY
		
		if (root == null) {	//can't remove from an empty tree
			System.out.println("\nContact not found.\n");
			return new Contact();
		}
		else { //tree is not empty
			//Search for the contact
			Tree t = root, parent = null;
			boolean left = true;
			while (t != null) {
				if (t.data.getName().equals(name)) { //contact found
					//remove the contact
					if (t.left == null && t.right == null) { //if it's a leaf
						if (left) parent.left = null;
						else parent.right = null;
						return t.data;
					}
					else if (t.left == null) { //there's only a right child
						if (left) parent.left = t.right;
						else parent.right = t.right;
						return t.data;
					}
					else if (t.right == null) { //there's only a left child
						if (left) parent.left = t.left;
						else parent.right = t.left;
						return t.data;
					}
					else { //there are two children
						Tree p = t.left, parent1 = t;
						while (p.right != null) { //find immediate predecessor
							parent1 = p;
							p = p.right; 
						}
						//swap contact to be removed with immediate predecessor
						Contact temp = t.data;
						t.data = p.data; 
						p.data = temp;
						
						//remove from tree
						if (parent1.equals(t)) parent1.left = null;
						else if (p.left == null) parent1.right = null;
						else parent1.right = p.left;
						
						return p.data; //return contact
					}
				}
				else if (name.compareTo(t.data.getName()) < 0) {
					//contact is alphabetically smaller than current node-> move left
					parent = t;
					t = t.left;
					left = true;
				}
				else {
					//contact is alphabetically greater than current node-> move right
					parent = t;
					t = t.right;
					left = false;
				}
			}
			System.out.println("\nContact not found.\n");
			return new Contact();
		}
	}
	
}
