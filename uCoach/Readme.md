##The Project



[Recent studies][13] performed with 11.320 Harvard Alumni showed that the physical activities that burn from 1000 to 2000 calories per week may reduce the risk of stroke for a normal person in 24%. The same study showed that in case of burning from 2000 to 3000 calories per week the risk of stroke risk is reduced in around 46%.

Just in the United States, every year strokes killed more than 150 000 people, not just strokes, but several different health problems related to the sedentary life make every day new victims.  Even with the available information, another [recent studies][14] showed that the number of sedentary people has increased on the past year. 

On this project our main goal was to boost our user’s motivation in order to get rid of sedentary life. What we discovered is that one key of motivation for exercising is [Goals Setting][15]. For helping our users with goal setting and health measurements control we developed uCoach.

Using uCoach, our user is able to set daily or date-fixed goals and store health measures, controlling if the goals were achieved or not.  

For that, we created goals based on the following measures and cases:
-	Weight (Gaining or loosing weight)
-	Steps (Walking x steps)
-	Calories (Spending x calories)
-	Running (Running x kilometers)
-	Walking (Walking x kilometers)
-	Cycling (Cycling x kilometers)
-	Sleeping (Sleeping x hours a day)

##Use Cases for Final User


As a user: 

* I can register
* I can login
* I can logout
* I can view my personal information
   email, name, birthdate, twitter username
* I can manually track my Health Measures
    weight, calories, steps, blood pressure
* I can connect my Google Fit account 
* I can view the history of my Health Measures
* I can set up personal Goals regarding a Health Measure Type
  * The goals can be frequent (daily frequency) or have a due date
* I can view my Goals
* I receive a motivational message when I register a new Measure that achieve a goal
* I can get a mention on a tweet congratulating me on a Goal achievement

##Project Structure

The basic structure of the Ucoach Client is according to the given diagram

![image](http://i.imgur.com/RLKMSeC.png)


The project is composed by the current repository and six other repositories. On each repository information about its endpoits, resources and basic functionality are given. 

* [Authentication API][6]
 * [See Wiki][7]
* [Internal Data Service][2]
 * [See Wiki][8]
* [Process Centric Service][4]
 * [See Wiki][9]
* [Business Logic Service][3]
 * [See Wiki][10]
* [Data Service][1]
 * [See Wiki][11]
* [External Data Service][5]
 * [See Wiki][12]


#ABOUT THE CLIENT

This repository sotores the client interface of the project, it is developed in Ruby and comunicates with the Business Logic and Process Centric using REST. 
One working instance of it is provided at Heroku: [http://ucoach-client.herokuapp.com/my_profile][16]



[1]: https://github.com/uCoach/data-service
[2]: https://github.com/uCoach/internal-data-service
[3]: https://github.com/uCoach/business-logic-service
[4]: https://github.com/uCoach/process-centric-service
[5]: https://github.com/uCoach/external-data-service
[6]: https://github.com/uCoach/authentication-api
[7]: https://github.com/uCoach/authentication-api/wiki
[8]: https://github.com/uCoach/internal-data-service/wiki
[9]: https://github.com/uCoach/process-centric-service/wiki
[10]: https://github.com/uCoach/business-logic-service/wiki
[11]: https://github.com/uCoach/data-service/wiki
[12]: https://github.com/uCoach/external-data-service/wiki
[13]: http://news.harvard.edu/gazette/1998/10.08/ExerciseCanRedu.html
[14]: http://www.medicaldaily.com/more-americans-lived-sedentary-lifestyle-2014-number-physically-active-hits-lowest-6-330788 
[15]: http://www.thestrengthandconditioningblog.com/2013/06/the-importance-of-having-fitness-goals.html
[16]: http://ucoach-client.herokuapp.com/my_profile
