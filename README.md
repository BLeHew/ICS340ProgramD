# SchedulingWithConflicts
This is a program that will generate a correct course schedule given a set of constraints.It will start off in a random
configuration, and will work from there to generate a correct schedule. Along with the constraints file, there are numerous other 
unwritten constraints that the program must follow in order to determine a correct schedule. These constraints are:
All 30 courses must be taken.
Fall and Spring semesters can have a max of 3 days, Summer a max of 2.
Courses in the same semester cannot be scheduled on the same day.

I chose to solve this problem by domain trimming as well as keeping track of the fitness of each course in each semester. I have yet to find
a schedule that the program could not solve.
