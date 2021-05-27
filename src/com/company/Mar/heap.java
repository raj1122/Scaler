package com.company.Mar;

import java.util.*;

public class heap {
    static ListNode head1;
    static ListNode head2;

    class kheap implements Comparable<kheap> {
        int val;
        int r;
        int c;

        public kheap(int val, int r, int c) {
            this.val = val;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(kheap o) {
            if (this.val == o.val) {
                if (this.r > o.r)
                    return -1;
                else if (this.r < o.r)
                    return 1;
                else {
                    if (this.c >= o.c)
                        return 1;
                    else
                        return -1;
                }


            }
            return this.val - o.val;
        }
    }

    class fractionData implements Comparable<fractionData> {
        double val;
        double a;
        double b;
        int idx;

        public fractionData( double a, double b, int idx) {
            this.val = a / b;
            this.a = a;
            this.b = b;
            this.idx = idx;
        }

        @Override
        public int compareTo(fractionData o) {
            if(this.val>o.val)
            {
                return 1;
            }
            else
                return -1;

        }
    }

    class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    public void executefn() {

        Integer[] arr1 = {1,3,4};
        ArrayList<Integer> levelTree = new ArrayList<>();
        Collections.addAll(levelTree, arr1);
        ArrayList<ArrayList<Integer>> dblArr = creatDbtArr1();

        ArrayList<ListNode> a = creatDblArrayList();

    }


    /*Kth Smallest Element in a Sorted Matrix
Problem Description

Given a sorted matrix of integers A of size N x M and an integer B.

Each of the rows and columns of matrix A are sorted in ascending order, find the Bth smallest element in the matrix.

NOTE: Return The Bth smallest element in the sorted order, not the Bth distinct element.

*/

    public void minHeapifyKth(kheap harr[], int i, int heap_size) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int min = i;

        if (l < heap_size && harr[l].val < harr[min].val) {
            min = l;
        }
        if (r < heap_size && harr[r].val == 0) {
            minHeapifyKth(harr, i, harr.length);
            i--;
        }
    }


    public int Kthsmalles1(ArrayList<ArrayList<Integer>> A, int B) {
        PriorityQueue<kheap> min = new PriorityQueue<>();

        int n = A.get(0).size();


        for (int i = 0; i < n; i++) {
            min.add(new kheap(A.get(0).get(i), 0, i));
        }
        kheap hr = new kheap(0, 0, 0);
        for (int i = 1; i <= B; i++) {

            // Get current heap root
            hr = min.poll();
            System.out.print(hr.val + " ");
            int rowIdx = hr.r;
            int colIdx = hr.c;
            if (rowIdx + 1 < A.size()) {
                min.add(new kheap(A.get(rowIdx + 1).get(colIdx), rowIdx + 1, colIdx));
            }
        }
        return hr.val;
    }


    public int Kthsmalles(ArrayList<ArrayList<Integer>> A, int B) {
        PriorityQueue<kheap> min = new PriorityQueue<>();

        int n = A.get(0).size();
        kheap harr[] = new kheap[n];


        for (int i = 0; i < n; i++) {
            harr[i] = new kheap(A.get(0).get(i), 0, i);
        }
        kheap hr = new kheap(0, 0, 0);
        for (int i = 1; i <= B; i++) {

            // Get current heap root
            hr = harr[0];

            // Get next value from column of root's
            // value. If the value stored at root was
            // last value in its column, then assign
            // INFINITE as next value

            int nextVal = hr.r < n - 1 ? A.get(hr.r + 1).get(hr.c) : Integer.MAX_VALUE;

            // Update heap root with next value
            harr[0] = new kheap(nextVal, hr.r + 1, hr.c);

            // Heapify root
            minHeapifyKth(harr, 0, n);
        }
        return hr.val;
    }


    /*Minimum largest element
Problem Description

Given an array A of N numbers, you have to perform B operations. In each operation, you have to pick any one of the N elements and add original value(value stored at index before we did any operations) to it's current value. You can choose any of the N elements in each operation.

Perform B operations in such a way that the largest element of the modified array(after B operations) is minimised. Find the minimum possible largest element after B operations.*/

    class minLar implements  Comparable<minLar>{
        int val;
        int a;
        int idx;

        public minLar(int val, int a, int idx) {
            this.val = val;
            this.a = a;
            this.idx = idx;
        }

