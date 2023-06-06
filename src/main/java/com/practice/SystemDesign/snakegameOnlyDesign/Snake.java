package com.practice.SystemDesign.snakegameOnlyDesign;

import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class Snake {
    private final Deque<int[]> body;

    public Snake() {
        body = new LinkedList<>();
        body.add(new int[]{0, 0});
    }

    public void move(int[] newPosition) {
        body.addFirst(newPosition);
        body.removeLast();
    }

    public void grow() {
        int[] head = getHead();
        body.addFirst(head);
    }

    public int getLength() {
        return body.size();
    }

    public int[] getHead() {
        return body.getFirst();
    }

    public boolean isOutOfBounds(int width, int height) {
        int[] head = getHead();
        int x = head[0];
        int y = head[1];
        return x < 0 || x >= height || y < 0 || y >= width;
    }

    public boolean isCollisionWithBody() {
        int[] head = getHead();
        Iterator<int[]> iterator = body.iterator();
        iterator.next(); // Skip the head
        while (iterator.hasNext()) {
            int[] segment = iterator.next();
            if (Arrays.equals(head, segment)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOccupyingPosition(int x, int y) {
        for (int[] segment : body) {
            if (segment[0] == x && segment[1] == y) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        body.clear();
        body.add(new int[]{0, 0});
    }
}

