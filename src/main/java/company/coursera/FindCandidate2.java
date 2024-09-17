package company.coursera;

import lombok.extern.slf4j.Slf4j;

import java.util.*;


record BallotGroup(List<String> candidates, int votes) {
}


@Slf4j
public class FindCandidate2 {

    private static String findPopularCandidate(List<BallotGroup> ballots) {
        Map<String, Integer> voteCount = new HashMap<>();

        // Count first-choice votes for each candidate
        for (BallotGroup ballot : ballots) {
            String firstChoice = ballot.candidates().get(0);
            voteCount.put(firstChoice, voteCount.getOrDefault(firstChoice, 0) + ballot.votes());
        }

        // Find the candidate with the most first-choice votes
        return Collections.max(voteCount.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private static String findRankedWinner(List<BallotGroup> ballots) {
        Map<String, Integer> voteCount;
        int totalVotes = ballots.stream().mapToInt(BallotGroup::votes).sum();
        int halfVotes = (int) Math.ceil(totalVotes / 2.0);

        // Create a copy of ballots for manipulation
        List<BallotGroup> remainingBallots = new ArrayList<>(ballots);

        while (true) {
            voteCount = new HashMap<>();

            // Count first-choice votes for each candidate
            for (BallotGroup ballot : remainingBallots) {
                String firstChoice = ballot.candidates().get(0);
                voteCount.put(firstChoice, voteCount.getOrDefault(firstChoice, 0) + ballot.votes());
            }

            // Find the candidate with the most first-choice votes
            String leadingCandidate = Collections.max(voteCount.entrySet(), Map.Entry.comparingByValue()).getKey();

            if (voteCount.get(leadingCandidate) > halfVotes) {
                return leadingCandidate;
            }

            // Eliminate the candidate with the fewest first-choice votes
            String lowestCandidate = Collections.min(voteCount.entrySet(), Map.Entry.comparingByValue()).getKey();
            remainingBallots = eliminateCandidate(remainingBallots, lowestCandidate);

            // If only one candidate is left, return them as the winner
            if (voteCount.size() == 1) {
                return leadingCandidate;
            }
        }
    }

    private static List<BallotGroup> eliminateCandidate(List<BallotGroup> ballots, String candidateToEliminate) {
        List<BallotGroup> updatedBallots = new ArrayList<>();

        for (BallotGroup ballot : ballots) {
            List<String> updatedCandidates = new ArrayList<>(ballot.candidates());
            updatedCandidates.remove(candidateToEliminate);
            if (!updatedCandidates.isEmpty()) {
                updatedBallots.add(new BallotGroup(updatedCandidates, ballot.votes()));
            }
        }

        return updatedBallots;
    }

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
}
