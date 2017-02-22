

### Course_1 ###
  * Use project above `authentication-api`
  * Set JDK root path under File > Project Structure > Project > Project SDK > New (Depending on your configuration it should be under 
 `Program Files` or `Program Files(x86)` 
  * Set Project Language level under File > Project Structure > Project > Project Language Level to `SDK Default`
  * Make sure you've an installed IvyIDEA
   
### If IvyIDEA plugin is NOT INSTALLED ###
Install Ivy IDEA and configure
 * File > Settings > Plugins > Search in repositories for `IvyIDEA` > Click install and restart.
 * File > Settings > IvyIDEA > Ivy Settings set to "Use ivy default"
 * Right click on "ivy.xml" > IvyIDEA > Resolve for all modules > Open Project settings > Ivy Settings set to "Use ivy default"
 * Right click on "ivy.xml" > IvyIDEA > Resolve for authentication-api module 
    * You can ignore `org.glassfish.ha#ha-api;3.1.9:	Unrecognized artifact type: hk2-jar, will not add this as a dependency in IntelliJ.`
 * Push Build > `Rebuild Project` to compile and test if dependencies have been resolves


### If IvyIDEA plugin is INSTALLED ###
Install Ivy IDEA and configure
 * Right click on "ivy.xml" > IvyIDEA > Resolve for authentication-api module 
 * Push Build > `Rebuild Project` to compile and test if dependencies have been resolves
    
