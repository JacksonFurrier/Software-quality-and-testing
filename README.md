# Unit testing, Tools and Libs #

Download the tools to run the examples for the Unit testing part of
the "Software quality and testing" course.

* [Coding guideline](https://google.github.io/styleguide/javaguide.html)
* Sample project [ToBeAnnounced](https://github.com)

### Computer lab news ###
* Use Windows because Linux is not working at the Lab....

### Mandatory programs, tools, libs for sample setup ###
* [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Apache ant](http://ant.apache.org/bindownload.cgi)
* [JetBrains IntelliJ](https://www.jetbrains.com/idea/download/#section=windows)

### Sample environment set-up for [ucoach-authentication-api](https://github.com/uCoach/authentication-api) ###
1. Clone project, run command from cmd, or powershell `git clone https://github.com/uCoach/authentication-api.git`
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

### Course 1. authentication-api MANUAL CONFIGURATION ###
* Clone/Fork/Download [authentication-api](https://github.com/uCoach/authentication-api)
* Sample project introduction
* Simple Unit testing example with refactoring
* Add `ivy.xml` from course_1

### Course 1. authentication-api AUTO CONFIGURATION ###
* GoTo Folder [course_1](https://github.com/JacksonFurrier/Software-quality-and-testing/tree/master/course_1) and follow the steps there

### Course 2-3. process-centric-service MANUAL CONFIGURATION ###

* Clone/Fork/Download [process-centric-service](https://github.com/uCoach/process-centric-service)
* [Groovy](https://dl.bintray.com/groovy/maven/apache-groovy-binary-2.4.8.zip)
* [Spock if(gradle.works)](https://github.com/spockframework/spock.git)
* [Spock else](https://search.maven.org/remotecontent?filepath=io/sniffy/spock/3.1.0-RC10/spock-3.1.0-RC10.jar)
* [TestNG](https://github.com/cbeust/testng.git)
* Add `ivy.xml` from course_2

###  Course 2-3. process-centric-service AUTO CONFIGURATION ###
* GoTo Folder [course_2](https://github.com/JacksonFurrier/Software-quality-and-testing/tree/master/course_2) and follow the steps there

### Course 4. ###
 
* [PowerMockito, Mockito, JUnit 4.12](http://dl.bintray.com/johanhaleby/generic/powermock-mockito2-junit-1.6.6.zip)

### Assignment ###

Under the folder [Internal data service](https://github.com/JacksonFurrier/Software-quality-and-testing/tree/master/course_4/internal-data-service/src/ucoach/data/ws)
meet the following requirments
* Unit test the classes in the folder such that
  * Tools for unit testing is a free of choose, you can use JUnit, TestNG, Spock or whatever unit testing framework
  * Hand the assigment in person at EIT Digital CLC, please write email before to match timespan and date for reviewing the assigment
  * **Deadline 2017. May. 8**
  * Reach at least 20% line coverage to pass the course, grading goes such that:
    * ">=" 20% - 2 
    * ">=" 30% - 3 
    * ">=" 40% - 4 		
    * ">=" 60% - 5 
	 

### Contact ###

* Adam Istvan Szucs
* szaqaei@inf.elte.hu :subject[2018_Software_Testing]
