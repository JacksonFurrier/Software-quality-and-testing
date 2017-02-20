# Unit testing, Tools and Libs #

Download the tools to run the examples for the Unit testing part of
the "Software quality and testing" course.

* [Coding guideline](https://google.github.io/styleguide/javaguide.html)
* Sample project [uCoach](https://github.com/ucoach)

### Computer lab news ###
* Use Windows because Linux is not working at the Lab....

### Mandatory programs, tools, libs for sample setup ###
* [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Apache ant](http://ant.apache.org/bindownload.cgi)
* [JetBrains IntelliJ](https://www.jetbrains.com/idea/download/#section=windows)

### Sample environment set-up for ucoach-authentication-api ###
1. Clone project, run command from cmd, or powershell       
    cmd :  `git clone https://github.com/uCoach/authentication-api.git`
2. Start IntelliJ and Import project from the directory you've cloned into.
 * File > Project Structure > Project SDK. Set it to the installed root directory of your JDK ( 1.8.x mandatory )
 * Project language level, set it to "SDK Default"
 * Click Import Project > "Create Project from existing sources" > Next > Next > Next > Next > Next > Finish
 * Double click on project name > Right-click on 'build.xml', "Add as Ant build file"
 * On the Ant Build Window double click "install"
 
3. Install Ivy IDEA and configure
 * File > Plugins > Search in repositories for `IvyIDEA` >> Click install and restart.
 * Right click on "ivy.xml" > IvyIDEA > Resolve for all modules > Open Project settings > Ivy Settings set to "Use ivy default"
 * Right click on "ivy.xml" > IvyIDEA > Resolve for authentication-api module 
    * You can ignore `org.glassfish.ha#ha-api;3.1.9:	Unrecognized artifact type: hk2-jar, will not add this as a dependency in IntelliJ.`
 * Push `CTRL + F9`  to compile and test if dependencies have been resolves

4. Add testing foldery to the project 
 * Right click on project name `authentication-api` > New > Folder > Name it `UnitTest`
 * Right click on `Unit test` folder > Mark directory as > Test Sources Root
 * Open src.ucoach.autu.restclient.util.JsonParser 
 * Click on the classname `JsonParser` > press `ALT + ENTER` > Create Test 
    * Testing library JUnit5
    * For the first tinme IntelliJ won't find it so click on `Fix` if needed
    * Check all member functions to generate test functions for
    * Click Ok
 * Recompile to test if adding the sample test class works.
 * If compilation worked, right click on `UnitTest` folder > ` Run All Tests `
 * All tests should pass and you should see `Feb 20, 2017 1:18:45 PM org.junit.platform.launcher.core.ServiceLoaderTestEngineRegistry loadTestEngines
INFO: Discovered TestEngines with IDs: [junit-jupiter, junit-vintage]
Process finished with exit code 00
 
5. Enjoy unit testing.

### Course 1. ###
* Clone/Fork/Download [authentication-api](https://github.com/uCoach/authentication-api)
* Sample project introduction
* Simple Unit testing example with refactoring

### Course 2-3. ###

* [Groovy](https://dl.bintray.com/groovy/maven/apache-groovy-binary-2.4.8.zip)
* [Spock if(gradle.works)](https://github.com/spockframework/spock.git)
* [Spock else](https://search.maven.org/remotecontent?filepath=io/sniffy/spock/3.1.0-RC10/spock-3.1.0-RC10.jar)
* [TestNG](https://github.com/cbeust/testng.git)

### Course 4. ###
 
* [PowerMockito, Mockito, JUnit 4.12](http://dl.bintray.com/johanhaleby/generic/powermock-mockito2-junit-1.6.6.zip)

### Contact ###

* Adam Istvan Szucs
* szucs.adamistvan.89@gmail.com :subject[2016_Software_Testing]
