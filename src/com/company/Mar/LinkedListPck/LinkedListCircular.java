package com.company.Mar.LinkedListPck;


// Java Program to convert a Binary Tree to a
// Circular Doubly Linked List

// Node class represents a Node of a Tree
class Node
{
    int val;
    Node left,right;

    public Node(int val)
    {
        this.val = val;
        left = right = null;
    }
}

public class LinkedListCircular {

    Node root;
    public LinkedListCircular()
    {
        root = null;
    }

    public Node concatenate(Node leftList,Node rightList)
    {
        // If either of the list is empty, then
        // return the other list
        if (leftList == null)
            return rightList;
        if (rightList == null)
            return leftList;

        // Store the last Node of left List
        Node leftLast = leftList.left;

        // Store the last Node of right List
        Node rightLast = rightList.left;

        // Connect the last node of Left List
        // with the first Node of the right List
        leftLast.right = rightList;
        rightList.left = leftLast;

        // left of first node refers to
        // the last node in the list
        leftList.left = rightLast;

        // Right of last node refers to the first
        // node of the List
        rightLast.right = leftList;

        // Return the Head of the List
        return leftList;
    }

    // Method converts a tree to a circular
    // Link List and then returns the head
    // of the Link List
    public Node bTreeToCList(Node root)
    {
        if (root == null)
            return null;

        // Recursively convert left and right subtrees
        Node left = bTreeToCList(root.left);
        Node right = bTreeToCList(root.right);

        // Make a circular linked list of single node
        // (or root). To do so, make the right and
        // left pointers of this node point to itself
        root.left = root.right = root;

        // Step 1 (concatenate the left list with the list
        //         with single node, i.e., current node)
        // Step 2 (concatenate the returned list with the
        //         right List)
        Node temp=concatenate(left, root);
        return concatenate(temp, right);
    }
    public  void exeucte()
    {
        // Build the tree
        LinkedListCircular tree = new LinkedListCircular();
        tree.root = new Node(10);
        tree.root.left = new Node(12);
        tree.root.right = new Node(15);
        tree.root.left.left = new Node(25);
        tree.root.left.right = new Node(30);
        tree.root.right.left = new Node(36);

        // head refers to the head of the Link List
        Node head = tree.bTreeToCList(tree.root);


    }

}
