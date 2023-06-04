package com.practice.SystemDesign.topkfolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection {
    private String collectionName;
    private int size;
    private List<File> files;
}
