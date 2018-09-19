package com.victor.Tree;

import java.util.*;

public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {

        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if(e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        // if e equeal to node.e can reach here
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    public void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.e);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void preOrderNorecursion() {
        Stack<Node> stack = new Stack<>();

        if (root == null) {
            System.out.println("null");
            return;
        }

        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }

    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.e);
            inOrder(node.right);
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.e);
        }
    }

    public void levelOrder() {

        Queue<Node> q = new LinkedList<>();

        if (root == null) {
            System.out.println("null");
            return;
        }

        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.e);

            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
    }

    public Node minimum() {
        if (root == null) throw new IllegalArgumentException("BST has no element. (i.e. BST is null.)");

        Node ret = root;
        while (ret.left != null) {
            ret = ret.left;
        }

        return ret;
    }

    public E removeMin() {
        return removeMin(root);
    }

    // delete the minimum in the BST
    // return the value of the minimum
    private E removeMin(Node node) {
        if (node == null) throw new IllegalArgumentException("BST has no element. (i.e. BST is null.)");
        if (node.left == null) {
            E minValue = node.e;
            node = node.right;
            size--;
            return minValue;
        }

        Node minNode = node;
        Node prev = null;

        while (minNode.left != null) {
            prev = minNode;
            minNode = minNode.left;
        }

        // minNode is the minimum, so it has not left
        if (minNode.right == null) {
            prev.left = null;
        } else {
            prev.left = minNode.right;
        }
        size--;

        return minNode.e;
    }


    public Node maximum() {
        if (root == null) throw new IllegalArgumentException("BST has no element. (i.e. BST is null.)");

        Node ret = root;
        while (ret.right != null) {
            ret = ret.right;
        }

        return ret;
    }

    public E removeMax() {
        if (root == null) throw new IllegalArgumentException("BST has no element. (i.e. BST is null.)");
        if (root.right == null) {
            E ret = root.e;
            root = root.left;
            size--;
            return ret;
        }

        Node maxNode = root;
        Node prev = null;
        while (maxNode.right != null) {
            prev = maxNode;
            maxNode = maxNode.right;
        }

        // maxNode has not right Node
        if (maxNode.left == null) {
            prev.right = null;
        } else {
            prev.right = maxNode.left;
        }
        size--;
        return maxNode.e;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     *
     *                0
     *               1  4
     *             2  5   3
     */

    // delete the node(.e == e)
    // return the rest of BST that has been delete the node
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {  // e < node.e (at the left)
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {   // node.e < e (at the right)
            node.right = remove(node.right, e);
            return node;
        }else { // e == node.e

            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // both left and right
            E sValue = removeMin(node.right);
            node.e = sValue;   // size--
            return node;
        }

    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");

        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }



    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num: nums) {
            bst.add(num);
        }

        bst.preOrder();
        System.out.println();
        bst.preOrderNorecursion();
        System.out.println();

        bst.inOrder();
        System.out.println("------");

        bst.postOrder();
        System.out.println("------");

        bst.levelOrder();
        System.out.println("------");

        System.out.println(bst.minimum().e);
        System.out.println(bst.maximum().e);

        System.out.println("------------");

        int n = 1000;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(10000));
        }

        ArrayList<Integer> nums2 = new ArrayList<>();
        while(!bst.isEmpty()) {
            nums2.add(bst.removeMin());
        }
        System.out.println(nums2);
        for (int i = 1; i < nums2.size(); i++) {
            if (nums2.get(i-1) > nums2.get(i))
                throw new IllegalArgumentException("Error");
        }
        System.out.println("removeMin test completed.");

        System.out.println("------------");

        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(10000));
        }
        ArrayList<Integer> nums3 = new ArrayList<>();
        while(!bst.isEmpty()) {
            nums3.add(bst.removeMax());
        }
        System.out.println(nums3);
        for (int i = 1; i < nums3.size(); i++) {
            if (nums3.get(i-1) < nums3.get(i))
                throw new IllegalArgumentException("Error" + nums3.get(i));
        }
        System.out.println("removeMan test completed.");

        System.out.println("------------");

        BST<Integer> bst2 = new BST<>();
        int[] nums4 = {5, 3, 6, 8, 4, 2};
        for (int num: nums4) {
            bst2.add(num);
        }
//        bst2.remove(2);
//        bst2.remove(8);
        bst2.remove(6);
        bst2.inOrder();
        System.out.println();
        System.out.println(bst2);

    }
}
