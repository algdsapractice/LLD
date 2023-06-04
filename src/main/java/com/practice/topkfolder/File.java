package com.practice.topkfolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private Collection collection;
    private int size;
    private String filename;
}
