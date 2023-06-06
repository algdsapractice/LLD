package com.practice.SystemDesign.topkfiles.firstApproach;

import java.util.*;
import java.util.stream.Collectors;

public class FileManager {

    private Map<String, Collection> collections;
    private int totalSize;

    public FileManager() {
        this.collections = new HashMap<>();
        this.totalSize = 0;
    }

    public void processFile(String fileName, int fileSize, String collectionName) {
        populateCollections(fileName, fileSize, collectionName);
        totalSize += fileSize;
    }

    private void populateCollections(String fileName, int fileSize, String collectionName) {
        File file = new File(fileName, fileSize);
        Collection collection= collections.getOrDefault(collectionName,new Collection());
        collection.setCollectionName(collectionName);
        collection.addFile(file);
        collection.setSize(collection.getSize()+fileSize);
        collections.put(collectionName, collection);
    }

    public int getTotalSize() {
        return totalSize;
    }


    public List<Collection> getTopCollections(int k) {
        return collections.values()
                .stream()
                .filter(collection -> collection.getCollectionName() != null)
                .sorted(Comparator.comparingInt(this::getCollectionSize).reversed())
                .limit(k)
                .collect(Collectors.toList());
    }




    public int getCollectionSize(Collection collection) {
        return collection.getSize();
    }



    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        fileManager.processFile("file1.txt", 1000, null);
        fileManager.processFile("file2.txt", 200, "collection1");
        fileManager.processFile("file3.txt", 200, "collection1");
        fileManager.processFile("file4.txt", 300, "collection2");
        fileManager.processFile("file5.txt", 100, null);

        System.out.println("Total size of files processed: " + fileManager.getTotalSize());

        int k = 2;
        List<Collection> topCollections = fileManager.getTopCollections(k);
        System.out.println("Top " + k + " collections:");
        for (Collection collection : topCollections) {
            System.out.println("- " + collection.getCollectionName()+ " : " + fileManager.getCollectionSize(collection));
        }
    }
}