SmallEvo
========
A small genetic algorithm framework written using new Java 8 features  
VERSION 1.2 RELEASE 20140922 
* Java 8  
* Maven 3  
* No external dependencies at this time, besides JUnit  
* Note that the current Unit Tests are really just eyeball style tests so I can quickly see if things are working.  
* See examples in the com.mattring.smallevo.examples package  

---

* 1st example is PhrasesMain.java
* Attempts to evolve a genome to match the incoming phrase.
* `java com.mattring.smallevo.examples.phrases.PhrasesMain "I love zebras and elephants"`

---

* 2nd example is TravelingSalesmanMain.java
* Attempts to evolve minimum distance solution to the Traveling Salesman Problem (TSP).
* Reads the tsp_destinations.txt file (which can be created with DestinationFileCreatorMain).
* The TSP, as I have defined it, is to find the shortest possible path that:
  1. begins at the salesperson's home base (defined as destination[0])
  2. visits every other destination once
  3. returns to home base
* Provides a javafx-based visual playback of the evolution.
* `java com.mattring.smallevo.examples.travelingsalesman.TravelingSalesmanMain`
  
![traveling salesman animation](http://sawyervacayhouse.weebly.com/uploads/2/7/0/2/2702135/5502545_orig.gif)

---

SmallEvo is released as open source under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0).  
