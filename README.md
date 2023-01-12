
# Music Macros

A spring boot and react project for learng the respective technologies and making a simple easy-to-use UI.


## API Reference

#### Songs

```http
  GET /api/
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

```http
  GET /api/items/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### add(num1, num2)

Takes two numbers and returns the sum.


## Screenshots

![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)


## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

1. MongoDB: `DB_USERNAME`, `DB_PASSWORD`, `DB_URL`

```
Host a MongoDB instance (locally or remotely)
```



## Run Locally

Prerequisites:
- Java v11 or above
- maven 3.8.* or above
- IDE (like IntelliJ, VSCode, etc)

Clone the project

```bash
  git clone https://github.com/joshidipesh12/music_mac
```

Go to the project directory

```bash
  cd music_mac
```

Install dependencies

```bash
  mvn clean install
```

Start the server

```bash
  mvn spring-boot:run
```


## Features

- Existing DB containing 100 songs and 21 artists
- CRUD operations for both entities
- Pagination, Sorting & Filtering of Lists
- Semantic Glassmorphic design with NextUI


## Tech Stack

**Client:** React, Redux, NextUI

**Server:** Spring Boot, MongoDB


## Author

- [@joshidipesh12](https://www.github.com/joshidipesh12)


## License

[MIT](https://choosealicense.com/licenses/mit/)

