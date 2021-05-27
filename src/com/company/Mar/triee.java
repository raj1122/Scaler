package com.company.Mar;

import java.util.*;

public class triee {


    public void executeF() {

        Integer[] arr1 = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};
        ArrayList<Integer> al1 = new ArrayList<>();
        Collections.addAll(al1, arr1);

        String s1[] = {"data", "circle", "cricket"};
        String s2[] = {"s", "ss", "sss", "ssss", "sssss", "s", "ss", "sss", "ssss", "sssss", "ssssss"};

        // "date","circel","crikket","data",
        ArrayList<String> strAl = new ArrayList<>();
        ArrayList<String> strAl2 = new ArrayList<>();
        Collections.addAll(strAl, s1);
        Collections.addAll(strAl2, s2);
//        prefix(strAl);
//        PrefixSuffix(strAl,strAl2);
//        ModifiedSearch(strAl,strAl2);
//        SpellinChecker(strAl,strAl2);
        ContactFinder(al1, strAl2);

    }

    static TrieNode root;

    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        int freq;  // To store frequency

        TrieNode() {
            freq = 0;
            for (int i = 0; i < 26; i++)
                child[i] = null;
        }
    }


    static TrieNodeSpell rootSpell;

    static class TrieNodeSpell {
        TrieNodeSpell[] child = new TrieNodeSpell[26];
        boolean end;

        TrieNodeSpell() {
            end = false;
            for (int i = 0; i < 26; i++)
                child[i] = null;
        }
    }


    public void insertSpell(String str) {
        // Length of the URL
        int len = str.length();
        TrieNodeSpell pCrawl = rootSpell;

        for (int level = 0; level < len; level++) {

            int index = str.charAt(level) - 'a';

            if (pCrawl.child[index] == null)
                pCrawl.child[index] = new TrieNodeSpell();

            // Move to the child
            pCrawl = pCrawl.child[index];
        }
        pCrawl.end = true;
    }

    public void insert(String str) {
        // Length of the URL
        int len = str.length();
        TrieNode pCrawl = root;


        for (int level = 0; level < len; level++) {

            int index = str.charAt(level) - 'a';

            if (pCrawl.child[index] == null) {
                pCrawl.child[index] = new TrieNode();
            }

            // Move to the child
            pCrawl = pCrawl.child[index];
            pCrawl.freq += 1;
        }
//        pCrawl.freq+=1;
    }


    /*Contact Finder
Problem Description

We want to make a custom contacts finder applications as part of our college project. The application must perform two types of operations:

Type 1: Add name B[i] ,denoted by 0 in vector A where B[i] is a string in vector B denoting a contact name. This must store B[i] as a new contact in the application.

Type 2: Find partial for B[i] ,denoted by 1 in vector A where B[i] is a string in vector B denoting a partial name to search the application for. It must count the number of contacts starting with B[i].

You have been given sequential add and find operations. You need to perform each operation in order.

And return as an array of integers, answers for each query of type 2(denoted by 1 in A) .*/
    public ArrayList<Integer> ContactFinder(ArrayList<Integer> A, ArrayList<String> B) {

        root = new TrieNode();
        root.freq = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) == 0) {
                insert(B.get(i));
            } else if (A.get(i) == 1) {
                int t = searchContact(B.get(i));
                ans.add(t);

            }
        }

        return ans;

    }


    public int searchContact(String str) {
        TrieNode tempNode = root;
        TrieNode prev = null;
        long ans = 0;
        int index = 0;
        for (index = 0; index < str.length(); index++) {
            int tempIndex = str.charAt(index) - 'a';
            if (tempNode.child[tempIndex] == null) {
                return (int) ans;
            }
            prev = tempNode;
            tempNode = tempNode.child[tempIndex];
        }
        if (tempNode != null)
            ans += tempNode.freq;

        return (int) ans;
    }

    /*Modified Search
Problem Description

Given two arrays of strings A of size N and B of size M.

Return a binary string C where C[i] = '1' if B[i] can be found in dictionary A using exactly one modification in B[i], Else C[i] = '0'.

NOTE: modification is defined as converting a character into another character.*/

    public String ModifiedSearch(ArrayList<String> A, ArrayList<String> B) {
        root = new TrieNode();
        root.freq = 0;
        StringBuilder ans = new StringBuilder("");
        HashSet<String> hs = new HashSet<>();
        for (int i = 0; i < A.size(); i++) {
            hs.add(A.get(i));
            insert(A.get(i));
        }

        for (int i = 0; i < B.size(); i++) {

            StringBuilder sb = new StringBuilder(B.get(i));
            boolean isExist = false;
            for (int j = 0; j < sb.length(); j++) {


                for (int k = 0; k < 26; k++) {
                    char ch = (char) (97 + k);
                    char ch1 = sb.charAt(j);
                    if (ch == ch1)
                        continue;
                    sb.setCharAt(j, ch);
                    TrieNode tempNode = root;
                    int cnt = search(sb.toString());
                    if (cnt > 0) {
                        isExist = true;
                        break;
                    }
                    sb.setCharAt(j, ch1);

                }

                if (isExist == true)
                    break;
            }
            if (isExist == true)
                ans.append("1");
            else
                ans.append("0");

        }

        return ans.toString();

    }

    private boolean searchWord(TrieNode tempNode, String str, int currIdx, int Mod) {

        int ans = 0;
        int temp = str.charAt(currIdx) - 'a';
        //if char is found iterate recursively
        if (tempNode.child[temp] != null) {
            if (searchWord(tempNode.child[temp], str, currIdx + 1, Mod)) {
                return true;
            }

            if (str.length() - 1 == currIdx && Mod == 0)
                return false;
        }

        //If word not found and mod is 1,then return false
        if (Mod == 1)
            return false;
        for (int index = 0; index < 26; index++) {
            if (tempNode.child[index] != null) {
                //iterative serach for all char using backtracking
                if (searchWord(tempNode.child[index], str, currIdx + 1, Mod + 1)) {
                    return true;
                }

            }
        }
        return false;
    }
    /*Prefix and Suffix
Problem Description

Given a list of N words denoted by string array A of size N.

You have to answer Q queries denoted by string array B, for each query you have a string S of size M, find the number of words in the list A which have string S as a prefix and suffix.

NOTE: The size M for all strings in the queries remains same.*/

    public ArrayList<Integer> PrefixSuffix(ArrayList<String> A, ArrayList<String> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        root = new TrieNode();
        root.freq = 0;
        int prefixSize = B.get(0).length();
        for (int i = 0; i < A.size(); i++) {
            if (isPrefixSuffix(A.get(i), prefixSize)) {

                insert(A.get(i));
            }
        }

        for (int i = 0; i < B.size(); i++) {
            int t = search(B.get(i));
            ans.add(t);
        }

        return ans;

    }

    public int search(String str) {
        TrieNode tempNode = root;
        int ans = 0;
        int index = 0;
        for (index = 0; index < str.length(); index++) {
            int tempIndex = str.charAt(index) - 'a';
            if (tempNode.child[tempIndex] == null) {
                return ans;
            }
            tempNode = tempNode.child[tempIndex];
        }
        if (tempNode != null) {

            if (index == str.length())
                return 1;

            ans += tempNode.freq;
        }
        return ans;
    }

    public boolean isPrefixSuffix(String str, int n) {
        if (str.length() < n) {
            return false;
        } else {
            int lptr = 0, rptr = str.length() - n;
            if (rptr < 0) return false;
            while (rptr < str.length()) {
                if (str.charAt(lptr) != str.charAt(rptr))
                    return false;
                rptr++;
                lptr++;
            }
        }
        return true;

    }


    /*Spelling Checker
Problem Description

Given an array of words A (dictionary) and another array B (which contain some words).

You have to return the binary array (of length |B|) as the answer where 1 denotes that the word is present in the dictionary and 0 denotes it is not present.

Formally, for each word in B, you need to return 1 if it is present in Dictionary and 0 if it is not.

Such problems can be seen in real life when we work on any online editor (like Google Documnet), if the word is not valid it is underlined by a red line.

NOTE: Try to do this in O(n) time complexity.*/

    public ArrayList<Integer> SpellinChecker(ArrayList<String> A, ArrayList<String> B) {
        ArrayList<Integer> res = new ArrayList<>();
        rootSpell = new TrieNodeSpell();

        for (int i = 0; i < A.size(); i++) {
            insertSpell(A.get(i));
        }

        checkPresent(res, A, B);
        return res;
    }

    private void checkPresent(ArrayList<Integer> res, ArrayList<String> a, ArrayList<String> b) {


        for (int i = 0; i < b.size(); i++) {
            TrieNodeSpell ptr = rootSpell;
            String s = b.get(i);
            for (int j = 0; j < s.length(); j++) {
                int t = s.charAt(j) - 'a';
                if (ptr.child[t] == null) {
//                    res.add(0);
                    break;
                }
                ptr = ptr.child[t];
            }

            if (ptr.end == true)
                res.add(1);
            else
                res.add(0);

        }
    }

    /*Shortest Unique Prefix
Problem Description

Given a list of N words. Find shortest unique prefix to represent each word in the list.

NOTE: Assume that no word is prefix of another. In other words, the representation is always possible



*/
    public ArrayList<String> prefix(ArrayList<String> A) {
        root = new TrieNode();
        root.freq = 0;
        for (int i = 0; i < A.size(); i++) {
            insert(A.get(i));
        }

        ArrayList<String> ans = new ArrayList<>();


        for (int i = 0; i < A.size(); i++) {
            int idx = findPrefixUtil(root, A.get(i), 0);
            String s = A.get(i).substring(0, idx + 1);
            ans.add(s);
        }
        return ans;
    }

    public int findPrefixUtil(TrieNode root, String s, int idx) {
        int t = s.charAt(idx) - 'a';
        if (root.child[t].freq == 1) return idx;
        if (root.child[t] != null) {
            return findPrefixUtil(root.child[t], s, idx + 1);
        }
        return -1;
    }
}
