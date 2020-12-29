package com.piyush.psds.google.design;

import java.util.HashMap;
import java.util.Map;

class DoublyLinkedListNode<K, V> {

    K key;
    V value;
    DoublyLinkedListNode<K, V> next;
    DoublyLinkedListNode<K, V> prev;

    public DoublyLinkedListNode(K k, V v) {
        this.key = k;
        this.value = v;
    }
}

class DoublyLinkedList<K, V> {
    DoublyLinkedListNode<K, V> head;
    DoublyLinkedListNode<K, V> tail;
    int size = 0;

    public DoublyLinkedList() {
        head = new DoublyLinkedListNode<>(null, null);
        tail = new DoublyLinkedListNode<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public DoublyLinkedListNode<K, V> addFirst(K key, V value) {
        DoublyLinkedListNode<K, V> node = new DoublyLinkedListNode<>(key, value);
        addFirst(node);
        return node;
    }

    public void addFirst(DoublyLinkedListNode<K, V> node) {
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
        size++;
    }

    public void delete(DoublyLinkedListNode<K, V> node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        size--;
    }

    public DoublyLinkedListNode<K, V> deleteLast() {
        DoublyLinkedListNode<K, V> node = tail.prev;
        delete(node);
        return node;
    }
}

public class LRUCache {

    public static void main(String[] args) {
        LRUCache l = new LRUCache(2);
        l.put(1,1);
        l.put(2,2);
        System.out.println(l.get(1));
    }

    DoublyLinkedList<Integer, Integer> list;
    Map<Integer, DoublyLinkedListNode<Integer, Integer>> map;
    int capacity;

    public LRUCache(int capacity) {
        list = new DoublyLinkedList<>();
        map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if(map.containsKey(key)) {
            DoublyLinkedListNode<Integer, Integer> node = map.get(key);
            list.delete(node);
            list.addFirst(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        DoublyLinkedListNode<Integer, Integer> node;
        if(map.containsKey(key)) {
            node = map.get(key);
            node.value = value;
            list.delete(node);
            list.addFirst(node);
        } else {
            node = list.addFirst(key, value);
            map.put(key, node);
        }
        if(list.size > capacity) {
            DoublyLinkedListNode<Integer, Integer> rn = list.deleteLast();
            map.remove(rn.key);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
