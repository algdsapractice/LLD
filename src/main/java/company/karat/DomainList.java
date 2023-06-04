package com.practice.SystemDesign.company.karat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainList {


    public static Map<String,Integer>  getDomainCountUsingSplit(String [] domains){
        Map<String,Integer> dmap = new HashMap<>();
        int [] hitsCounter = new int[domains.length] ;
        int index  = 0 ;
        for (String domain:domains) {
            String [] splitData = domain.split("[,.]") ;
            System.out.println("____________________________");
            Arrays.stream(splitData).forEach(System.out::println);
            System.out.println("____________________________");
            hitsCounter[index] = Integer.parseInt(splitData[0].trim()) ;
            String key = "" ;
            for(int i = splitData.length -1 ; i > 0 ; i--)
            {
                if( key== ""){
                    key = splitData[i] + key ;
                    System.out.println("splitData[i] "+splitData[i]);
                    System.out.println("key before"+key);

                }else{
                    key = splitData[i] + "." +  key;
                    System.out.println("key After"+key);
                }
                dmap.put(key,dmap.getOrDefault(key,0) + hitsCounter[index]) ;
            }

        }
        dmap.forEach((key, value) -> System.out.println(key + " : " + value));

        return dmap;
    }
    public static List<String>  getDomainCount(String [] domains){
        Map<String,Integer> dmap = new HashMap<>();
        for (String domain:domains) {
                Integer hits = Integer.parseInt(domain.substring(0,domain.indexOf(",")));
                String currentDomain = domain.substring(domain.indexOf(",")+1);
                while(!currentDomain.isEmpty()){
                    dmap.put(currentDomain,dmap.getOrDefault(currentDomain,0)+hits);
                    int domainIndex = currentDomain.indexOf(".");
                    currentDomain = currentDomain.substring(domainIndex+1);
                    if(domainIndex==-1){
                        currentDomain="";
                    }
                }

            dmap.forEach((key, value) -> System.out.println(key + " : " + value));



        }
        return null;
    }

    public static void main(String[] args) {

        String [] domains = {"400,map.google.com", "400,mail.google.com", "200,mail.yahoo.com","200,job.yahoo.com"};
        getDomainCountUsingSplit(domains);
       getDomainCount(domains);
    }
}