        @Override
        public int compareTo(minLar o) {

            return this.val-o.val;
        }
    }
    public int minLarElem(ArrayList<Integer> A, int B) {

        if(A.size()==0)
            return 0;

        if(A.size()==1 && B!=0) {
            long ans=A.get(0);
            for (int i = 0; i < B; i++) {
                ans=ans+A.get(0);

            }
            return (int)ans;
        }



        PriorityQueue<minLar> pq=new PriorityQueue<>();
        long max=Integer.MIN_VALUE;
        int curr[]=new int[A.size()];
        for (int i = 0; i < A.size(); i++) {
            curr[i]=A.get(i);
            pq.add(new minLar(A.get(i)*2,A.get(i),i));
            max=Math.max(max,A.get(i));
        }

        if(B==0)
            return (int) max;


        max=Integer.MIN_VALUE;
        while(B>0)
        {
            minLar top = pq.peek();
            pq.poll();
            int i=top.idx;
            curr[i] +=A.get(i);
            pq.add(new minLar(curr[i]+A.get(i),A.get(i),i));
            B--;
        }

        for (int i = 0; i < A.size(); i++) {
            max=Math.max(max,curr[i]);
        }
        return (int) max;
    }

    /*KEEP SWAPPING
Given a string of lowercase alphabets A of size N and two integers B and C.

You have to perform B swaps, Where ith swap = swap(A[i%N], A[ (i + C)%N ]).

Return the final string A after B swaps.

NOTE: String A is 0 based indexed.

NOTE: Swaps are 1 based indexed.

*/

