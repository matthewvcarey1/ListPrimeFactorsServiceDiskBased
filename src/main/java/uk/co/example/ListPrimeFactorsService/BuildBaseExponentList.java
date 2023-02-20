package uk.co.example.ListPrimeFactorsService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BuildBaseExponentList {
    private BuildBaseExponentList(){}
    protected static ArrayList<BaseExponent> build(ArrayList<Long> factors){
        // Deduplicate in to a TreeMap with the key being the number and value how many times the number occurs.
        Map<Long,Long> factorCountMap = factors.stream()
                .collect(Collectors.toMap(Function.identity(), v -> 1L, Long::sum, TreeMap::new));
        ArrayList<BaseExponent> lbe = new ArrayList<>();
        // build the list of base exponents in the correct order because a tree map is sorted no more sorting is needed.
        for (Map.Entry<Long, Long> entry : factorCountMap.entrySet()) {
            lbe.add(new BaseExponent(entry.getKey(), entry.getValue()));
        }
        return lbe;
    }
}
