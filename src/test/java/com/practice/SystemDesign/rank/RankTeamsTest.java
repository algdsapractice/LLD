package com.practice.SystemDesign.rank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RankTeamsTest {

    RankTeams  rankTeams ;
    String[] votes;


    @BeforeEach
    public void setUp()  {

        rankTeams= new RankTeams();
        votes = new String[]{"ABC","ACB","ABC","ACB","ACB"};
    }


    @Test
    public void testRankTeams()  {

        String s=rankTeams.rankTeams(votes);
        Assertions.assertEquals("ACB",s,"String did not match");

    }

}