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
 * Click Import Project > "Create Project from existing sources" > Next > Next > Next > Next > Next > Finish
 * Double click on project name > Right-click on 'build.xml', "Add as Ant build file"

 * Right-click on build.xml -> "Add as Ant Build File"
 * Ant Build window -> "install" 
 * Right-click on src -> "Mark it as sources root"
 * Add libs and tools
  * Use IvyIDEA plugin -> "use ivy default"
  * Add libs by "hand" 
 * Right-click "New Folder" name it UnitTest{s} and mark it as "Test Sources Root"
 * For CUT or SUT ALT+ENTER generate tests....

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
