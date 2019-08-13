# CLI interface for Apriori(FPG) association rule mining algorithm

###  - Dataset -

Dataset is a text file containing transactional data. 
Each transaction is located on a new line, starting with the first. 
Transaction elements are separated by a white space. An example in the root of the repository.

### - Posix parameters -

-f,--file <arg>        File name.             (default - none, required - true)

-a,--algorithm <arg>   Algorithm name.        (default - "FPG", required - false)

-ms,--support <arg>    Min mining support.    (default - 2, required - false)

-ss,--set-size <arg>   Min result set size.   (default - 1, required - false)

-mt,--max-tr-size <arg>   Max transaction size. (default - 0x7fffffff, required - false)

### - Usage -

`java -jar pm [-a <arg>] -f <arg> [-ms <arg>] [-mt <arg>] [-ss <arg>]`

### - Usage example -

`java -jar pm -f filename.txt -a apriori -ms 3 -ss 2 -mt 20`

### - Algorithm output result -

The results of the algorithm are saved in the "OUTPUT" folder next to the file.
The name of the new file will correspond to the current date and time.
