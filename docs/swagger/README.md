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
