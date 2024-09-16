package company.coursera;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/*
Q: Given a list of ballots consisting of votes and all the candidates grouped together as map where each ballot shows the candidate getting those many votes in that specific position -

Find candidate with "popular vote" - this is the candidate having most number of votes from the electorate's as their first choice
Find "ranked choice" winner. This is calculated as follows -
If there is a candidate who has more than half first choice votes, then he is the clear winner.
If there are multiple candidates having same number of first choice votes, we remove the candidate with least number of first choice votes, recalculate the choice votes and thereby return the candidate with most first place votes; so on until we either find a candidate with clear majority ( > 50% votes) or if there is just a single candidate remaining.
Eg: {([A, B, C] -> 4), ([B, C, A] -> 3), ([C, B, A] -> 2)}.
This means that candidate A got 4 votes as the electorate's first choice, candidate B got 4 votes as electorate's 2nd choice, candidate C got 4 votes as electorate's third choice. Continuing further, candidate B got 3 votes as electorate's first choice, candidate C got 3 votes as electorate's 2nd choice and candidate A got 3 votes as electorate's third choice. And so on.

Solution:

Popular winner - Candiate A is the popular winner having received the most number of votes as the electorate's first choice.
Ranked winner - In this case, although candidate A has got most votes as the electorate's first choice, it is not the clear majority ( 4 != 50% of total of 9 ballots). So we remove the votes for the candidate with the least number of first choice votes i.e. C in this case. Now we are left with - {([A, B] -> 4), ([B, A] -> 3), ([B, A] -> 2)} giving us the winner as candidate B who now has 5 votes as the electorate's first choice.
 */

@Slf4j
public class FindCandidate {

    private static  String findPopularCandidate(Map<List<String>, Integer> ballots) {
        Map<String, Integer> voteCount = new HashMap<>();

        for (List<String> ballot : ballots.keySet()) {
            String candidate = ballot.get(0);
            voteCount.put(candidate, voteCount.getOrDefault(candidate, 0) + ballots.get(ballot));
        }

       String candidate =
               voteCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(1)
                       .map(m->m.getKey()).collect(Collectors.toList()).get(0);
log.info("Candidate found   "+candidate);
//       String popularWinner = null;
//        int maxVotes = -1;
//
//        for (String candidate : voteCount.keySet()) {
//            int votes = voteCount.get(candidate);
//
//            if (votes > maxVotes) {
//                maxVotes = votes;
//                popularWinner = candidate;
//            }
//        }
//        return popularWinner;

        return candidate;
    }

    private static String findRankedWinner(Map<List<String>, Integer> ballots) {
        Map<String, Integer> voteCount = new HashMap<>();

        for (List<String> ballot : ballots.keySet()) {
            String candidate = ballot.get(0);
            voteCount.put(candidate, voteCount.getOrDefault(candidate, 0) + ballots.get(ballot));
        }

        int totalVotes = 0;
        for (int votes : voteCount.values()) {
            totalVotes += votes;
        }

        int halfVotes = (int) Math.ceil(totalVotes / 2.0);

        while (true) {
            String candidate = null;
            int maxVotes = -1;

            for (String c : voteCount.keySet()) {
                int votes = voteCount.get(c);
                if (votes > maxVotes) {
                    maxVotes = votes;
                    candidate = c;
                }
            }

            if (maxVotes >= halfVotes) {
                return candidate;
            }

            if (voteCount.size() == 1) {
                return candidate;
            }

            voteCount.remove(candidate);

            for (List<String> ballot : ballots.keySet()) {
                if (ballot.contains(candidate)) {
                    int voteWeight = ballots.get(ballot);
                    int idx = ballot.indexOf(candidate);
                    String nextChoice = null;
                    for (int i = idx + 1; i < ballot.size(); i++) {
                        String choice = ballot.get(i);
                        if (voteCount.containsKey(choice)) {
                            nextChoice = choice;
                            break;
                        }
                    }
                    if (nextChoice == null) {
                        nextChoice = ballot.get(0);
                    }
                    voteCount.put(nextChoice, voteCount.getOrDefault(nextChoice, 0) + voteWeight);
                }
            }
        }
    }


    public static void main(String[] args) {
        Map<List<String>, Integer> ballots = new HashMap<>();
        ballots.put(Arrays.asList("A", "B", "C"), 4);
        ballots.put(Arrays.asList("B", "C", "A"), 3);
        ballots.put(Arrays.asList("C", "B", "A"), 2);
//        ballots.put(Arrays.asList("B"), 2);


        String popularWinner = findPopularCandidate(ballots);
        String rankedWinner = findRankedWinner(ballots);

        System.out.println("Popular Winner: " + popularWinner);
        System.out.println("Ranked Winner: " + rankedWinner);
    }


}
