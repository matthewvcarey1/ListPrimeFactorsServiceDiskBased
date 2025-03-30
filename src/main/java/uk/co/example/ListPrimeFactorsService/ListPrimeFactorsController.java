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

    private ListPrimeFactorsService setPfs(){
        if(this.pfs == null) {
            int cacheSize = Integer.parseInt(cacheSizeString);
            int cacheableMilliseconds = Integer.parseInt(cacheableMillisecondString);
            this.pfs = new ListPrimeFactorsService();
            this.pfs.setPrimeDBPath(primesDbPath);
            this.pfs.setUpperLimitString(upperLimitString);
            this.pfs.setCacheSize(cacheSize);
            this.pfs.setCacheableMillisecs(cacheableMilliseconds);
        }
        return this.pfs;
    }


    // @GetMapping("/primefactors/{valueString}")
    //@RequestMapping(value = "/primefactors/{valueString}", method = RequestMethod.GET)
    @GetMapping("/primefactors/{valueString}")
    public ListFactorsResult primePath(@PathVariable String valueString) {
        this.pfs = setPfs();
        return this.pfs.list(valueString);
    }

    //  @GetMapping("/primeLimits/")
    // @RequestMapping(value = "/primeLimits/", method = RequestMethod.GET)
    @GetMapping("/primeLimits/")
    public PrimeLimits primeLimits() {
        this.pfs = setPfs();
        return this.pfs.limits();
    }

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }


}
