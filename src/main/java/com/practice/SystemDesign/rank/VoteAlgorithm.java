package com.practice.SystemDesign.rank;
/*
Design a ranked-choice voting system. A single person votes by handing in an ordered array of names like : ["alice", "bob", "charlie", "dan"...] Part 1 Candidates are given 3 points for 1st place, 2 points for 2nd and 1 point for 3rd place. Given an array of arrays of names, return the candidates in order of most points to least. Part 2 In the event of a tie, return the candidate who reached the winning points first

READ BEFORE ANSWERING:

The Code should be complete without any errors and should execute.

Incomplete code or code with erros and logical problem will get a downvote.

First vote have highest weight and subsequent votes have weight-1 then previous vote

Input : [[A, B, C

A, C, D

D,A,C]]

Output : [A, D , C , B]

Consider the following:

1] Use the Design principles and design patterns in the code

2] Write Executable code with Junit Tests and Console Tests.

3] How can we use this code in concurrent environment?
 */

import java.util.*;
import java.util.stream.Collectors;


public class VoteAlgorithm {


    public static List<String> calculateVotes(List<List<String>> ballots) { // calculateVotes method
        Map<String, Integer> map = new HashMap<>(); // create a map of strings and integers
        // sort after each step and add to the map
        for (List<String> ballot : ballots) {   // for each list of strings in the list of lists
            for (int i = 0; i < ballot.size(); i++) {   // for each string in the list of strings
                String candidate = ballot.get(i);   // get the string
                map.put(candidate, map.getOrDefault(candidate, 0) + ballot.size() - i); // add the string to the map
                // sort the map by value
                map = sortByValue(map);
            }
        }
        return new ArrayList<>(map.keySet());   // return the map as a list of strings
    }

    public static Map<String, Integer> sortByValue(Map<String, Integer> map) {  // sort the map by value
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());   // create a list of map entries
        Collections.sort(list, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));   // sort the list
        Map<String, Integer> result = new LinkedHashMap<>();    // create a map of strings and integers
        for (Map.Entry<String, Integer> entry : list) { // for each map entry in the list
            result.put(entry.getKey(), entry.getValue());   // add the map entry to the map
        }
        return result;
    }

    public static void main(String[] args) {    // main method
        List<List<String>> ballots = new ArrayList<>();   // create a list of lists of strings
        ballots.add(Arrays.asList("A", "B", "C"));  // add a list of strings to the list of lists
        ballots.add(Arrays.asList("A", "C", "D"));  // add a list of strings to the list of lists
        ballots.add(Arrays.asList("D", "A", "C"));  // add a list of strings to the list of lists
        List<String> result = calculateVotes(ballots);  // call the calculateVotes method
        System.out.println("Result: " + result);    // print the result
    }
}

//public class VoteAlgorithm {
//
//    public List<String> getCandidatesInSortedOrder(List<List<String>> ballots){
//
//        Map<String,Integer> map = new HashMap<>();
//
//        for (List<String> ballot: ballots) {
//
//            for(int i=0; i<ballot.size(); i++){
//                String candidate=ballot.get(i);
//                map.put(candidate,map.getOrDefault(candidate,0)+ballot.size()-i);
//            }
//        }
//
//       return map.entrySet().stream()
//               .sorted((o1, o2) -> (o2.getValue()).compareTo(o1.getValue())).map(m->m.getKey())
//               .collect(Collectors.toList());
//
//    }
//}

//
//import java.util.*;
//import java.util.stream.Collectors;
//
//import java.util.*;
//import java.util.stream.Collectors;

//public class VoteAlgorithm {
//
//    public List<String> getCandidatesInSortedOrder(List<List<String>> ballots) {
//        Map<String, Integer> map = new HashMap<>();
//        Map<String, Integer> firstOccurrence = new HashMap<>();
//
//        for (List<String> ballot : ballots) {
//            Set<String> processedCandidates = new HashSet<>();
//            for (int i = 0; i < ballot.size(); i++) {
//                String candidate = ballot.get(i);
//                if (processedCandidates.contains(candidate)) {
//                    continue; // Skip if candidate already processed
//                }
//                map.put(candidate, map.getOrDefault(candidate, 0) + ballot.size() - i);
//                firstOccurrence.putIfAbsent(candidate, i);
//                processedCandidates.add(candidate);
//            }
//        }
//        List<String> sortedCandidates = map.entrySet().stream()
//                .sorted((e1, e2) -> {
//                    int pointsDiff = e2.getValue().compareTo(e1.getValue());
//                    if (pointsDiff != 0) {
//                        return pointsDiff;
//                    } else {
//                        return firstOccurrence.get(e1.getKey()).compareTo(firstOccurrence.get(e2.getKey()));
//                    }
//                })
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());
//
//        return sortedCandidates;
//    }
//}
