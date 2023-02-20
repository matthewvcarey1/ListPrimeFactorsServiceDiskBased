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
    private static final String PARSE_ERROR_MESSAGE = "The input value could not be parsed as a Long Integer";

    private Long limit = null;

    private synchronized void setLimit(long lim){
        if(this.limit == null){
            this.limit = lim;
        }
    }
    @GetMapping("/primefactors/{valueString}")
    public ListFactorsResult  primePath(@PathVariable String valueString){
        try {
            ListPrimeFactors lpf = ListPrimeFactors.getInstance(primesDbPath,upperLimitString);
            if(upperLimitString==null){
                setLimit(lpf.limit);
            }else{
                setLimit(Long.parseLong(upperLimitString));
            }
            long value=Long.parseLong(valueString);

            if(!lpf.validate(value)){
                return new ListFactorsResult("Value not in range  2 - "+ this.limit,"",Long.toString(value));
            }
            return new ListFactorsResult("", lpf.ListFactorsString(value), Long.toString(value));
        } catch (NumberFormatException e){
            return new ListFactorsResult(PARSE_ERROR_MESSAGE , "", "");
        } catch (Exception e){
            return new ListFactorsResult(e.toString(),"", "");
        }
    }



    @GetMapping("/primeLimits/")
    public PrimeLimits primeLimits(){
        try {
            if(upperLimitString==null){
                if (this.limit == null){
                    ListPrimeFactors lpf = ListPrimeFactors.getInstance(primesDbPath,upperLimitString);
                    setLimit(lpf.limit);
                }
            }else{
                setLimit(Long.parseLong(upperLimitString));
            }

            final long upperLimit = this.limit;
            final long lowerLimit = 2L;
            return new PrimeLimits(Long.valueOf(lowerLimit).toString(),Long.valueOf(upperLimit).toString(),"");
        }catch (Exception e){
            return new PrimeLimits("","", e.getMessage());
        }
    }

    @RequestMapping("/")
    public ModelAndView home()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
