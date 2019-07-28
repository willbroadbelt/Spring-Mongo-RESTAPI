# Spring-Mongo-RESTAPI
Simple Spring-Boot REST api using MongDB 


## Project
Main Java source files are found in `src/main/java` and Java test files in `src/test/java`. Dependencies are added in `pom.xml`

---
This is a small [spring-boot](https://spring.io/projects/spring-boot) web server with user registration and login with a [MongoDB](https://www.mongodb.com/). They can enter a number of locations at registration so that when they log in they can retrieve the weather reports from these locations
using the [OpenWeatherMap API](https://openweathermap.org/).
Currently only the backend is implemented so using postman to interact with the server is necessary. Application mainly created for learning purposes.

----
## Running
1. Run a mongodb instance by running ```mongod --dbpath=your/path/here```
2. Build and run maven using ide -- or by running ```mvn package``` then `java -jar target/spring-boot-1.0-SNAPSHOT.jar`

Signup is done with a POST (/signup):
```
{
        "email": "thismail@email.com",
        "password": "YourPassword",
        "locations": ["London", "Liverpool,UK", "Paris,FR", "etc"]
}
```

Login with a POST (/login):
```
{
           "email": "thismail@email.com",
           "password": "FredickChilto"
}
```

Once logged in, the weathers are accessed by GET (/weather) and returns a string list:
```$xslt
[
    "City: London Current weather: few clouds Temperature: 17.62",
    "City: Liverpool Current weather: moderate rain Temperature: 14.2",
    "City: Paris Current weather: clear sky Temperature: 18.33"
]
```

----
## Testing
Run the test suite using running ```mvn test```


### Extenions
Creating a few basic html/js pages for the front-end.