    public String solve(String A, int B, int C) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int n = A.length(), total = n;
        int a[] = new int[100005];
        String ans = A;
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        for (int i = 1; i <= 1000000; i++) {

            swap(a, i % n, (i + C) % n);
        }
        for (int i = 0; i < n; i++) {
//            m[i] = a[i];
            hm.put(a[i], i);
        }
        int p = B / 1000000;
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < n; j++) {
//                ans[j] = A[m[j]];
//                ans[j] = A[hm.get(j)];
            }
            A = ans;
        }

        for (int i = p * 1000000 + 1; i <= B; i++) {
//            swap(A[i%n],A[(i + C)%n]);
        }
        return A;
    }
    /*B-th Smallest Prime Fraction
Problem Description

A sorted array of integers, A contains 1, plus some number of primes. Then, for every p < q in the list, we consider the fraction p/q.

What is the B-th smallest fraction considered?

Return your answer as an array of integers, where answer[0] = p and answer[1] = q.*/

    public ArrayList<Integer> BPrimeFraction(ArrayList<Integer> A, int B) {
        int n = A.size();
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<fractionData> pq = new PriorityQueue<>();

        for (int i = 0; i < A.size(); i++) {
            if(A.get(0)<A.get(i))
                pq.add(new fractionData(A.get(0),A.get(i),0));
        }

        while(B-- >=0)
        {
            fractionData fData=pq.poll();
            if(B==0)
            {
                ans.add((int)fData.a);
                ans.add((int)fData.b);
                return ans;
            }

            if(fData.idx + 1 < n){
                int idx = fData.idx + 1;
                if(A.get(idx)< fData.b)
                pq.add(new fractionData(A.get(idx), fData.b, idx));
            }
        }
        return ans;

    }

    /*Ways to form Max Heap
Problem Description

Max Heap is a special kind of complete binary tree in which for every node the value present in that node is greater than the value present in it’s children nodes.

Find the number of distinct Max Heap can be made from A distinct integers.

In short, you have to ensure the following properties for the max heap :

Heap has to be a complete binary tree ( A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.)
Every node is greater than all its children.
NOTE: If you want to know more about Heaps, please visit this link. Return your answer modulo 109 + 7.*/

    class pairt {
        long a;
        long b;

        public pairt(long a, long b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            pairt pairt = (pairt) o;
            return a == pairt.a && b == pairt.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    public int solve(int A) {

        HashMap<pairt, Long> hm = new HashMap<pairt, Long>();
        int[] log2 = new int[105];
        int currLog2 = -1;
        int currPower2 = 1;

        // for each power of two find logarithm
        for (int i = 1; i <= A; i++) {
            if (currPower2 == i) {
                currLog2++;
                currPower2 *= 2;
            }
            log2[i] = currLog2;
        }

        long[][] combNCR = getCombs(A);
        long ans = formHeap(A, log2, combNCR);
        return (int) ans;
    }


    public long formHeap(long A, int[] log2, long[][] combNCR) {
        if (A <= 2)
            return 1L;
        long mod = (int) (1e9 + 7);
        //calculating height
        long h = log2[(int) A];

        //last level approx count
        int numh = (1 << h);

        long left_count = 0;

        //last level leaf node calculation  2^h
        long lastLevelLeaf = A - ((1 << h) - 1);

        if (lastLevelLeaf >= (numh / 2)) {
            left_count += (1 << h) - 1;
        } else {
            left_count += (1 << h) - 1 - ((numh / 2) - lastLevelLeaf);
        }

        long t = formHeap(left_count, log2, combNCR);
        long t1 = formHeap((A - left_count - 1), log2, combNCR);
        long ncr = combNCR[(int) (A - 1)][(int) (left_count)];

        t = ((t % mod) * (t1 % mod)) % mod;
        t = ((t % mod) * (ncr % mod)) % mod;
        return t % mod;
    }



    private long[][] getCombs(int A) {
        long[][] combs = new long[A + 1][A + 1];
        for (int n = 1; n <= A; n++) {
            for (int r = 0; r <= n; r++) {
                if (n == r || r == 0) {
                    combs[n][r] = 1;
                } else {
                    combs[n][r] = (combs[n - 1][r] + combs[n - 1][r - 1]) % 1000000007;
                }
            }
        }
        return combs;
    }




    /*Special Median
Problem Description

You are given an array A containing N numbers. This array is called special if it satisfies one of the following properties:

There exists an element A[i] in the array such that A[i] is equal to the median of elements [A[0], A[1], ...., A[i-1]]
There exists an element A[i] in the array such that A[i] is equal to the median of elements [A[i+1], A[i+2], ...., A[N-1]]
Median is the middle element in the sorted list of elements. If the number of elements are even then median will be (sum of both middle elements)/2.

Return 1 if the array is special else return 0.

NOTE:

For A[0] consider only the median of elements [A[1], A[2], ..., A[N-1]] (as there are no elements to the left of it)
For A[N-1] consider only the median of elements [A[0], A[1], ...., A[N-2]]*/

    public int SpecialMedian(ArrayList<Integer> A) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        if (checkMedian(A, min, max))
            return 1;

        int n = A.size();
        for (int i = 0; i < n / 2; i++) {
            long temp = A.get(i);
            A.set(i, A.get(n - i - 1));
            A.set(n - i - 1, (int) temp);
        }
        min.clear();
        max.clear();

        if (checkMedian(A, min, max))
            return 1;
        return 0;
    }

    private boolean checkMedian(ArrayList<Integer> a, PriorityQueue<Integer> min, PriorityQueue<Integer> max) {
        double cMedian = a.get(0);
        add(a.get(0), min, max);
        rebalancingSM(min, max);

        for (int i = 1; i < a.size() - 1; i++) {
            addSM(a.get(i), min, max);
            rebalancingSM(min, max);
            cMedian = getMedianSM(min, max);

            if (cMedian == a.get(i + 1)) {
                return true;
            }
        }

        return false;
    }

    private void rebalancingSM(PriorityQueue<Integer> min, PriorityQueue<Integer> max) {

        if (max.size() - min.size() >= 2)
            min.offer(max.poll());
        else if (min.size() - max.size() >= 2)
            max.offer(min.poll());
    }

    private double getMedianSM(PriorityQueue<Integer> min, PriorityQueue<Integer> max) {
        double median = 0;
        if (min.size() < max.size())
            median = max.peek();
        else if (min.size() > max.size())
            median = min.peek();
        else {
            median = ((double) max.peek() + min.peek()) / 2;
        }
        return median;
    }

    private void addSM(Integer num, PriorityQueue<Integer> min, PriorityQueue<Integer> max) {

        if (max.size() == 0 || num < max.peek()) {
            max.add(num);
        } else {
            min.add(num);
        }
    }


    /*Running Median
Problem Description

Given an array of integers A denoting a stream of integers. New arrays of integer B and C are formed. Each time an integer is encountered in a stream, append it at the end of B and append median of array B at the C.

Find and return the array C.

NOTE:

If the number of elements are N in B and N is odd then consider medain as B[N/2] ( B must be in sorted order).
If the number of elements are N in B and N is even then consider medain as B[N/2-1]. ( B must be in sorted order).*/

    public ArrayList<Integer> RenningMedian(ArrayList<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < A.size(); i++) {

            add(A.get(i), min, max);
            rebalancing(min, max);
            int median = getMedian(min, max);
            ans.add(median);
        }
        return ans;
    }

    private void rebalancing(PriorityQueue<Integer> min, PriorityQueue<Integer> max) {

        if (max.size() - min.size() >= 2)
            min.offer(max.poll());
        else if (min.size() - max.size() >= 2)
            max.offer(min.poll());

    }

    private int getMedian(PriorityQueue<Integer> min, PriorityQueue<Integer> max) {
        int median = 0;
        if (min.size() < max.size())
            median = max.peek();
        else if (min.size() > max.size())
            median = min.peek();
        else {
            median = max.peek();
        }
        return median;
    }

    private void add(Integer num, PriorityQueue<Integer> min, PriorityQueue<Integer> max) {

        if (max.size() == 0 || num < max.peek()) {
            max.add(num);
        } else {
            min.add(num);
        }
    }


    /*Ath largest element
Problem Description

Given an integer array B of size N.

You need to find the Ath largest element in the subarray [1 to i] where i varies from 1 to N. In other words, find the Ath largest element in the sub-arrays [1 : 1], [1 : 2], [1 : 3], ...., [1 : N].

NOTE: If any subarray [1 : i] has less than A elements then output array should be -1 at the ith index.*/

    public ArrayList<Integer> ALargestNum(int A, ArrayList<Integer> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < B.size(); i++) {
            if (pq.size() < A) {
                pq.add(B.get(i));
            } else {
                if (B.get(i) >= pq.peek()) {
                    pq.poll();
                    pq.add(B.get(i));
                }
            }

            if (pq.size() >= A)
                ans.add(pq.peek());
            else
                ans.add(-1);

        }
        return ans;
    }

    /*Merge K Sorted Lists
Problem Description

Given a list containing head pointers of N sorted linked lists. Merge these N given sorted linked lists and return it as one sorted list.

*/
    public ListNode mergeKLists(ArrayList<ListNode> a) {
        ListNode start = null;
        ListNode end = null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                new Comparator<ListNode>() {
                    public int compare(ListNode a, ListNode b) {
                        return a.val - b.val;
                    }
                });

        for (int i = 0; i < a.size(); i++) {
            pq.add(a.get(i));
        }

        while (!pq.isEmpty()) {
            ListNode top = pq.peek();
            pq.remove();
            if (top.next != null)
                pq.add(top.next);

            if (start == null) {
                start = top;
                end = top;
            } else {
                end.next = top;
                end = top;
            }
        }

        return start;

    }


    /*Misha and Candies
Problem Description

Misha loves eating candies. She has given N boxes of Candies.

She decides, every time she will choose a box having the minimum number of candies, eat half of the candies and put the remaining candies in the other box that has the minimum number of candies.
Misha does not like a box if it has the number of candies greater than B so she won't eat from that box. Can you find how many candies she will eat?

NOTE 1: If a box has an odd number of candies then Misha will eat floor(odd/2).

NOTE 2: A same box will not be chosen again.*/

    public int MishaCandies(ArrayList<Integer> A, int B) {

        long eat = 0;
        createMinHeap(A);
        while (A.size() > 0) {
            int min = extractMin(A);
            if (min == -1 || min > B)
                break;
            int eat1 = (int) Math.floor(min / 2);
            eat = eat + eat1;
            int smin = extractMin(A);
            if (smin == -1 || smin > B)
                break;
            int t1 = ((min / 2) + min % 2);
            int t = smin + t1;
            insertKey(A, t);
        }
        return (int) eat;
    }
    /*Magician and Chocolates
Problem Description

Given N bags, each bag contains Bi chocolates. There is a kid and a magician. In one unit of time, kid chooses a random bag i, eats Bi chocolates, then the magician fills the ith bag with floor(Bi/2) chocolates.

Find the maximum number of chocolates that kid can eat in A units of time.

NOTE:

floor() function returns the largest integer less than or equal to a given number.
Return your answer modulo 109+7*/

    public int nchoc(int A, ArrayList<Integer> B) {
        createMaxHeap(B);
        long cost = 0;
        long mod = (long) (1e9 + 7);
        while (A > 0) {
            long max = extractMax(B);
            cost = ((cost % mod) + (max % mod)) % mod;
            int t = (int) Math.floor(max / 2);
            insertKeyMax(B, t);
            A--;
        }

        return (int) cost;
    }

    private void insertKeyMax(ArrayList<Integer> a, int elem) {
        a.add(elem);
        int curr = a.size() - 1;
        int p = (int) Math.floor((curr - 1) / 2);
        while (curr != 0 && a.get(p) < a.get(curr)) {
            p = (int) Math.floor((curr - 1) / 2);
            swap(a, p, curr);
            curr = p;
            p = (int) Math.floor((curr - 1) / 2);
        }
    }

    private int extractMax(ArrayList<Integer> a) {
        int fmin = a.get(0);
        int size = a.size() - 1;
        swap(a, 0, size);
        a.remove(size);
        MaxHeapify(a, 0);
        return fmin;
    }
    /*Maximum array sum after B negations
Problem Description

Given an array of integers A and an integer B. You must modify the array exactly B number of times. In single modification, we can replace any one array element A[i] by -A[i].

You need to perform these modifications in such a way that after exactly B modifications, sum of the array must be maximum.*/

    public int MaxArrayBNegation(ArrayList<Integer> A, int B) {
        createMinHeap(A);
        int cost = 0;
        while (B > 0) {
            int min = extractMin(A);
            min = min * -1;
            insertKey(A, min);
            B--;
        }

        long sum = 0;
        for (int i = 0; i < A.size(); i++) {
            sum += A.get(i);
        }
        return (int) sum;

    }

    /*Product of 3
Problem Description

Given an integer array A of size N.

You have to find the product of the 3 largest integers in array A from range 1 to i, where i goes from 1 to N.

Return an array B where B[i] is the product of the largest 3 integers in range 1 to i in array A. If i < 3, then the integer at index i is -1.*/


    public ArrayList<Integer> Product3(ArrayList<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<>();

        int size = A.size();
        if (size < 3) {
            for (int i = 0; i < size; i++) {
                ans.add(-1);
                return ans;
            }
        }
        ans.add(-1);
        ans.add(-1);
        int prod = 1;
        int heap[] = new int[3];

        for (int i = 0; i < 3; i++) {
            prod = prod * A.get(i);
            heap[i] = A.get(i);
        }
        ans.add(prod);
        heap = buildHeapMin(heap);


        for (int i = 3; i < size; i++) {
            int t = heap[0];
            if (t < A.get(i)) {
                heap[0] = A.get(i);
                prod = (prod / t) * A.get(i);
                ans.add(prod);
                MinHeapifyArr(heap, 0);
            } else {
                ans.add(prod);
            }
        }
        return ans;

    }

    private int[] buildHeapMin(int[] heap) {
        int heap_size = heap.length;
        int i = (heap_size - 1) / 2;
        while (i >= 0) {
            MinHeapifyArr(heap, i);
            i--;
        }
        return heap;
    }

    private void swap(int heap[], int greatIdx, int i) {
        int t = heap[greatIdx];
        heap[greatIdx] = heap[i];
        heap[i] = t;
    }

    private void MinHeapifyArr(int[] heap, int i) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int smallest = i;
        int size = heap.length;
        if (l < size && heap[l] < heap[i])
            smallest = l;
        if (r < size && heap[r] < heap[smallest])
            smallest = r;

        if (smallest != i) {
            swap(heap, i, smallest);
            MinHeapifyArr(heap, smallest);
        }
    }
    /*Connect ropes
Problem Description

Given an array of integers A representing the length of ropes.

You need to connect these ropes into one rope. The cost of connecting two ropes is equal to the sum of their lengths.

Find and return the minimum cost to connect these ropes into one rope.*/

    public int ConnectRope(ArrayList<Integer> A) {
        createMinHeap(A);
        int cost = 0;
        while (A.size() > 1) {
            int fmin = extractMin(A);
            int smin = extractMin(A);
            cost += fmin + smin;
            insertKey(A, fmin + smin);
        }
        return cost;
    }


    private ListNode insertKeyListNode(ListNode ans, ListNode curr) {
        if (ans == null) {
            ans = new ListNode(curr.val);
            ans.next = null;
        } else {
            ans.next = curr;
            ans.next.next = null;
        }

        return ans;
    }

    private void insertKey(ArrayList<Integer> a, int elem) {
        a.add(elem);
        int curr = a.size() - 1;
        int p = (int) Math.floor((curr - 1) / 2);
        while (curr != 0 && a.get(p) > a.get(curr)) {
            p = (int) Math.floor((curr - 1) / 2);
            swap(a, p, curr);
            curr = p;
            p = (int) Math.floor((curr - 1) / 2);
        }
    }

    private int extractMin(ArrayList<Integer> a) {
        if (a.isEmpty())
            return -1;
        int fmin = a.get(0);
        int size = a.size() - 1;
        swap(a, 0, size);
        a.remove(size);
        MinHeapify(a, 0);
        return fmin;
    }

    private void MinHeapify(ArrayList<Integer> a, int i) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int smallest = i;
        if (l < a.size() && a.get(l) < a.get(i))
            smallest = l;
        if (r < a.size() && a.get(r) < a.get(smallest))
            smallest = r;
        if (smallest != i) {
            swap(a, i, smallest);
            MinHeapify(a, smallest);
        }
    }


    private void swap(ArrayList<Integer> a, int greatIdx, int i) {
        int t = a.get(greatIdx);
        a.set(greatIdx, a.get(i));
        a.set(i, t);
    }


    private void createMaxHeap(ArrayList<Integer> a) {
        int heap_size = a.size();
        int i = (heap_size - 1) / 2;
        while (i >= 0) {
            MaxHeapify(a, i);
            i--;
        }
    }

    private void MaxHeapify(ArrayList<Integer> a, int i) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int greatest = i;
        if (l < a.size() && a.get(l) > a.get(i))
            greatest = l;
        if (r < a.size() && a.get(r) > a.get(greatest))
            greatest = r;

        if (greatest != i) {
            swap(a, i, greatest);
            MaxHeapify(a, greatest);
        }
    }

    private void createMinHeap(ArrayList<Integer> a) {
        int heap_size = a.size();
        int i = (heap_size - 1) / 2;
        while (i >= 0) {
            MinHeapify(a, i);
            i--;
        }
    }

    public ListNode pushA(int new_data, ListNode head) {
        ListNode new_node = new ListNode(new_data);
        new_node.next = head;
        head = new_node;
        return new_node;
    }

    private ArrayList<ListNode> creatDblArrayList() {
        ArrayList<ListNode> a = new ArrayList<>();
        Integer[] arr1 = {1, 10, 20};
        ArrayList<Integer> levelTree = new ArrayList<>();
        Collections.addAll(levelTree, arr1);
        Integer[] arr2 = {4, 11, 13};
        ArrayList<Integer> levelTree2 = new ArrayList<>();
        Collections.addAll(levelTree2, arr2);

        Integer[] arr3 = {3, 8};
        ArrayList<Integer> levelTree3 = new ArrayList<>();
        Collections.addAll(levelTree3, arr3);
        ArrayList<ArrayList<Integer>> dbtlres = new ArrayList<ArrayList<Integer>>();
        dbtlres.add(levelTree);
        dbtlres.add(levelTree2);
        dbtlres.add(levelTree3);

        ListNode ptr = null;
        for (int i = 0; i < 3; i++) {

            ptr = null;

            ArrayList<Integer> curr = dbtlres.get(i);
            for (int j = curr.size() - 1; j >= 0; j--) {
                ptr = pushA(curr.get(j), ptr);
            }
            a.add(ptr);
        }

        return a;
    }

    private static ArrayList<ArrayList<Integer>> creatDbtArr1() {
        ArrayList<ArrayList<Integer>> dblArr = new ArrayList<>();

        Integer arr[][] = new Integer[][]
                {{9, 9, 11},
                        {9, 11, 13},
                        {10, 12, 15},
                        {13, 14, 16},
                        {16, 20, 21}
                };

        for (int i = 0; i < arr.length; i++) {

            ArrayList<Integer> al1 = new ArrayList<>();
            for (int j = 0; j < arr[i].length; j++) {
                al1.add(arr[i][j]);

            }
            dblArr.add(al1);

        }
        return dblArr;
    }

}
