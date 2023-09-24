# ListPrimeFactorsService

A very silly REST service that very quickly finds the Prime Factors of numbers in a range. This range can be the entire range of java positive long integers, excluding 1. 

Really if you want to find the prime factors of larger numbers there are at least two sites that will provide them: 

https://www.wolframalpha.com/input?i=factorize+1374389534715675665 and http://www.socr.ucla.edu/Applets.dir/SOCR_PrimeNumberDecomposition.html .

The prime numbers it uses are precalculated and stored in a disk based MapDB TreeSet.

This is because there are a lot of prime numbers and it is not practical to have them all in memory.

This program calculates the Prime Factors of the input number. The limit is the square of the largest prime it has in its contiguous set of primes numbers or the largest long integer in java which ever is less.

One feature of this program is that if it "discovers" another prime number while calculating some prime factors it stores that prime in its set. When this program has stored a prime some calculations using that prime number become much faster.

It is a Gradle Springboot java program. It is based on the earlier java standalone commandline program.

I have tidied up the original routines to make them more in line with the "Functional" use of Java.

There are two other programs needed, one to generate a large set of the primes called [generatePrimes](https://github.com/matthewvcarey1/generatePrimes) and another to load them into the disk based set called [buildDiskLongSet](https://github.com/matthewvcarey1/BuildDiskLongSet). 

Generating the primes is quite quick, but loading them into the disk based TreeSet is very slow indeed (40 Hours).

I will fill out the tests.

I have included a very small disk based db of primes in the form of zip file. If you unzip this file on an SSD drive and put an entry in application.properties pointing at the file. 

Then 
```
/gradlew clean build
```
 and 
``` 
./gradlew clean bootRun
```
will work.

If you generate a full size db at least up to 0x100000000 it should factorize values up to MAX LONG. If you do up to 0x1000000000 factorizing some values will be quicker but the disk based db will inittially be much bigger.

The disk based db files can be zipped or gzipped down to much smaller archives when not in use.

## Note

You must edit the application.properties file and unzip dummy.db.zip before running or building this application.


To build and test
```
./gradlew clean build
```

To run as a service
```
./gradlew clean bootRun
```

To run in https:

Changed all the http: references in index.html to https:

To generate a p12 file from a certificate and key
 openssl pkcs12 -export -name springboot -in certificate.crt -inkey private.key -out springboot.p12 

Then copy the springboot.p12 to your src/main/resources folder