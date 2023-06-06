package com.practice.SystemDesign.rank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VoteAlgorithmTest {


    VoteAlgorithm voteAlgorithm;
    List<List<String>> bollots;

    @BeforeEach
    void setUp() {
        voteAlgorithm= new VoteAlgorithm();
        bollots = new ArrayList<>();
        bollots.add(Arrays.asList("A","B","C"));
        bollots.add(Arrays.asList("A","C","D"));
        bollots.add(Arrays.asList("D","A","C"));


    }

    @Test
    void getCandidatesInSortedOrder() {

       //List<String> sorted= voteAlgorithm.getCandidatesInSortedOrder(bollots);
        List<String> sorted= voteAlgorithm.calculateVotes(bollots);

        Assertions.assertEquals(Arrays.asList("A","D","C","B"),sorted);
    }
}