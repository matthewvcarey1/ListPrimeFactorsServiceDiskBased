package uk.co.example.ListPrimeFactorsService;

import java.util.*;
import java.util.stream.Collectors;

public class BuildBaseExponentList {
    private BuildBaseExponentList(){}

    protected static List<BaseExponent> build(List<Long> factors) {
        // Deduplicate into a TreeMap with the key being the number and value how many times the number occurs.
        Map<Long, Long> factorCountMap = factors.stream()
                .collect(Collectors.toMap(v -> v, v -> 1L, Long::sum, TreeMap::new));
        List<BaseExponent> lbe = new ArrayList<>();
        // Build the list of base exponents in the correct order because a TreeMap is sorted no more sorting is needed.
        for (Map.Entry<Long, Long> entry : factorCountMap.entrySet()) {
            lbe.add(new BaseExponent(entry.getKey(), entry.getValue()));
        }
        return lbe;
    }
}
