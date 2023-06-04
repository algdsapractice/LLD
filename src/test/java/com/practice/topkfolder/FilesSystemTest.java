package com.practice.topkfolder;

import com.practice.SystemDesign.topkfolder.Collection;
import com.practice.SystemDesign.topkfolder.FilesSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FilesSystemTest {

    private FilesSystem filesSystem;


    @BeforeEach
    void setUp() {

        filesSystem = new FilesSystem();
        filesSystem.addFiles("file1.txt", 100, "");
        filesSystem.addFiles("file2.txt", 200, "collection1");
        filesSystem.addFiles("file3.txt", 200, "collection1");
        filesSystem.addFiles("file4.txt", 300, "collection2");
        filesSystem.addFiles("file5.txt", 100, "");
    }


    @Test
    public void testTotalSize() {
        int totalSize = filesSystem.getTotalSize();
        Assertions.assertEquals(900, totalSize, "Total size did not match");
    }


    @Test
    public void testTopNCollections() {

        List<Collection> collections = filesSystem.getTopNcollections(2);
        Collection col1 = collections.get(0);
        Assertions.assertEquals("collection1", col1.getCollectionName(), "Different collection returned");
        Assertions.assertEquals(400, col1.getSize(), "Different collection returned");
    }

}