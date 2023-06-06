package com.practice.SystemDesign.topkfiles.secondApproach;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class FileProcessor {

    // Calculate total size of files processed
    public static int calculateTotalSize(List<Object[]> files) {
        int totalSize = 0;
        for (Object[] file : files) {
            totalSize += (int) file[1];
        }
        return totalSize;
    }

    // Calculate Top K collections based on size
    public static List<String[]> calculateTopKCollections(List<Object[]> files, int k) {
        Map<String, Integer> collectionSizes = new HashMap<>();
        for (Object[] file : files) {
            if (file.length > 2) {
                List<String> collections = (List<String>) file[2];
                for (String collection : collections) {
                    int size = (int) file[1];
                    collectionSizes.put(collection, collectionSizes.getOrDefault(collection, 0) + size);
                }
            }
        }
        List<String[]> topKCollections = new ArrayList<>();
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        queue.addAll(collectionSizes.entrySet());
        int i = 0;
        while (i < k && !queue.isEmpty()) {
            Map.Entry<String, Integer> entry = queue.poll();
            topKCollections.add(new String[] {entry.getKey(), Integer.toString(entry.getValue())});
            i++;
        }
        return topKCollections;
    }



    public  List<String[]> concurrentCalculateTopKCollections(List<Object[]> files, int k) {
        Map<String, Integer> collectionSizes = new ConcurrentHashMap<>();
        for (Object[] file : files) {
            if (file.length > 2) {
                List<String> collections = (List<String>) file[2];
                int size = (int) file[1];
                for (String collection : collections) {
                    collectionSizes.compute(collection, (key, value) -> (value == null) ? size : value + size);
                }
            }
        }

        List<String[]> topKCollections = new ArrayList<>();
        PriorityBlockingQueue<Map.Entry<String, Integer>> queue = new PriorityBlockingQueue<>(k, (a, b) -> b.getValue() - a.getValue());
        queue.addAll(collectionSizes.entrySet());

        int i = 0;
        while (i < k && !queue.isEmpty()) {
            Map.Entry<String, Integer> entry = queue.poll();
            topKCollections.add(new String[] { entry.getKey(), Integer.toString(entry.getValue()) });
            i++;
        }

        return topKCollections;
    }


    public static void main(String[] args) {
        List<Object[]> files = new ArrayList<>();
        files.add(new Object[] {"file1.txt", 100});
        files.add(new Object[] {"file2.txt", 200, Arrays.asList("collection1")});
        files.add(new Object[] {"file3.txt", 200, Arrays.asList("collection1")});
        files.add(new Object[] {"file4.txt", 300, Arrays.asList("collection2")});
        files.add(new Object[] {"file5.txt", 100});

        int totalSize = calculateTotalSize(files);
        System.out.println("Total size of files processed: " + totalSize);

        List<String[]> topKCollections = calculateTopKCollections(files, 2);
        System.out.println("Top " + topKCollections.size() + " collections:");
        for (String[] collection : topKCollections) {
            System.out.println(collection[0] + " : " + collection[1]);
        }

        System.out.println("Concurrent calls ");
        List<String[]> topKConCollections = calculateTopKCollections(files, 2);
        for (String[] collection : topKConCollections) {
            System.out.println(collection[0] + " : " + collection[1]);
        }


    }
}