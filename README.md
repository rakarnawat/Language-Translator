## Name - Rohit Karnawat

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in words/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile words/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile words/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile words/src/build.xml run -Darg0=input1.txt -Darg1=input2.txt -Darg2=4 -Darg3=output.txt -Darg4=output2.txt -Darg5=2

Description: Runs the code.

-----------------------------------------------------------------------
## Description:

Time Complexity = O(N)

In this project we have used Visitor and Strategy design pattern. We have taken two input files and value of K from the command line. First file has sentences in it and Second file contains multiple British and American words.
While designing this project we have created many java helper files. In this we have read the input file using bufferreader function and we write the output using filewriter function.
We have stored all the sentences in arraylist and in that we have each sentence stored in different arraylist where each word is an element. In this we count the occurrence of words so that we can get words. We have used iterator method to get the next word and to count them.
We have created iterator and component interface in the project.
In the second part of the project we have used hashmap for storing the Briish and American words. The British words are saved as the key and American words are saved as value in the hashmap.
We find the compare the words from the British to American input file with the words in the sentences. If the word is found then it is replaced by the value of that particular key in the file and the output is written in the output file. 
