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


*POST:*
- accounts/create
- games/create
- games/add/platform
- games/add/genre
- platforms/create
- genres/create
- orders/create
- orders/add

<br />

*GET:*
  - accounts/read/all
  - accounts/read/{id} *(account id)*
  - games/read/all
  - games/read/{id} &emsp; *(game id)*
  - games/read/name/{name} &emsp;*(game name, string)*
  - games/read/age/{age} &emsp; *(game age rating, int, returns all less than or equal)*
  - games/read/cost/{cost} &emsp; *(game cost, double, returns all less than or equal)*
  - games/read/online/{isOnline} &emsp; *(whether the game is an online game, boolean - true or false)*
  - games/read/platform/{id} &emsp; *(platform id, returns all games that have the platform with the specified id)*
  - games/read/genre/{id} &emsp; *(genre id, returns all games that have the genre with the specified id)*
  - orders/read/{id}/items &emsp; *(order id, returns all games in the order with the specificed id)*
  - platforms/read/all
  - platforms/read/{id}
  - genres/read/all
  - genres/read/{id}
  - orders/read/all
  - orders/read/{id}

<br />

*PUT:*
  - accounts/update/{id}
  - games/update/{id}
  - platforms/update/{id}
  - genres/update/{id}
  - orders/update/{id}

<br />

*DELETE:*
  - accounts/remove/{id}
  - games/remove/{id}
  - platforms/remove/{id}
  - genres/remove/{id}
  - orders/remove/{id}

Bodies
---

## Screenshots of your database to prove that data is being persisted.

```sql
DROP DATABASE IF EXISTS gamestore;
CREATE DATABASE IF NOT EXISTS gamestore;
USE gamestore;
```
---

## Testing
All the tests, both unit tests & integration tests, I created for the gamestore API successfully passed. The tests overall exceed the required coverage of 80% (as stated by businesses) on the src/main/java, having an overall coverage of 86.7% (even with integration tests disabled with the @Disabled annotation).


>Image showing coverage with integration tests disabled:
>
>![Coverage (disabled integration tests) with drawings](https://user-images.githubusercontent.com/93586261/158376753-554da7f0-da17-4753-80ef-26c5a2a0f411.jpg)



>Image showing coverage with integration tests:
>
>![Coverage with drawings](https://user-images.githubusercontent.com/93586261/158376850-755d9798-c93b-4030-8e05-90b3562eb14c.jpg)
