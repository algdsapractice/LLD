package company.coursera;

import java.util.*;
import java.util.stream.Collectors;

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
        ballots.put(Arrays.asList("B"), 2);


        String popularWinner = findPopularCandidate(ballots);
        String rankedWinner = findRankedWinner(ballots);

        System.out.println("Popular Winner: " + popularWinner);
        System.out.println("Ranked Winner: " + rankedWinner);
    }


}
