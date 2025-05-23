package com.example.demo.leetCode;

public class MyHashSet {

    public final int size = 1000;
    Node[] buckets;


    public MyHashSet() {
        buckets = new Node[size];
    }

    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();
        set.add(2);
        set.add(1002);
        set.add(2002);
        set.remove(2);
        System.out.println(set.contains(2));
        System.out.println(set.contains(1002));
        System.out.println(set.contains(2002));
    }


    public int hash(int key) {
        return key % size;
    }

    public void add(int key) {

        int index = hash(key);
        Node newNode = new Node(key);
        if (buckets[index] == null) {
            buckets[index] = newNode;
        } else {
            Node prevNode = findNode(key);
            if (prevNode == null) return;
            prevNode.next = newNode;
        }

    }

    public void remove(int key) {
        Node prev = findNode(key);
        if (prev.next != null) {
            if (prev.keyAndValue == -1 && prev.next.next == null) {
                buckets[hash(key)] = null;
                return;
            }
            prev.next = prev.next.next;
        }

    }


    public boolean contains(int key) {
        Node node = findNode(key);
        if (node.next != null && node.next.keyAndValue == key) {
            return true;
        }
        return false;


    }

    private Node findNode(int key) {//give prev node
        int index = hash(key);

        Node prev = new Node(-1);//1 2 2002
        prev.next = buckets[index];

        while (prev.next != null && prev.next.keyAndValue != key) {
            prev = prev.next;
        }
        if (prev.keyAndValue == key) {
            return null;
        }

        return prev;
    }

    private class Node {
        public Node next;
        int keyAndValue;

        public Node(int keyAndValue) {
            this.keyAndValue = keyAndValue;
        }

    }
}