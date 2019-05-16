This Eclipse project was built and run using Java 1.8.0_131. To build project, select Project->Build Project.

The project contains the following runnable code:

tls.test.Test.java - Returns smallest positive integer value which does not exist in array A.
	Run tls.test.Test.main()
tls.test1.Solution.java - Returns the Hours, Minutes, and Seconds for any partial remainder day for the input number of seconds.
	Run tls.test1.Solution.main()
tls.test2.Solution.java - For this Test, was allowed to change 4 lines of code to fix one or more defects, but not add new lines,
	move existing lines, or delete existing line. Original lines have been commented out.
	The method solution is suppose to return the value with the maximum number of occurrences.
	If there was a tie for maximum number of occurrences, then the first value in the array that
	was tied for maximum number of occurrences is returned.
	For example: input array {1,2,2,4,4} would return 2 as the value with the maximum number of occurrences
	since 2 if before 4 in the array.
	Run tls.test2.Solution.main()
tls.test3.Solution.java - Returns the count of values with square roots for the given range of numbers
	The count includes square roots for negative numbers because Complex Number Theory allows
	negative numbers to have square roots.
	Run tls.test3.Solution.main()
tls.test4.Solution.java - The code calculates the storage used by four types of files (music, images, movies, and other).
	Even though StringTokenizer is deprecated, I used it instead of String.split() because some of
	the developer forums seem to think StringTokenizer is much faster than String.split(). Also, using
	StringTokenizer allowed me to not have to create String arrays for some of the lower level tokens
	derived from the line tokens that each contained one line of data from the original multi-line input string.
	Run tls.test4.Solution.main()
tls.elevator - Basic elevator simulator. Currently configured for a six floor building with four elevators. Two of the elevators (3 and 4)
	are also freight elevators with a front passenger door and a back freight door. Since only starting state elevator and floor data is set 
	in BuildingElevatorController.setupTestState(), an elevator can sit idle when it reaches a floor for pickup or drop off and it has no further
	drop offs or new pickups of the correct type for that elevator. An example of this is Elevator 1 which services the pickup going up
	on Floor 1. Since the passenger did not press a floor to go to and all other passenger pickups have been serviced by other elevators,
	Elevator 1 sits idle on Floor 1 after the 1st iteration. If Elevator 1, as part of iteration 2, had the passenger pressed a floor number
	button (like floor 3) to go to, Elevator 1 would have gone to that floor to drop off the passenger. 
	Run tls.elevator.Building.main()


	