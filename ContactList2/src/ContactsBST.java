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
		if (root == null) {	
			return null;//If tree is empty, there is nothing to find.
		}
		else {
			Tree t = root, parent = null;
			while (t != null) {
				if(name.compareTo(t.data.getName()) == 0) {//If they are equal, contact found.
					System.out.println(t.data);
					t = null;
				}
				else if(name.compareTo(t.data.getName()) < 0) {//If it's smaller move to the left subtree
					parent = t;
					t = t.left;
				}
				else {//If the name is greater, move to the right subtree
					parent = t;
					t = t.right;
				}
			}	
		}
		return new Contact();
	}
	
	public void Insert(Contact d) {
		if (root == null) {	//empty tree, insert the first element
			root = new Tree(d);//create root node from the first element
		}
		else { //tree is not empty
			Tree t = root, parent = null;//need to keep track of parent
			while (t != null) {//because the root exists
				if (t.data.getName().compareTo(d.getName()) > 0) {//If contact needs to go left
					if (t.left == null) {
						t.left = new Tree(d);//make a new tree in the left node
						t = null;//change t to get out of the while loop
					}
					else {//otherwise, do it again but shift to the left
						parent = t;
						t= t.left;
					}
				}
				else {//contact is alphabetically greater than current node-> move right
					if (t.right == null) {
						t.right = new Tree(d);//make a new tree in the right node
						t = null;//change t to get out of the while loop
					}
					else {
						parent = t;
						t = t.right;
					}
				}
			}
		}
	}
	
	public void Print() {//Display all the contacts in alphabetic order
		PrintInOrder(root);	
	}
	
	private void PrintInOrder(Tree node) {//Display all the contacts in-order: left, root, right
		if (node == null) {//if the node is null, don't do anything
			return;
		}
		PrintInOrder(node.left);//traverse left subtree
		System.out.printf("%s%n", node.data);//output node data
		PrintInOrder(node.right);//traverse right subtree
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
