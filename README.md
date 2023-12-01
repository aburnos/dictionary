# Dictionary service
The Dictionary Management REST API service

# Prerequisites
* Java 17 (Amazon Corretto) (Check out https://sdkman.io for easy install and management)
* Maven (Check out https://sdkman.io for easy install and management)
* Docker https://docs.docker.com/install/
* Docker Compose https://docs.docker.com/compose/install/
* Docker Desktop (optional, but recommended, easily manage docker containers, applications, and images ) https://docs.docker.com/desktop/
* Your favorite IDE (IntelliJ IDEA ;=)

# Run preparation
### Step 1
You need to run Postgres database in docker, using following command:
```bash
docker-compose up -d
```
You can check that database is working using connection psql command:
```bash
psql -d postgresql://localhost:5432/dictionaries?user=test
``` 
or using Docker Desktop.

### Step 2 (optional)
Run `mvn clean install` just to check that's all ok (Tests should pass) and BUILD SUCCESS.

### Step 3
#### Run on development host environment:
Run `Service` configuration on IntelliJ IDEA or execute `mvn spring-boot:run` on command line.

#### Run using docker:
You need to build docker image, using the following command:
```bash
docker build -t dictionary .
```

and then start container, using the following command:
```bash
docker run --name dictionary -e "SPRING_PROFILES_ACTIVE=docker" -p 8080:8080 -t dictionary
```

### Step 4
#### OpenAPI swagger-ui
```
http://localhost:8080/swagger-ui/index.html
```

#### Create Dictionary: POST /api/dictionary
```
curl --location 'http://localhost:8080/api/dictionary' \
--header 'Content-Type: application/json' \
--data '{
    "description": "test",
    "items": [
        {
            "key": "test_key_1",
            "value": "test_value_1"
        },
        {
            "key": "test_key_2",
            "value": "test_value_2"
        }
    ]
}'
```

#### Get Dictionary: GET /api/dictionary/{id}
```
curl --location 'http://localhost:8080/api/dictionary/15'
```

#### Get All Dictionaries: GET /api/dictionaries
```
curl --location 'http://localhost:8080/api/dictionaries'
```

#### Delete Dictionary: DELETE /api/dictionary/{id}
```
curl --location --request DELETE 'http://localhost:8080/api/dictionary/15'
```

#### Update Dictionary: PUT /api/dictionary/{id}
```
curl --location --request PUT 'http://localhost:8080/api/dictionary/15' \
--header 'Content-Type: application/json' \
--data '{
    "description": "test",
    "items": [
        {
            "id": 16,
            "key": "test_key_111",
            "value": "test_value_111"
        },
        {
            "id": 17,
            "key": "test_key_222",
            "value": "test_value_222"
        }
    ]
}'
```

