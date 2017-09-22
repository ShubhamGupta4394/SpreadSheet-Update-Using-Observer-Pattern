
Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=input.txt -Darg1=output.txt -Darg2=0 

-----------------------------------------------------------------------

## To create tarball for submission
ant -buildfile src/build.xml tarzip

-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.”

[Date: ] -- 04/04/2017

-----------------------------------------------------------------------

Provide justification for Data Structures used in this assignment in
term of Big O complexity (time and/or space)

LinkedHashMap - I have used LinkedHashMap to store rows of cell mapping String to Integer because it maintains order, as well as
it can handle key value pair, which makes it better than other data structure.
Time Complexity for operations in LinkedHashMap is O(1).

ArrayList - I have used ArrayList to store Cell and SheetMatrix because it stores element in order, can store objects,
as well as Collections operations can be performed on it.
Time Complexity for operation in ArrayList is O(1).

I have used recursion algorithm to detect the cycle and for search in arraylist. Time complexity is of 0(n^2).

-----------------------------------------------------------------------

Provide list of citations (urls, etc.) from where you have taken code
(if any).


For Time Complexity -
http://stackoverflow.com/questions/559839/big-o-summary-for-java-collections-framework-implementations.


