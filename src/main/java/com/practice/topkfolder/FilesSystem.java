package com.practice.topkfolder;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilesSystem {

    Map<String,Collection> map;
    int totalSize = 0;

    public FilesSystem() {
        this.map = new HashMap<>();
    }

    public void addFiles(String file, int size, String name) {

        if(null!=name && name.isEmpty()){

        }

    }


    public int getTotalSize() {
            return this.totalSize;
    }

    public List<Collection> getTopNcollections(int k) {
        return null;

    }
}
