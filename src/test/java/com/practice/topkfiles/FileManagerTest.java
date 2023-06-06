package com.practice.topkfiles;

import com.practice.SystemDesign.topkfiles.firstApproach.Collection;
import com.practice.SystemDesign.topkfiles.firstApproach.FileManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class FileManagerTest {

    private FileManager fileManager;

    @BeforeEach
    void setUp()
    {
        fileManager = new FileManager();
        fileManager.processFile("file1.txt", 100, "");
        fileManager.processFile("file2.txt", 200, "collection1");
        fileManager.processFile("file3.txt", 200, "collection1");
        fileManager.processFile("file4.txt", 300, "collection2");
        fileManager.processFile("file5.txt", 100, "");
    }


    @Test
    public void testTotalSize() {
        int totalSize = fileManager.getTotalSize();
        Assertions.assertEquals(900, totalSize, "Total size did not match");
    }


    @Test
    public void testTopNCollections() {

        List<Collection> collections = fileManager.getTopCollections(2);
        Collection col1 = collections.get(0);
        Assertions.assertEquals("collection1", col1.getCollectionName(), "Different collection returned");
        Assertions.assertEquals(400, col1.getSize(), "Different collection returned");

        Collection col2 = collections.get(1);
        Assertions.assertEquals("collection2", col2.getCollectionName(), "Different collection returned");
        Assertions.assertEquals(300, col2.getSize(), "Different collection returned");
    }
}