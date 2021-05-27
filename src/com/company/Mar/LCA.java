package com.company.Mar;


import java.util.*;

public class LCA {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return String.format(val + ",");
        }
    }

    public void executeFn() {
        Integer[] arr1 = {27, 19, 44, 10, 24, 36, -1, -1, -1, -1, -1, -1, -1};
        ArrayList<Integer> levelTree = new ArrayList<>();
        Collections.addAll(levelTree, arr1);
        TreeNode madeRoot = DeserializeTree(levelTree);
//        checkEqualTree(madeRoot);
        Dist2Node(madeRoot, 27, 36);
    }



    
    /*Distance between Nodes of BST
Problem Description

Given a binary search tree.
Return the distance between two nodes with given two keys B and C. It may be assumed that both keys exist in BST.

NOTE: Distance between two nodes is number of edges between them*/

    public int Dist2Node(TreeNode A, int B, int C) {
        int h = calDist(A, Math.min(B, C), Math.max(B, C));

        return h;
    }

    private int calDist(TreeNode a, int min, int max) {
        if (a == null)
            return 0;

        if (a.val > min && a.val > max)
            return calDist(a.left, min, max);
        if (a.val < min && a.val < max)
            return calDist(a.right, min, max);

        if (a.val >= min && a.val <= max)
            return calDistRoot(a, min) + calDistRoot(a, max);
        return 0;
    }

    private int calDistRoot(TreeNode a, int elem) {

        if (a.val == elem)
            return 0;
        else if (a.val > elem)
            return 1 + calDistRoot(a.left, elem);
        else
            return 1 + calDistRoot(a.right, elem);
    }

    /*Equal Tree Partition
Problem Description

Given a binary tree A. Check whether it is possible to partition the tree to two trees which have equal sum of values after removing exactly one edge on the original tree.

*/
    int EqualTree(TreeNode node, Stack<Integer> st) {
        if (node == null)
            return 0;
        int leftSum = EqualTree(node.left, st);
        int rightSum = EqualTree(node.right, st);
        int curr = node.val + leftSum + rightSum;
        st.push(curr);
        return curr;
    }

    public boolean checkEqualTree(TreeNode root) {
        Stack<Integer> st = new Stack<>();
        EqualTree(root, st);
        int totalSum = st.peek();
        st.pop();
        while (!st.empty()) {
            int x = st.peek();
            st.pop();
            int y = totalSum - x;
            if (x == y)
                return true;
        }
        return false;
    }

    /*Sum binary tree or not
Problem Description

Given a binary tree. Check whether the given tree is a Sum-binary Tree or not.

Sum-binary Tree is a Binary Tree where the value of a every node is equal to sum of the nodes present in its left subtree and right subtree.

An empty tree is Sum-binary Tree and sum of an empty tree can be considered as 0. A leaf node is also considered as SumTree.

Return 1 if it sum-binary tree else return 0.*/

    public int SumBinary(TreeNode A) {

        if (SumBinary1(A) == -1)
            return 0;
        return 1;
    }

    public int SumBinary1(TreeNode a) {
        if (a == null)
            return 0;

        if (a.left == null && a.right == null)
            return a.val;
        int left = SumBinary1(a.left);
        int right = SumBinary1(a.right);

        if (left == -1 || right == -1)
            return -1;
        if (a.val == (left + right))
            return (a.val + left + right);
        else
            return -1;

    }

    /*Invert the Binary Tree
Problem Description

Given a binary tree A, invert the binary tree and return it.

Inverting refers to making left child as the right child and vice versa.*/

    public TreeNode invertTree(TreeNode A) {
        if (A == null)
            return null;
        TreeNode temp = A.left;
        A.left = invertTree(A.right);
        A.right = invertTree(temp);
        return A;
    }

    /*Flatten Binary Tree to Linked List
Problem Description

Given a binary tree A, flatten it to a linked list in-place.

The left child of all nodes should be NULL.*/

    public TreeNode flatten(TreeNode a) {
        return flatten1(a);
    }

    public TreeNode flatten1(TreeNode a) {
        if (a == null)
            return null;
        TreeNode left = flatten1(a.left);
        TreeNode right = flatten1(a.right);
        a.left = null;
        if (left == null)
            a.right = right;
        else {
            a.right = left;
            while (left.right != null)
                left = left.right;
            left.right = right;
        }
        return a;
    }

    /*Least Common Ancestor
Problem Description

Find the lowest common ancestor in an unordered binary tree A given two values B and C in the tree.

Lowest common ancestor : the lowest common ancestor (LCA) of two nodes and w in a tree or directed acyclic graph (DAG) is the lowest (i.e. deepest) node that has both v and w as descendants.*/


    boolean leftP = false;
    boolean rightP = false;

    public int lca(TreeNode A, int B, int C) {
        TreeNode ans = lcaFind(A, B, C);

        if (leftP == true && rightP == true)
            return ans.val;
        else
            return -1;

    }

    public TreeNode lcaFind(TreeNode a, int b, int c) {
        if (a == null)
            return null;
        //if either of node matches root then return root
        TreeNode temp = null;
        if (a.val == b) {
            temp = a;
            leftP = true;
        }

        if (a.val == c) {
            temp = a;
            rightP = true;
        }


        TreeNode left = lcaFind(a.left, b, c);
        TreeNode right = lcaFind(a.right, b, c);

        if (temp != null)
            return temp;

        if (left != null && right != null)
            return a;

        if (left == null)
            return right;
        else
            return left;
    }


    public TreeNode DeserializeTree(ArrayList<Integer> A) {


        Queue<TreeNode> q = new LinkedList<>();
        TreeNode head = null, temp = null;
        int i = 0;
        head = new TreeNode(A.get(i));
        q.add(head);
        while (!q.isEmpty()) {

            temp = q.peek();
            q.poll();

            int l = 2 * i + 1;
            int r = 2 * i + 2;
            i++;


            if ((l > A.size() - 1) || (r > A.size() - 1))
                break;

            if (A.get(l) != -1) {
                temp.left = new TreeNode(A.get(l));
                q.add(temp.left);
            }

            if (A.get(r) != -1) {
                temp.right = new TreeNode(A.get(r));
                q.add(temp.right);
            }

        }

        return head;

    }

}
