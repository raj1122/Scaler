package com.company.Mar;



import java.util.*;


public class tree17 {
    public TreeNode root;


    static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }


    static class TreeNodeq {
        int hd;
        TreeNode node;

        TreeNodeq(int hd, TreeNode node) {
            this.hd = hd;
            this.node = node;
        }
    }

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

    public void executeFn(ArrayList<Integer> al1, ArrayList<Integer> a2) {
        Integer[] arr1 = {2, 1, 3};
        ArrayList<Integer> levelTree = new ArrayList<>();
        Collections.addAll(levelTree, arr1);
        tree17 tree = new tree17();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        tree.root.right.left = new TreeNode(6);
        tree.root.right.right = new TreeNode(7);
//        tree.root.left.left.left = new TreeNode(8);
//        tree.inorderTraversal(tree.root);
//        tree.buildTree(al1,a2);

//        tree.buildTreefrmPreorder(al1,a2);
//        tree.verticalOrderTraversal(tree.root);
//        tree.SerializeTree(tree.root);
        TreeNode madeRoot = tree.DeserializeTree(levelTree);
        TreeLinkNode madeRootLink = tree.DeserializeTreeLink(levelTree);


//        tree.connect(madeRootLink);
//        tree.postorderTraversal(tree.root);
//        tree.OddEvenLevels(madeRoot);
//        tree.isValidBST(madeRoot);
//        tree.recoverTree(madeRoot);
//        tree.BSTRange(madeRoot,10,51);
//        connect(madeRootLink);
        kthsmallest(madeRoot, 2);

    }



    /*
    *Largest BST Subtree
Problem Description

Given a Binary Tree A with N nodes.

Write a function that returns the size of the largest subtree which is also a Binary Search Tree (BST).

If the complete Binary Tree is BST, then return the size of whole tree.

NOTE:

Largest subtree means subtree with most number of nodes.
    * */

    static class nodeInfo
    {
        int size; // Size of subtree
        int max; // Min value in subtree
        int min; // Max value in subtree
        int ans; // Size of largest BST which
        // is subtree of current node
        boolean isBST; // If subtree is BST

        nodeInfo(){}   // empty constructor

        nodeInfo(int size, int max, int min,
                 int ans, boolean isBST)
        {
            this.size = size;
            this.max = max;
            this.min = min;
            this.ans = ans;
            this.isBST = isBST;
        }
    }
    static int MAX = Integer.MAX_VALUE;
    static int MIN = Integer.MIN_VALUE;

    static nodeInfo largestBST(TreeNode root) {

        // Base cases : When tree is empty or it has
        // one child.
        if (root == null)
            return new nodeInfo(0, MIN, MAX, 0, true);
        if(root.left == null && root.right == null)
            return new nodeInfo(1, root.val, root.val, 1, true);
        nodeInfo left = largestBST(root.left);
        nodeInfo right = largestBST(root.right);

        // Create a return variable and initialize its size.
        nodeInfo returnInfo = new nodeInfo();
        returnInfo.size = 1 + left.size + right.size;


        if(left.isBST && right.isBST && left.max < root.val && right.min > root.val)
        {
            returnInfo.min = Math.min(Math.min(left.min, right.min), root.val);
            returnInfo.max = Math.max(Math.max(left.max, right.max), root.val);

            // Update answer for tree rooted under
            // current 'root'
            returnInfo.ans = returnInfo.size;
            returnInfo.isBST = true;
            return returnInfo;
        }

        // If whole tree is not BST, return maximum
        // of left and right subtrees
        returnInfo.ans = Math.max(left.ans, right.ans);
        returnInfo.isBST = false;
        return returnInfo;

    }
    public int solve(TreeNode A) {
        return largestBST(A).ans;
    }



















    /*Check for BST with One Child
Problem Description

Given preorder traversal of a binary tree, check if it is possible that it is also a preorder traversal of a Binary Search Tree (BST), where each internal node (non-leaf nodes) have exactly one child.*/

    public String solve(ArrayList<Integer> A) {
        int min, max, size = A.size();
        if (A.get(size - 1) > A.get(size - 2)) {
            max = A.get(size - 1);
            min = A.get(size - 2);
        } else {
            max = A.get(size - 2);
            min = A.get(size - 1);
        }


        // Every element must be either smaller than min or
        // greater than max
        for (int i = size - 3; i >= 0; i--) {
            if (A.get(i) < min)
                min = A.get(i);
            else if (A.get(i) > max)
                max = A.get(i);
            else
                return "NO";
        }
        return "YES";
    }


    /*Kth Smallest Element In Tree
Problem Description

Given a binary search tree represented by root A, write a function to find the Bth smallest element in the tree.*/
    public int kthsmallest(TreeNode A, int B) {
        Stack<TreeNode> st = new Stack<>();

        int cnt = 0;
        TreeNode curr = A;

        while (curr != null || !st.isEmpty()) {
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }


            if (curr == null) {
                curr = st.peek();
                cnt++;
                if (cnt == B)
                    return curr.val;
                st.pop();
//                System.out.println(curr.val);
                curr = curr.right;
            }
        }

        return 0;

    }

    /*BST nodes in a range
Problem Description

Given a binary search tree of integers. You are given a range B and C.

Return the count of the number of nodes that lies in the given range.*/
    public int BSTRange(TreeNode A, int B, int C) {
        long ans = 0;
        Stack<TreeNode> st = new Stack<>();
        st.push(A);
        TreeNode curr = null;
        while (!st.isEmpty()) {
            curr = st.pop();

            if (curr.val >= B && curr.val <= C) {
                ans++;
                if (curr.right != null)
                    st.push(curr.right);
                if (curr.left != null)
                    st.push(curr.left);
            } else if (curr.val < B && curr.right != null)
                st.push(curr.right);
            else if (curr.left != null)
                st.push(curr.left);
        }
        return (int) ans;
    }

    /*Common Nodes in Two BST
Problem Description

Given two BST's A and B, return the (sum of all common nodes in both A and B) % (109 +7) .

In case there is no common node, return 0.

NOTE:

Try to do it one pass through the trees.*/

    public int CommonNodes(TreeNode A, TreeNode B) {
        ArrayList<Integer> arr = inorderTraversal(A);
        ArrayList<Integer> arrB = inorderTraversal(A);
        long sum = 0;
        long mod = (long) (1e9 + 7);
        int nA, nB;

        nA = arr.size();
        nB = arrB.size();

        int j = 0;
        int i = 0;

        while (i < nA && j < nB) {

            if (arr.get(i).intValue() == arrB.get(j).intValue()) {
                sum = ((sum % mod) + (arr.get(i) % mod) % mod);

                i++;
                j++;
            } else if (arr.get(i) < arrB.get(j)) {
                i++;
            } else {
                j++;
            }

        }
        return (int) sum;

    }


    /*Sorted Array To Balanced BST
Problem Description

Given an array where elements are sorted in ascending order, convert it to a height Balanced Binary Search Tree (BBST).

Balanced tree : a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.*/

    public TreeNode sortedArrayToBST(final int[] A) {
        return createBst(A, 0, A.length - 1);
    }

    private TreeNode createBst(int[] a, int start, int end) {
        if (start > end)
            return null;

        int mid = (start + end) / 2;

        TreeNode root = new TreeNode(a[mid]);
        root.left = createBst(a, start, mid - 1);
        root.right = createBst(a, mid + 1, end);
        return root;

    }

    /*2-Sum Binary Tree
Problem Description

Given a binary search tree A, where each node contains a positive integer, and an integer B, you have to find whether or not there exist two different nodes X and Y such that X.value + Y.value = B.

Return 1 to denote that two such nodes exist. Return 0, otherwise.

*/
    public int t2Sum(TreeNode A, int B) {
        ArrayList<Integer> ans = inorderTraversal(A);
        int l = 0;
        int r = ans.size() - 1;
        while (l < r) {
            int sum = ans.get(l) + ans.get(r);
            if (sum == B)
                return 1;
            else if (sum > B)
                r--;
            else l++;
        }

        return 0;

    }
