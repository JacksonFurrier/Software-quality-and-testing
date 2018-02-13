# Unit testing, Tools and Libs #

Download the tools to run the examples for the Unit testing part of
the "Software quality and testing" course.

* [Coding guideline](https://google.github.io/styleguide/javaguide.html)
* Sample project [ToBeAnnounced](https://github.com)

### Computer lab news ###
* Use Windows if possible

### Mandatory programs, tools, libs for sample setup ###
* [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Apache ant](http://ant.apache.org/bindownload.cgi)
* [JetBrains IntelliJ](https://www.jetbrains.com/idea/download/#section=windows)

### Sample environment set-up for [ToBeAnnounced](https://github.com) ###
1. Clone project, run command from cmd, or powershell `git clone https://github.com`
2. Start IntelliJ and Import project from the directory you've cloned into.
 * Click Import Project > "Create Project from existing sources" > Next > Next > Next > Next > Next > Finish
 * File > Project Structure > Project SDK. Set it to the installed root directory of your JDK ( 1.8.x mandatory )
 * Project language level, set it to "SDK Default"
 * Double click on project name > Right-click on 'build.xml', "Add as Ant build file"
 * On the Ant Build Window double click "install"
 
3. Install Ivy IDEA and configure
 * File > Settings > Plugins > Search in repositories for `IvyIDEA` > Click install and restart.
 * File > Settings > IvyIDEA > Ivy Settings set to "Use ivy default"
 * Right click on "ivy.xml" > IvyIDEA > Resolve for all modules > Open Project settings > Ivy Settings set to "Use ivy default"
 * Right click on "ivy.xml" > IvyIDEA > Resolve for authentication-api module 
    * You can ignore `org.glassfish.ha#ha-api;3.1.9:	Unrecognized artifact type: hk2-jar, will not add this as a dependency in IntelliJ.`
 * Push `CTRL + F9`  to compile and test if dependencies have been resolves

4. Add testing foldery to the project 
 * Right click on project name `authentication-api` > New > Folder > Name it `UnitTest`
 * Right click on `UnitTest` folder > Mark directory as > Test Sources Root
 * Open src.ucoach.autu.restclient.util.JsonParser 
 * Click on the classname `JsonParser` > press `ALT + ENTER` > Create Test 
    * Testing library JUnit5
    * For the first tinme IntelliJ won't find it so click on `Fix` if needed
    * Check all member functions to generate test functions for
    * Click Ok
 * Recompile to test if adding the sample test class works.
 * If compilation worked, right click on `UnitTest` folder > ` Run All Tests `
 * All tests should pass and you should see `Feb 20, 2017 1:18:45 PM org.junit.platform.launcher.core.ServiceLoaderTestEngineRegistry loadTestEngines INFO: Discovered TestEngines with IDs: [junit-jupiter, junit-vintage] Process finished with exit code 0`
 
5. Enjoy unit testing.

### Assignment ###

Under the folder [ToBeAnnounced](https://github.com)
meet the following requirments
* Unit test the classes in the folder such that
  * Tools for unit testing is a free of choose, you can use JUnit, TestNG, Spock or whatever unit testing framework
  * Hand the assigment in person at EIT Digital CLC, please write email before to match timespan and date for reviewing the assigment
  * **Deadline 2018. March. 31**
  * Reach at least 20% line coverage to pass the course, grading goes such that:
    * ">=" 50% - 2 
    * ">=" 60% - 3 
    * ">=" 70% - 4 		
    * ">=" 80% - 5 
	 

### Contact ###

* Adam Istvan Szucs
* szaqaei@inf.elte.hu :subject[2018_Software_Testing]
