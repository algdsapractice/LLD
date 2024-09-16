package company.coursera;

import java.util.*;


record BallotGroup(List<String> candidates, int votes) {
}

public class FindCandidate2 {

    public static void main(String[] args) {
        List<BallotGroup> ballots = new ArrayList<>();
        ballots.add(new BallotGroup(Arrays.asList("A", "B", "C"), 4));
        ballots.add(new BallotGroup(Arrays.asList("B", "C", "A"), 3));
        ballots.add(new BallotGroup(Arrays.asList("C", "B", "A"), 2));
        ballots.add(new BallotGroup(Arrays.asList("B"), 2));

        String popularWinner = findPopularCandidate(ballots);
        String rankedWinner = findRankedWinner(ballots);

        System.out.println("Popular Winner: " + popularWinner);
        System.out.println("Ranked Winner: " + rankedWinner);
    }

    private static String findRankedWinner(List<BallotGroup> ballots) {

        Map<String,Integer> voteCountForCandidate = new HashMap<>();

        for(BallotGroup ballotGroup:ballots){
           String  firstChoiceCandidate=ballotGroup.candidates().getFirst();
            voteCountForCandidate.put(firstChoiceCandidate, voteCountForCandidate.getOrDefault(firstChoiceCandidate,0)+ballotGroup.votes());
        }

       //
       return Collections.max(voteCountForCandidate.entrySet(), Map.Entry.comparingByValue()).getKey();

    }

    private static String findPopularCandidate(List<BallotGroup> ballots) {
        return null;
    }
}
