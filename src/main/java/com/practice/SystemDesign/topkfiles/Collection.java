package com.practice.SystemDesign.topkfiles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Collection {

    private String collectionName;
    private List<File> files = new ArrayList<>();
    private int size = 0;

    public void addFile(File file){
        this.files.add(file);
    }
}
