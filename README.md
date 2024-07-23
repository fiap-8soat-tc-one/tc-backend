# Tech Challenge Backend Api

Tech challenge pos tech fiap backend app part 1

# Team

- Myller Lobo
- Jean Carlos
- Caio Isikawa
- Vanderly
- Thiago

## Requirements

- Maven 3
- Java 17 (Open JDK 17)
- Postgres 15
- Docker Desktop
- Intellij IDEA
- DBeaver SQL Client
- Postman

## Instalattion

### Install and run postgres docker image:

```
docker pull postgres:15.3-alpine
docker run --name tech-challenge -p 5432:5432 -e POSTGRES_PASSWORD=123456 -d postgres:15.3-alpine

```

### After run postgres check database connection using a sql client recomend DBeaver:

![image](https://github.com/user-attachments/assets/d8e9f080-da41-424a-b865-1ad8c558946d)

### Open Intellij IDEA and add Jdk 17

Clone the project and import from branch main into Intellij IDEA
After go to File -> Project Structure

![image](https://github.com/user-attachments/assets/01d7aebb-4a17-42d2-ba97-85328cd7ea4d)

### Configure a Maven command

Intellij go to Run -> Edit Configuration and add a new maven configuration select java option jre Jdk 17 and run to
build the project

```
mvn clean package

```

![image](https://github.com/user-attachments/assets/d3509542-039e-40a8-b991-abfbd490d9fd)

![image](https://github.com/user-attachments/assets/aeebcb45-c371-40a3-ba9e-293e092df939)

### Run the app

To run the app go to Run -> Edit Configuration and add a new application config and run:
![image](https://github.com/user-attachments/assets/f7146723-222d-47fb-b2e7-bd1a25ee32db)

![image](https://github.com/user-attachments/assets/21757490-e659-43ad-9ddc-d2bcd33a3560)

### Verify Install

Check logs app into intellij console and DBeaver with the tables created

![image](https://github.com/user-attachments/assets/b2bcd347-9f95-4432-ace8-83be704b800d)

You can check the swagger api

```
http://localhost:8080/swagger-ui/index.html
```

![image](https://github.com/user-attachments/assets/f299e869-6080-431a-9c3f-2ab187117005)

### Test app

Using a postman is necessary to get a bearer jwt token and after to execute a category crud operations

Login app:

  ```
  curl --location 'http://localhost:8080/oauth/token' \
--header 'Authorization: Basic YW5ndWxhcjpAbmd1bEByMA==' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'username=myller' \
--data-urlencode 'password=12345678' \
--data-urlencode 'grant_type=password'

  ```

![image](https://github.com/user-attachments/assets/5a6574a1-ad07-4629-bc3b-ec4c6c1e3d7d)

Copy the "access_token" generated from the response and execute a list categories api to test the authentication:

```
curl --location 'http://localhost:8080/api/private/v1/categories' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer [access token]'

```

![image](https://github.com/user-attachments/assets/f766c3d8-c447-4166-bd12-5da86cae812f)












