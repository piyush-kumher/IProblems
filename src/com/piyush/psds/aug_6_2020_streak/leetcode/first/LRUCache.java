package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.HashMap;
import java.util.Map;

class DoubleLinkedListNode<T, R> {

    public T key;
    public R value;
    public DoubleLinkedListNode<T, R> prev;
    public DoubleLinkedListNode<T, R> next;

    public DoubleLinkedListNode(T key, R value) {
        this.key = key;
        this.value = value;
    }
}

class DoubleLinkedList<T, R> {

    DoubleLinkedListNode<T, R> head;
    DoubleLinkedListNode<T, R> tail;
    int size = 0;

    public DoubleLinkedList() {
        head = new DoubleLinkedListNode<>(null, null);
        tail = new DoubleLinkedListNode<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public DoubleLinkedListNode<T, R> addFirst(T key, R value) {
        DoubleLinkedListNode<T, R> node = new DoubleLinkedListNode<>(key, value);
        addFirst(node);
        return node;
    }

    private void addFirst(DoubleLinkedListNode<T, R> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        size++;
    }

    public DoubleLinkedListNode<T, R> addTail(T key, R value) {
        DoubleLinkedListNode<T, R> node = new DoubleLinkedListNode<>(key, value);
        addTail(node);
        return node;
    }

    private void addTail(DoubleLinkedListNode<T, R> node) {
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
        size++;
    }

    private void removeNode(DoubleLinkedListNode<T, R> node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        size--;
    }

    public DoubleLinkedListNode<T, R> removeFirst() {
        if (head.next != null) {
            final DoubleLinkedListNode<T, R> deleting = head.next;
            removeNode(deleting);
            return deleting;
        }
        return null;
    }

    public DoubleLinkedListNode<T, R> removeTail() {
        if (tail.prev != null) {
            final DoubleLinkedListNode<T, R> deleting = tail.prev;
            removeNode(deleting);
            return deleting;
        }
        return null;
    }

    public void moveToHead(DoubleLinkedListNode<T, R> node) {
        removeNode(node);
        addFirst(node);
    }
}

public class LRUCache {

    int capacity;
    Map<Integer, DoubleLinkedListNode<Integer, Integer>> map;
    DoubleLinkedList<Integer, Integer> doubleLinkedList;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        doubleLinkedList = new DoubleLinkedList<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        final DoubleLinkedListNode<Integer, Integer> node = map.get(key);
        doubleLinkedList.moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DoubleLinkedListNode<Integer, Integer> node = map.get(key);
        if (node == null) {
            node = doubleLinkedList.addFirst(key, value);
            map.put(key, node);
            if (doubleLinkedList.size > capacity) {
                final DoubleLinkedListNode<Integer, Integer> removedElement = doubleLinkedList.removeTail();
                map.remove(removedElement.key);
            }
        } else {
            node.value = value;
            doubleLinkedList.moveToHead(node);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
    }

}
