# Event Pro

Event Pro is a web based application that can be used by an event venue to
broadcast event data and sell tickets.
It serves as an online event management website serving the functionality
of an event manager. The proposed website allows user to select from different
event types such as arts and sports, after which the system allows the user to
select and buy tickets for the event.

# Run the App on Local Machine
### Prerequisites
- AWS IAM credentials
- Local MySQL server running on port 3306 (default)
- `Lombok` plugin is installed in your favorite IDE  
- Optional but preferred to have: `google-java-format` plugin with auto saved enabled

### Steps
- Start by creating an `application-local.yaml` file in `./src/main/resources` folder. This file
is gitignored to help avoid checking your credentials to GitHub
- Copy the content of `application.yaml` to `application-local.yaml` and fill in this local file 
your MySQL username, password and your AWS credentials (access, secret, bucket name).
Also the `spring.profiles.active` value should only be `local`
- To start the app from IDE, build and run `EventProApplication.java` with below VM arg  
`-Dspring.profiles.active=local`
    - Or simply with Maven wrapper  
`./mvnw spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=local`

Explore and test available APIs via Swagger documentation  
http://localhost:9000/swagger-ui.html

To actually make authenticated request, use Postman as below

## Access Protected API with Postman 
Download [Postman](https://www.getpostman.com) and import below collection for a list of available APIs  
[Postman Collection](https://www.getpostman.com/collections/02549973295799e83bff)

- Add an environment variable with key=`protocol` and value=`http`
- Add an environment variable with key=`host` and value=`localhost:9000`

See the authorization flow below on how you can access the API. Typically you only need to 
signup/authenticate once and use the generated token to make subsequent requests.

## Authorization Flow
![Auth Image](./src/main/resources/AuthDiagram.png)
 
