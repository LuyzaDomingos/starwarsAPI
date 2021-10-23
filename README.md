# :star::gun::princess: Star Wars  API

Star Wars REST API  - Planets

# :pencil2:About the Project

This project is about search, create, update and delete planets. And these planets have information like names, climate, terrain, how many residents live on that planet and how often that planet appears in Star Wars movies. And the application access an external API, and it is from there that it retrieves information about the number of residents and movies.

# :rocket: External API

The external API that the application accesses is [SWAPI](https://swapi.dev/). Which has information about planets, people, movies, starships, vehicles and species from the Star Wars universe. This project only uses information from the planets, as we can see [here](https://swapi.dev/api/planets/1/).

# :page_with_curl: Endpoints
The API provides the following endpoints:

 - ### GET /planets
**Description:** Gets **all** the planets stored in the database.  

- ### GET /planets/id/{id}
**Description:** Get the planet stored in the database by the **id**. 

- ### GET /planets/name/{name}
**Description:** Get the planet stored in the database by the **name**. 

- ### POST /planets
**Description:** Insert a planet on database.  
**Request body:** Json containing planet name, climate and terrain.  
Ex: 
```
{
  "name": "Coruscant",
  "climate": "temperate",
  "terrain": "cityscape, mountains"

}
```  
**Response Data:** Planet in JSON format with information about residents and films captured from the external API.
```
{
    "id": 3,
    "name": "Coruscant",
    "climate": "temperate",
    "terrain": "cityscape, mountains",
    "numberFilms": 4,
    "numberOfResidents": 3
}
```

- ### PUT /planets/{id}
**Description:** Update a planet stored in the database by the **ID**. 


- ### DEL /planets/{id}
**Description:** Delete a planet by the **ID**. 

- ### POST /auth
**Description:** Create an authentication token.
**Request body:** Json email and password.  
Ex: 
```
{
  "email": "test@email.com",
  "loginPassword": "1"

}
```  
**Response Data:** Token and the type of token in JSON format to have access to the endpoints of the API.
```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3dfvjdfjaoadjbjsiBXYXJzIiwicNDkyNjkwNywiZXhwIjoxNjM1MDEzMzA3fQ.OgWfR2Hn5EWLQ_ZRDpKeiVsT__QIbz1JSaPysOCNkCE",
    "type": "Bearer"
}
```

- ### GET /planets/swapi/{id}
**Description:** Get a specific planet from the external API by **ID**. 

**Response Data:** Planet in JSON format captured from external API.
```
{
    "name": "Coruscant",
    "climate": "temperate",
    "terrain": "cityscape, mountains",
    "residents": [
        "https://swapi.dev/api/people/34/",
        "https://swapi.dev/api/people/55/",
        "https://swapi.dev/api/people/74/"
    ],
    "films": [
        "https://swapi.dev/api/films/3/",
        "https://swapi.dev/api/films/4/",
        "https://swapi.dev/api/films/5/",
        "https://swapi.dev/api/films/6/"
    ],
    "filmsCount": 4,
    "residentsCount": 3
}
```


### Documentation
The api documentation was built using Swagger. To use the documentation at the address: http://localhost:8081/swagger-ui.html#/ it is necessary that the program is running.

# :hammer_and_pick: Tools and Tecnology
The project was developed with the following tools and technologies.
 - Spring Boot
 - MySQL
 - Swagger
 - Docker
 - Maven

# :computer::gear: SetUp/Run



