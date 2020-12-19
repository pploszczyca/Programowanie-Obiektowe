package statistics;

import java.util.Map;

public class AbstractMapStatistics {
    protected String findMostPopularGen(Map<String, Integer> genes){
        Map.Entry<String, Integer> maxEntry = null;
        String mostPopularGen;

        for (Map.Entry<String, Integer> entry : genes.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }

        try{
            mostPopularGen = maxEntry.getKey();
        } catch (Exception ex){
            mostPopularGen = "";
        }

        return mostPopularGen;
    }

}
