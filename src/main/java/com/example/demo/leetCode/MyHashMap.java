package com.example.demo.leetCode;

class MyHashMap {
    private static final int SIZE = 1000;
    private Node[] buckets;

    public MyHashMap() {
        buckets = new Node[SIZE];
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(1, 1);
        map.put(1001, 2);
        map.put(2001, 3);
        map.put(2, 3);

        map.remove(2);
        map.remove(2001);


        System.out.println(map.get(2));
        System.out.println(map.get(2001));


    }

    private int hash(int key) {
        return key % SIZE;
    }

    public void put(int key, int value) {
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new Node(key, value);
        } else {
            Node prev = findNode(buckets[index], key);
            if (prev.next == null) {
                prev.next = new Node(key, value);
            } else {
                prev.next.value = value;
            }
        }
    }

    public int get(int key) {
        int index = hash(key);
        if (buckets[index] == null) return -1;
        Node prev = findNode(buckets[index], key);
        return prev.next == null ? -1 : prev.next.value;
    }

    public void remove(int key) {
        int index = hash(key);
        if (buckets[index] == null) return;

        Node prev = findNode(buckets[index], key);
        if (prev.next != null) {
            if (prev.key == -1 && prev.value == -1 && prev.next.next == null) {
                buckets[index] = null;
                return;
            }
            prev.next = prev.next.next;
        }
    }

    private Node findNode(Node head, int key) {
        Node dummy = new Node(-1, -1); //create Node

        dummy.next = head;
        Node prev = dummy;
        while (prev.next != null && prev.next.key != key) {
            prev = prev.next;
        }
        return prev;
    }

    private static class Node {
        int key, value;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
