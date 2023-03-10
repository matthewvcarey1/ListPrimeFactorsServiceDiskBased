package uk.co.example.ListPrimeFactorsService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ListPrimeFactorsController {

    @Value("${primesDbPath}")
    String primesDbPath;

    @Value("${upperLimitString}")
    String upperLimitString;

    @Value("${cacheSize}")
    String cacheSizeString;

    @Value("${cacheableMilliSeconds}")
    String cacheableMillisecondString;


    ListPrimeFactorsService pfs;

    // @GetMapping("/primefactors/{valueString}")
    //@RequestMapping(value = "/primefactors/{valueString}", method = RequestMethod.GET)
    @GetMapping("/primefactors/{valueString}")
    public ListFactorsResult primePath(@PathVariable String valueString) {
        if(pfs == null) {
            int cacheSize = Integer.parseInt(cacheSizeString);
            int cacheableMilliseconds = Integer.parseInt(cacheableMillisecondString);
            pfs = new ListPrimeFactorsService();
            pfs.setPrimeDBPath(primesDbPath);
            pfs.setUpperLimitString(upperLimitString);
            pfs.setCacheSize(cacheSize);
            pfs.setCacheableMillisecs(cacheableMilliseconds);
        }
        return pfs.list(valueString);
    }

    //  @GetMapping("/primeLimits/")
    // @RequestMapping(value = "/primeLimits/", method = RequestMethod.GET)
    @GetMapping("/primeLimits/")
    public PrimeLimits primeLimits() {
        if(pfs == null) {
            int cacheSize = Integer.parseInt(cacheSizeString);
            int cacheableMilliseconds = Integer.parseInt(cacheableMillisecondString);
            pfs = new ListPrimeFactorsService();
            pfs.setPrimeDBPath(primesDbPath);
            pfs.setUpperLimitString(upperLimitString);
            pfs.setCacheSize(cacheSize);
            pfs.setCacheableMillisecs(cacheableMilliseconds);
        }
        return pfs.limits();
    }

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
