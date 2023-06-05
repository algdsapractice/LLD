package com.practice.SystemDesign.rank;

import java.util.*;

public class RankTeams {
    public  String rankTeams(String[] votes){
        Map<Character,int[]> teamVotes = new HashMap<>();

        for(String vote : votes){
            for(int i = 0; i < vote.length(); i++){
                char team =  vote.charAt(i);
                teamVotes.putIfAbsent(team, new int[vote.length()]);
                teamVotes.get(team)[i]++;

            }
        }

        List<Character> teamsList = new ArrayList<>(teamVotes.keySet());
        Collections.sort(teamsList, (a, b) -> compareTeams(a, b,teamVotes, votes));

        // direct approach
        // Collections.sort(sortedTeams, (a, b) -> {
        //     for (int i = 0; i < votes[0].length(); i++) {
        //         if (teamVotes.get(a)[i] != teamVotes.get(b)[i]) {
        //             return teamVotes.get(b)[i] - teamVotes.get(a)[i];
        //         }
        //     }
        //     return a - b;
        // });
        return joinList(teamsList);
    }

    private static String joinList(List<Character> teamsList) {
        StringBuilder result = new StringBuilder();
        for (char team : teamsList) {
            result.append(team);
        }

        return  result.toString();
    }

    private  int compareTeams(Character teamA, Character teamB, Map<Character, int[]> teamVotes, String[] votes) {
        for (int i = 0; i < votes[0].length(); i++) {
            int voteCountA = teamVotes.get(teamA)[i];
            int voteCountB = teamVotes.get(teamB)[i];

            if (voteCountA != voteCountB) {
                return Integer.compare(voteCountB, voteCountA);
            }
        }
        return teamA.compareTo(teamB);  // Compare based on team identifiers
    }
}
