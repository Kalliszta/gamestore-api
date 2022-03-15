# gamestore-api
A project I created to demonstrate skills I have learnt at my time at the 9 week Software Development Bootcamp offered by QA Ltd.

---

## Links
Jira Board: [gamestore-API](https://kalg.atlassian.net/jira/software/projects/GS/boards/2/roadmap?shared=&atlOrigin=eyJpIjoiZTAxNGQ5MTNkMWQyNDM5MDkzN2Y1ODA5ZGUwYjkzNDciLCJwIjoiaiJ9 "Link to gamestore-API project roadmap on Jira")

![gamestoreapi_2022-03-15_11 51am](https://user-images.githubusercontent.com/93586261/158372190-a17a8ed1-e0a0-46d1-9bf6-b96b9fde691f.png)

---

## Why are we doing this?

---

## How I expected the challenge to go.

---

## What went well? / What didn't go as planned?

---

## Possible improvements for future revisions of the project.
A possible way to improve this project is through implementing a user hierarchy system using Spring Boot Security. By implementing a user hierarchy, each user would be categorised either a NEWUSER, CUSTOMER or ADMIN. The create request in Accounts would be the only request accessible to a NEWUSER, which would enable them to create an account to become a CUSTOMER. To use the requests CUSTOMERs and ADMINs would need to login using their username and password (which are already stored in the accounts table when a new account is created). CUSTOMERs would be able to interact/modify data that is about them/associated with them from the accounts and orders tables through having authorisation to only a limited amount of requests. CUSTOMERs would only be able the use GET requests to interact with data stored stored in the games tables. Whereas ADMINs would be able to use any requests implemented to access data stored in any of the tables.

Currently the password of each user is stored as readable plain text which is bad practise. If I had more time I would have saved an encrypted version of each account's passwords, that would have been encrypted using a hashing algorithm and adding a set salt. By doing this, when any account that tries requesting data from the accounts table which includes the password of accounts, they would only be able to retrieve/see the unreadable hashed password from which they wouldn't be able to read/understand what the original/actual password is. A hashed password although cannot be reverted can be compared to a string someone enters as their password as the same hash & salt is applied to the inputted password as to the password used original password when the account was created, therefore if the two hashed passwords match (are identical) it means the original versions would match too, so the entered password is correct.

Another possible improvement would inlcude having a ratings table which would allow each account to review each game once. Using the ratings in the ratings table, in the games table there would be a total_rating field (with a DOUBLE data type) which would use the SQL query below to caluclate its value for each record.

```sql
SELECT (SUM(rating) / COUNT(rating)) AS total_rating FROM ratings RIGHT OUTER JOIN games ON ratings.fk_game_id = games.id GROUP BY games.id;
```
---

## Screenshots showing your postman requests and the output from the API.
All endpoints the program has are listed below under the HTTP request they use, each request starts with the URL 'http://localhost:8080/gamestore/' followed by one of the paths below (don't include brackets in the path they only specify what data to pass in for e.g. {id}):


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

## Database and data is being persisted.

In order to achieve data persistence I created/reset the gamestore database (when needed) outside the program in mySQL workbench using the following commands:
```sql
DROP DATABASE IF EXISTS gamestore;
CREATE DATABASE IF NOT EXISTS gamestore;
USE gamestore;
```

By creating the database outside the program it means that data could have been stored before the program ever existed or was run (as long as the table names and fields would have matched the ones the table names and fields that would have been created by the program once it was created/run). Data in my program persists because data is added to tables in the database created using mySQL workbench, this information is not lost once the program stops running as it is stored outside the program/API. In addition, the program itself does not drop the tables when its run instead when its run as the program uses the same models e.g. Games class to create the games table it means it can identify if the table alrady exists, if it does, it can continue interacting with it just like before when the program was first run/run before.

![data in database](https://user-images.githubusercontent.com/93586261/158425141-b362a168-be31-4aa2-9614-d042583fbf76.PNG)
![data in games](https://user-images.githubusercontent.com/93586261/158426679-39656305-f363-43ed-8b9f-e86c246150d6.PNG)



---

## Testing
All the tests, both unit tests & integration tests, I created for the gamestore API successfully passed. The tests overall exceed the required coverage of 80% (as stated by businesses) on the src/main/java, having an overall coverage of 86.7% (even with integration tests disabled with the @Disabled annotation).


>Image showing coverage with integration tests disabled:
>
>![Coverage (disabled integration tests) with drawings](https://user-images.githubusercontent.com/93586261/158376753-554da7f0-da17-4753-80ef-26c5a2a0f411.jpg)



>Image showing coverage with integration tests:
>
>![Coverage with drawings](https://user-images.githubusercontent.com/93586261/158376850-755d9798-c93b-4030-8e05-90b3562eb14c.jpg)