/*Recover Binary Search Tree
Problem Description

Two elements of a binary search tree (BST),represented by root A are swapped by mistake. Tell us the 2 values swapping which the tree will be restored.

A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?*/

    public ArrayList<Integer> recoverTree(TreeNode A) {
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();

        //Two nodes first and Second
        TreeNode first = null;
        TreeNode second = null;
        TreeNode curr = A;
        TreeNode prev = null;

        //usign INorder traveral to check nodes not in position w.r.t increasing order
        while (curr != null || !st.isEmpty()) {
            if (curr != null) {
                st.push(curr);
                curr = curr.left;

            }

            if (curr == null) {
                curr = st.pop();
                //if we find element not in place we store that element
                if (prev != null && prev.val > curr.val) {
                    //if first is null then first =prev and second curr
                    if (first == null) {
                        first = prev;
                        prev = curr;
                        second = curr;
                    } else { //if we found another element that is not in place in right side,we update second
                        second = curr;
                        prev = curr;  //also updating prev so that we cn iterate through right side
                    }
                } else {
                    prev = curr;
                }

                curr = curr.right;
            }
        }
        ans.add(first.val);
        ans.add(second.val);
        return ans;
    }
    /*Valid Binary Search Tree
Problem Description

Given a binary tree represented by root A.

Assume a BST is defined as follows:

1) The left subtree of a node contains only nodes with keys less than the node's key.

2) The right subtree of a node contains only nodes with keys greater than the node's key.

3) Both the left and right subtrees must also be binary search trees.*/

    public int isValidBST(TreeNode A) {

        if (isValidBST1(A) == true)
            return 1;
        return 0;

    }

    TreeNode prev;

    private boolean isValidBST1(TreeNode root) {
        if (root != null) {
            if (!isValidBST1(root.left))
                return false;

            // allows only distinct values node
            if (prev != null && root.val <= prev.val)
                return false;
            prev = root;
            return isValidBST1(root.right);
        }
        return true;
    }

    /*Identical Binary Trees
Problem Description

Given two binary trees, check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.*/


    public int isSameTree(TreeNode A, TreeNode B) {
        if (A != null && B == null)
            return 0;

        if (A == null && B != null)
            return 0;

        if (A == null && B == null)
            return 1;

        if (A.val == B.val && isSameTree(A.left, B.left) == 1 && isSameTree(A.right, B.right) == 1)
            return 1;

        return 0;
    }
    /*Counting the Nodes
Problem Description

Given the root of a tree A with each node having a certain value, find the count of nodes which have more value than all its ancestor*/

    public int CountingNodes(TreeNode A) {
        Queue<TreeNode> q = new LinkedList<>();
        if (A == null)
            return 0;

        int max = Integer.MIN_VALUE;
        int ans = 0;
        TreeNode curr = null;
        TreeNode currMax = null;
        q.add(A);
        q.add(new TreeNode(max));
        while (!q.isEmpty()) {
            curr = q.poll();
            currMax = q.poll();

            if (curr.val > currMax.val)
                ans++;
            if (curr.left != null) {
                q.add(curr.left);
                q.add(new TreeNode(Math.max(currMax.val, curr.val)));
            }

            if (curr.right != null) {
                q.add(curr.right);
                q.add(new TreeNode(Math.max(currMax.val, curr.val)));
            }


        }
        return ans;
    }

    /*Balanced Binary Tree
Problem Description

Given a root of binary tree A, determine if it is height-balanced.

A height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.*/

    public int isBalanced(TreeNode A) {

        if (A == null)
            return 1;

        int lh = heightTree(A.left);
        int rh = heightTree((A.right));
        if (Math.abs(rh - lh) <= 1 && isBalanced(A.left) == 1 && isBalanced(A.right) == 1)
            return 1;


        return 0;
    }

    private int heightTree(TreeNode a) {
        if (a == null)
            return -1;
        else {
            int lheight = heightTree(a.left);
            int rheight = heightTree(a.right);
            return 1 + Math.max(lheight, rheight);
        }
    }


    /*ZigZag Level Order Traversal BT
Problem Description

Given a binary tree, return the zigzag level order traversal of its nodes values. (ie, from left to right, then right to left for the next level and alternate between).*/

    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Stack<TreeNode> q1 = new Stack<>();
        Stack<TreeNode> q2 = new Stack<>();
        TreeNode curr = null;
        q1.add(A);
        ArrayList<Integer> res = new ArrayList<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            res = new ArrayList<>();
            while (!q1.isEmpty()) {

                curr = q1.pop();
                res.add(curr.val);

                if (curr.left != null)
                    q2.push(curr.left);

                if (curr.right != null)
                    q2.push(curr.right);
            }
            if (!res.isEmpty())
                ans.add(res);

            res = new ArrayList<>();
            while (!q2.isEmpty()) {
                curr = q2.pop();
                res.add(curr.val);

                if (curr.right != null)
                    q1.push(curr.right);

                if (curr.left != null)
                    q1.push(curr.left);
            }
            if (!res.isEmpty())
                ans.add(res);
        }
        return ans;

    }

    /*Postorder Traversal
Problem Description

Given a binary tree, return the Postorder traversal of its nodes values.

NOTE: Using recursion is not allowed.

*/
    public ArrayList<Integer> postorderTraversal(TreeNode A) {
        ArrayList<Integer> ans = new ArrayList<>();


        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        st1.push(A);
        while (!st1.isEmpty()) {
            TreeNode curr = st1.pop();
            st2.push(curr);

            if (curr.left != null)
                st1.push(curr.left);
            if (curr.right != null)
                st1.push(curr.right);
            System.out.println(Arrays.deepToString(st2.toArray()));
        }

        while (!st2.isEmpty()) {
            ans.add(st2.pop().val);
        }
        return ans;
    }

    /*Preorder Traversal
Problem Description

Given a binary tree, return the preorder traversal of its nodes values.

NOTE: Using recursion is not allowed.*/
    public ArrayList<Integer> preorderTraversal(TreeNode A) {
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        st.push(A);
        TreeNode curr = null;
        while (!st.isEmpty()) {
            curr = st.pop();
            ans.add(curr.val);

            //as using stack so insert right first
            if (curr.right != null)
                st.push(curr.right);
            if (curr.left != null)
                st.push(curr.left);
        }
        return ans;
    }

    /*Next Pointer Binary Tree
Problem Description

Given a binary tree,

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Assume perfect binary tree and try to solve this in constant extra space.*/

    public void connect(TreeLinkNode root) {
        if (root == null)
            return;
        Queue<TreeLinkNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            TreeLinkNode curr = q.poll();
            if (curr == null && q.isEmpty())
                return;
            else if (curr == null) {
                q.add(null);
            } else {
                curr.next = q.peek();
                if (curr.left != null)
                    q.add(curr.left);
                if (curr.right != null)
                    q.add(curr.right);
            }
        }

    }


    /*Path Sum
Problem Description

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.*/


    public int hasPathSum(TreeNode A, int B) {
        boolean hasPath = hasPathSum1(A, B);
        if (hasPath == true)
            return 0;
        else
            return 1;
    }

    private boolean hasPathSum1(TreeNode a, int sum) {
        if (a == null)
            return false;
        else if (a.left == null && a.right == null && sum - a.val == 0)
            return true;
        else
            return hasPathSum1(a.left, sum - a.val) || hasPathSum1(a.right, sum - a.val);

    }



    /*Odd and Even Levels
Problem Description

Given a binary tree of integers. Find the difference between the sum of nodes at odd level and sum of nodes at even level.

NOTE: Consider the level of root node as 1.*/

    public long calculateModOnAddition(long num1, long num2) {
        num1 = num1 % 1000000007;
        num2 = num2 % 1000000007;
        return ((num1 + num2 + 1000000007) % 1000000007 + 1000000007) % 1000000007;
    }

    public int OddEvenLevels(TreeNode A) {
        if (A == null)
            return 0;
        int level = 1;
        TreeNodeq curr = new TreeNodeq(level, A);
        Queue<TreeNodeq> q = new LinkedList<>();

        q.add(curr);
        long sumOdd = 0;
        long sumEven = 0;
        while (!q.isEmpty()) {
            curr = q.poll();
            if ((curr.hd % 2) == 0) {
                sumEven = calculateModOnAddition(sumEven, curr.node.val);
            } else {
                sumOdd = calculateModOnAddition(sumOdd, curr.node.val);
            }

            if (curr.node.left != null && curr.node.left.val != -1)
                q.add(new TreeNodeq(curr.hd + 1, curr.node.left));

            if (curr.node.right != null && curr.node.right.val != -1)
                q.add(new TreeNodeq(curr.hd + 1, curr.node.right));
        }

        long t1 = sumEven - sumOdd;
        return (int) t1;
    }
    /*Serialize Binary Tree
Problem Description

Given the root node of a Binary Tree denoted by A. You have to Serialize the given Binary Tree in the described format.

Serialize means encode it into a integer array denoting the Level Order Traversal of the given Binary Tree.

NOTE:

In the array, the NULL/None child is denoted by -1.
For more clarification check the Example Input.
*/

    public ArrayList<Integer> SerializeTree(TreeNode A) {
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode curr = null;
        ArrayList<Integer> ans = new ArrayList<>();

        q.add(A);
        while (!q.isEmpty()) {
            curr = q.peek();
            q.poll();
            ans.add(curr.val);

            if (curr.val == -1)
                continue;
            if (curr.left != null)
                q.add(curr.left);
            else
                q.add(new TreeNode(-1));

            if (curr.right != null)
                q.add(curr.right);
            else
                q.add(new TreeNode(-1));
        }

        return ans;
    }

    /*Vertical Order traversal
Problem Description

Given a binary tree, return a 2-D array with vertical order traversal of it. Go through the example and image for more details.*/


    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();


        if (root == null)
            return ans;
        TreeNodeq curr = null;
        //using level order traversal
        TreeMap<Integer, ArrayList<Integer>> hm = new TreeMap<>();
        Queue<TreeNodeq> q = new LinkedList<>();

        int height = 0;

        q.add(new TreeNodeq(0, A));
        while (!q.isEmpty()) {
            curr = q.poll();
            height = curr.hd;
            //if vertical level is already present
            if (hm.containsKey(height)) {
                hm.get(height).add(curr.node.val);
            } else {
                ArrayList<Integer> al = new ArrayList<>();
                al.add(curr.node.val);
                hm.put(height, al);
            }

            if (curr.node.left != null)
                q.add(new TreeNodeq(height - 1, curr.node.left));
            if (curr.node.right != null)
                q.add(new TreeNodeq(height + 1, curr.node.right));
        }


        for (Map.Entry<Integer, ArrayList<Integer>> entry : hm.entrySet()) {
            ArrayList<Integer> al = entry.getValue();
            ans.add(al);

        }


        return ans;

    }



    /*Binary Tree From Inorder And Preorder
Problem Description

Given preorder and inorder traversal of a tree, construct the binary tree.

NOTE: You may assume that duplicates do not exist in the tree.



Problem Constraints
1 <= number of nodes <= 105



Input Format
First argument is an integer array A denoting the preorder traversal of the tree.

Second argument is an integer array B denoting the inorder traversal of the tree.



Output Format
Return the root node of the binary tree.*/


    static int preIdx;

    public TreeNode buildTreefrmPreorder(ArrayList<Integer> A, ArrayList<Integer> B) {
        Map<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < A.size(); i++)
            hm.put(B.get(i), i);

        preIdx = 0;
        return buildTreefromPreorder(A, B, hm, 0, A.size() - 1);
    }

    private TreeNode buildTreefromPreorder(ArrayList<Integer> pre, ArrayList<Integer> in, Map<Integer, Integer> hm, int is, int ie) {
        //Root is null
        if (is > ie)
            return null;

        int curr = pre.get(preIdx);
        preIdx++;
        TreeNode node = new TreeNode(curr);

        //Only one node it has no left and right subtree
        if (is == ie)
            return node;
        int iIndex = hm.get(curr);

        //left  subtree will be build first as psIdx will be -- (VLR)
        node.left = buildTreefromPreorder(pre, in, hm, is, iIndex - 1);
        node.right = buildTreefromPreorder(pre, in, hm, iIndex + 1, ie);


        return node;
    }


    /*Deserialize Binary Tree
Problem Description

Given an integer array A denoting the Level Order Traversal of the Binary Tree.

You have to Deserialize the given Traversal in the Binary Tree and return the root of the Binary Tree.

NOTE:

In the array, the NULL/None child is denoted by -1.
For more clarification check the Example Input.*/

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

    public TreeLinkNode DeserializeTreeLink(ArrayList<Integer> A) {


        Queue<TreeLinkNode> q = new LinkedList<>();
        TreeLinkNode head = null, temp = null;
        int i = 0;
        head = new TreeLinkNode(A.get(i));
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
                temp.left = new TreeLinkNode(A.get(l));
                q.add(temp.left);
            }

            if (A.get(r) != -1) {
                temp.right = new TreeLinkNode(A.get(r));
                q.add(temp.right);
            }

        }

        return head;

    }

    /*Binary Tree From Inorder And Postorder
Problem Description

Given inorder and postorder traversal of a tree, construct the binary tree.

NOTE: You may assume that duplicates do not exist in the tree.*/

    static int psIDx;

    public TreeNode buildTree(ArrayList<Integer> A, ArrayList<Integer> B) {
        Map<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < A.size(); i++)
            hm.put(A.get(i), i);

        psIDx = A.size() - 1;
        return buildTree1(A, B, hm, 0, A.size() - 1);
    }

    private TreeNode buildTree1(ArrayList<Integer> in, ArrayList<Integer> post, Map<Integer, Integer> hm, int is, int ie) {
        //Root is null
        if (is > ie)
            return null;

        int curr = post.get(psIDx);
        psIDx--;
        TreeNode node = new TreeNode(curr);

        //Only one node it has no left and right subtree
        if (is == ie)
            return node;
        int iIndex = hm.get(curr);

        //right subtree will be build first as psIdx will be -- (LRV)
        node.right = buildTree1(in, post, hm, iIndex + 1, ie);
        node.left = buildTree1(in, post, hm, is, iIndex - 1);

        return node;

    }


    /*Inorder Traversal
Problem Description

Given a binary tree, return the inorder traversal of its nodes values.

NOTE: Using recursion is not allowed.

*/
    public ArrayList<Integer> inorderTraversal(TreeNode A) {

        ArrayList<Integer> res = new ArrayList<>();

        Stack<TreeNode> st = new Stack<>();

        TreeNode curr = A;


        while (curr != null || !st.isEmpty()) {
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }


            if (curr == null) {
                curr = st.peek();
                st.pop();
//                System.out.println(curr.val);
                res.add(curr.val);
                curr = curr.right;
            }
        }

        return res;
    }

    /*Level Order
Problem Description

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).*/


    public ArrayList<ArrayList<Integer>> levelOrder1(TreeNode A) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        ArrayList<Integer> ans = new ArrayList<>();
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();


        q1.add(A);
        while (!q1.isEmpty() || !q2.isEmpty()) {
            ans = new ArrayList<>();
            while (!q1.isEmpty()) {


                TreeNode curr = q1.peek();
                q1.remove();
                ans.add(curr.val);
                if (curr.left != null)
                    q2.add(curr.left);
                if (curr.right != null)
                    q2.add(curr.right);
            }
            if (!ans.isEmpty())
                res.add(ans);

            ans = new ArrayList<>();

            while (!q2.isEmpty()) {
                TreeNode curr = q2.peek();
                q2.remove();
                ans.add(curr.val);
                if (curr.left != null)
                    q1.add(curr.left);
                if (curr.right != null)
                    q1.add(curr.right);
            }


            if (!ans.isEmpty())
                res.add(ans);
        }


        return res;
    }

}
