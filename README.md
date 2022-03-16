# gamestore-api
A project I created to demonstrate skills I have learnt at my time at the 9 week Software Development Bootcamp offered by QA Ltd.

---

## Links
Jira Board: [gamestore-API](https://kalg.atlassian.net/jira/software/projects/GS/boards/2/roadmap?shared=&atlOrigin=eyJpIjoiZTAxNGQ5MTNkMWQyNDM5MDkzN2Y1ODA5ZGUwYjkzNDciLCJwIjoiaiJ9 "Link to gamestore-API project roadmap on Jira")

![gamestoreapi_2022-03-15_11 51am](https://user-images.githubusercontent.com/93586261/158372190-a17a8ed1-e0a0-46d1-9bf6-b96b9fde691f.png)

---

## Instructions to use API

### 1. Have the following software installed

| Software | Version |
|---|---|
| [Java](https://www.oracle.com/java/technologies/downloads/#java11) | 11 | 
| [Eclipse](https://www.eclipse.org/downloads/) | 4.22.0 |
| [mySQL Workbench](https://dev.mysql.com/downloads/windows/installer/8.0.html) | 8.0.28 |
| [POSTMAN](https://www.postman.com/downloads/)| 9.14.0 |
| [Spring Boot](https://spring.io/tools) | 2.6.4 |
| [Lombok](https://projectlombok.org/download) | 1.18.22 |

### 2. Create database

Run the following commands within mySQL workbench (WARNING if a database called gamestore already exists it will be dropped - deleted):

```sql
DROP DATABASE IF EXISTS gamestore;
CREATE DATABASE IF NOT EXISTS gamestore;
USE gamestore;
```
<p align="justify">
You only have to run the commands above once, before you ever run the program. By running the commands you create a database locally called gamestore which the program can then create the required tables within. You can also run the commands to reset the database and remove all the tables & data stored in it, however this is not recommended as the data deleted is lost forever.
</p>

### 2.1. (Optional - Modify code & create new .jar)

<details>
<summary>Click here for instructions</summary>
  
<p align="justify">
If you wish to modify the code you may, however you will be required to generate your own .jar file once you have finished modifying the code. 
</p> 

>2.1.1. Once you have made any the changes, in the package explorer you simply <code>right click on the project -> Run As -> Maven clean</code> to delete any files in the target folder 
>
>![maven clean](https://user-images.githubusercontent.com/93586261/158578084-5a477f55-c8f0-4392-9c03-1f0d778e062c.jpg)
  
>2.1.2. Next in the package explorer you have to <code>right click on the project -> Run As -> Maven install</code> to build the .jar file
>
>![maven install](https://user-images.githubusercontent.com/93586261/158578121-bdb5b9e2-7037-4c96-bb29-98a00a48d352.jpg)

>2.1.3. Refresh the package explorer
>
>![refresh](https://user-images.githubusercontent.com/93586261/158578188-0f71ef9e-4f59-4577-9913-62bea8e428da.jpg)

  
>2.1.4. You should find the executable .jar in the target folder
>
>![target folder](https://user-images.githubusercontent.com/93586261/158579059-f4b51001-7264-4be8-a772-7e56f066907f.jpg)

</details>

### 3. (Use .jar file given)

<p align="justify">
To use the .jar file you open the command prompt, navigate to the root of the project then use the command then in the format <code>java -jar filename.jar</code> you run:
</p>

```
java -jar GameStore-0.0.1-SNAPSHOT.jar
```

To stop the program simply press `Ctrl + C`

---

## Why did I do this / what is the project's purpose?
<p align="justify">
My project purpose is to act as a backend for a website owned by a company who sells video games online. As part of my project I was required to handle requests but I wasn't required to create an actual frontend, as a result I used POSTMAN to act as the frontend. My program is an API that can recieve HTTP requests and deal with them in a way you would expected it to. For example a user of the program could request to see all the games with the genre 'MMORPG' using the specific endpoint which role is to deal with that request, the program would successfully retrieve the required information from the database (so all games with the MMORPG genre) and return them (in a list) to POSTMAN which would output them in a JSON format.
</p>

---

## How I expected the challenge to go
<p align="justify">
Initially I overestimated the amount of time I had and underestimated the workload. 1 week and 3 days is not a lot to create a complex API with user hierarchies, which is why I had to scrap that feature as despite it have logical use in my program it was not a required feature for my project to have.
</p>

---

## What went well? / What didn't go as planned?
<p align="justify">
Before I did this project, I had previous experience with working with joined tabled in 'Visual Basic .NET' therefore I assumed due to my experiance I would quickly figure out how to join tables in Java/Spring Boot. I can accross a number of errors some of which were due to typos in my code, extra annotations such as <code>@JsonManagedReference</code> in the parent classes, missing annotations such as <code>@JsonBackReference</code> in the child classes, etc but the most complex issue I ran into was the one caused by Lombok. The issue caused by Lombok caused me to waste a few hours scratching my head, the issue it caused threw the error message <code>"Content type 'application/json;charset=UTF-8' not supported"</code> whenever I tried using an endpoint that used a POST request. As I eventually worked out the issue was being caused by Lombok e.g. by using the @AllArgsConstructor annotation Lombok was not just awaiting the variables in the class (e.g. in the Games class) to create object (a game) but also awaiting the join table variables as arguments. Once I made the all arguments contructors and contructors that take in specific values myself, I succesfully managed to join tables using the following format: 
</p>

Code in parent classes:
``` java
@OneToMany(mappedBy = "games", fetch = FetchType.LAZY)
@OnDelete(action = OnDeleteAction.CASCADE) //if deleted so are its children
private List<GamePlatforms> gamePlatforms;
```
Code in child classes:
``` java
@JsonBackReference(value = "games")
@ManyToOne(targetEntity = Games.class, fetch = FetchType.LAZY)
@JoinColumn(name="fk_games_id")
private Games games;
```
<br />

<p align="justify">
As I have had practise creating custom queries before in interfaces that extend the JPARepository in other projects, I could easily create custom queries for this project. As I am familiar with SQL and not very familiar with JPQL (which JPARepository uses by default), I had o first to specify for all my custom queries to be read as native queires (SQL queries) and then I could write the query/command itself in SQL.
</p>

``` java
@Query(value = "SELECT * FROM games LEFT OUTER JOIN game_platforms ON games.id=game_platforms.fk_games_id LEFT OUTER JOIN platforms ON platforms.id=game_platforms.fk_platforms_id WHERE platforms.id = :inputId", nativeQuery = true)
public List<Games> getGamesByPlatformId(@Param("inputId") Long inputId);
```

---

## Possible improvements for future revisions of the project.
<p align="justify">
A possible way to improve this project is through implementing a user hierarchy system using Spring Boot Security. By implementing a user hierarchy, each user would be categorised either a NEWUSER, CUSTOMER or ADMIN. The create request in Accounts would be the only request accessible to a NEWUSER, which would enable them to create an account to become a CUSTOMER. To use the requests CUSTOMERs and ADMINs would need to login using their username and password (which are already stored in the accounts table when a new account is created). CUSTOMERs would be able to interact/modify data that is about them/associated with them from the accounts and orders tables through having authorisation to only a limited amount of requests. CUSTOMERs would only be able the use GET requests to interact with data stored stored in the games tables. Whereas ADMINs would be able to use any requests implemented to access data stored in any of the tables.
</p>

<p align="justify">
Currently the password of each user is stored as readable plain text which is bad practise. If I had more time I would have saved an encrypted version of each account's passwords, that would have been encrypted using a hashing algorithm and adding a set salt. By doing this, when any account that tries requesting data from the accounts table which includes the password of accounts, they would only be able to retrieve/see the unreadable hashed password from which they wouldn't be able to read/understand what the original/actual password is. A hashed password although cannot be reverted can be compared to a string someone enters as their password as the same hash & salt is applied to the inputted password as to the password used original password when the account was created, therefore if the two hashed passwords match (are identical) it means the original versions would match too, so the entered password is correct.
</p>

<p align="justify">
Another possible improvement would inlcude having a ratings table which would allow each account to review each game once. Using the ratings in the ratings table, in the games table there would be a total_rating field (with a DOUBLE data type) which would use the SQL query below to caluclate its value for each record.
</p>
  
```sql
SELECT (SUM(rating) / COUNT(rating)) AS total_rating FROM ratings RIGHT OUTER JOIN games ON ratings.fk_game_id = games.id GROUP BY games.id;
```
<br />

>[Link to all stories/features of Jira that have been labelled as 'Won't' as they won't be a feature](https://kalg.atlassian.net/jira/software/projects/GS/boards/2/roadmap?label=Won%27t&selectedIssue=GS-15&shared=&atlOrigin=eyJpIjoiODJmZjllNWZjY2ZkNDMxZjk3OWUyY2JlOTQ3ZGMxMTQiLCJwIjoiaiJ9)
>
>![gamestoreapi_2022-03-15_01 10pm](https://user-images.githubusercontent.com/93586261/158441868-bae5f222-768a-4719-91ba-eb205a114e25.png)

<br />

---

## List of all endpoints
<p align="justify">
All endpoints the program has are listed below under the HTTP request they use, each request starts with the URL <strong> <code>'http://localhost:8080/gamestore/'</code> </strong> followed by one of the paths below:
</p>

| **POST** |
|---|
| accounts/create |
| games/create |
| games/add/platform |
| games/add/genre |
| platforms/create |
| genres/create |
| orders/create |
| orders/add |

<br />

| **GET** | **Input type** |
|---|---|
| accounts/read/all |
| accounts/read/{id} | *Long (account id)* |
| games/read/all
| games/read/{id} | *Long (game id)* |
| games/read/name/{name} | *String (game name)* |
| games/read/age/{age} | *Integer (game age rating)* |
| games/read/cost/{cost} | *Double (game cost)* |
| games/read/online/{isOnline} | *Boolean (whether the game is an online game)* |
| games/read/platform/{id} | *Long (platform id)* |
| games/read/genre/{id} | *Long (genre id)* |
| orders/read/{id}/items | *Long (order id)* |
| platforms/read/all
| platforms/read/{id} | *Long (platform id)* |
| genres/read/all
| genres/read/{id} | *Long (genre id)* ||
| orders/read/all
| orders/read/{id} | *Long (orders id)* |

<br />

| **PUT** | **Input type** |
|---|---|
| accounts/update/{id} | *Long (account id)* |
| games/update/{id} | *Long (game id)* |
| platforms/update/{id} | *Long (platform id)* |
| genres/update/{id} | *Long (genre id)* |
| orders/update/{id} | *Long (order id)* |

<br />

| **DELETE** | **Input type** |
|---|---|
| accounts/remove/{id} | *Long (account id)* |
| games/remove/{id} | *Long (game id)* |
| platforms/remove/{id} | *Long (platform id)* |
| genres/remove/{id} | *Long (genre id)* |
| orders/remove/{id} | *Long (order id)* |

---

## Some examples of how to use POSTMAN and endpoints

>Endpoint '../accounts/create' in POSTMAN with an exmaple of a JSON body to pass through to create an account
>
>![POSTMAN create example](https://user-images.githubusercontent.com/93586261/158430632-f9e9e1bc-3ccc-4c96-8c0f-bc7426187caa.jpg)

>Endpoint '../accounts/read/all' in POSTMAN with an exmaple of what JSON to expect back
>
>![POSTMAN read-all example](https://user-images.githubusercontent.com/93586261/158433447-8be1bb9d-53fd-46c2-b225-eae9ea6f3417.jpg)

>Endpoint '../accounts/read/1' in POSTMAN with an exmaple of what JSON to expect back
>
>![POSTMAN read-id example](https://user-images.githubusercontent.com/93586261/158433484-1c32a749-3ed5-4138-8a95-fff698e55dac.jpg)

>Endpoint '../accounts/update/1' in POSTMAN with an exmaple of a JSON body to pass through to update an account
>
>![POSTMAN update example](https://user-images.githubusercontent.com/93586261/158433525-d0b16b12-1e91-4100-9998-c5aeac29a094.jpg)

>Endpoint '../accounts/remove/1' in POSTMAN with an exmaple of what to expect if account is succesfully deleted
>
>![POSTMAN remove example](https://user-images.githubusercontent.com/93586261/158433565-75dc3d09-52d4-49eb-8673-9bf7fe89d2d7.jpg)

---

## Database and persisted data

In order to achieve data persistence I created/reset the gamestore database (when needed) outside the program in mySQL workbench using the following commands:
```sql
DROP DATABASE IF EXISTS gamestore;
CREATE DATABASE IF NOT EXISTS gamestore;
USE gamestore;
```

<p align="justify">
By creating the database outside the program it means that data could have been stored before the program ever existed or was run (as long as the table names and fields would have matched the ones the table names and fields that would have been created by the program once it was created/run). Data in my program persists because data is added to tables in the database created using mySQL workbench, this information is not lost once the program stops running as it is stored outside the program/API. In addition, the program itself does not drop the tables when its run instead when its run as the program uses the same models e.g. Games class to create the games table it means it can identify if the table alrady exists, if it does, it can continue interacting with it just like before when the program was first run/run before.
</p>
  
>Running the command `SELECT * FROM accounts;` to show that data still exists in the accounts table:
>
>![data in database](https://user-images.githubusercontent.com/93586261/158425141-b362a168-be31-4aa2-9614-d042583fbf76.PNG)

>Running the command `SELECT * FROM games;` to show that data still exists in the games table:
>
>![data in games](https://user-images.githubusercontent.com/93586261/158426679-39656305-f363-43ed-8b9f-e86c246150d6.PNG)

---

## Testing
<p align="justify">
All the tests, both unit tests & integration tests, I created for the gamestore API successfully passed. The tests overall exceed the required coverage of 80% (as stated by businesses) on the src/main/java, having an overall coverage of 86.3% (even with integration tests disabled with the @Disabled annotation and lombok bolierplate excluded from tests).
</p>

>Image showing coverage of 86.3% with integration tests disabled:
>
>![TestCoverage (disabled integration tests)](https://user-images.githubusercontent.com/93586261/158567221-ca56776b-635a-41d3-93b9-9ee283cc95d6.jpg)

>Image showing coverage of 86.3% with integration tests:
>
>![TestCoverage](https://user-images.githubusercontent.com/93586261/158567260-bd1749b2-a5b8-4bae-bcfa-942d3f66e568.jpg)
