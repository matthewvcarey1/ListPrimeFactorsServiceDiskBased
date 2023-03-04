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

    ListPrimeFactorsService pfs;

    // @GetMapping("/primefactors/{valueString}")
    //@RequestMapping(value = "/primefactors/{valueString}", method = RequestMethod.GET)
    @GetMapping("/primefactors/{valueString}")
    public ListFactorsResult primePath(@PathVariable String valueString) {
        if(pfs == null) {
            pfs = new ListPrimeFactorsService();
            pfs.setPrimeDBPath(primesDbPath);
            pfs.setUpperLimitString(upperLimitString);
        }
        return pfs.list(valueString);
    }

    //  @GetMapping("/primeLimits/")
    // @RequestMapping(value = "/primeLimits/", method = RequestMethod.GET)
    @GetMapping("/primeLimits/")
    public PrimeLimits primeLimits() {
        if(pfs == null) {
            pfs = new ListPrimeFactorsService();
            pfs.setPrimeDBPath(primesDbPath);
            pfs.setUpperLimitString(upperLimitString);
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
