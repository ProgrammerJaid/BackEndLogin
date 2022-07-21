![image](https://user-images.githubusercontent.com/96864350/180274393-7ac763bd-e0d1-432a-9933-c7c2c8928b26.png)
# BackEndLogin

Login Api integrated with spring security and uses Java Mail Service.
Simply sends an email with link to activate account and the link expires in 15 mins.
This is a project in testing so it has hibernate ddl property as create.

The applications has many rest api, as follows:

To register user:`http://localhost:8080/api/v1/register`.It JSON format is as follows:

```
{
    "firstName":"user first name",
    "lastName":"user last name",
    "email":"user email address",
    "password":"password"
}
```

It returns a token that can be used to be sent as a key in postman for user verification or
a link will be provided to the email address given which will expire within 15 minutes.
Once user confirms their email address then their account is enabled and they can login.

Login can be done in your web browser by simply typing `http://localhost:8080/`.

Certain things to consider, configure your email by going into <strong>manage your gmail account->security</strong> 
there enable two step verification and create an app password that will be given in properties files as followed-  
&nbsp;     **spring.mail.password = created app password**.

Please disable any anti virus while sending email.

---
### Configuring MySql:

```
CREATE USER 'student-app'@'localhost' IDENTIFIED BY 'student-app';
GRANT ALL PRIVILEGES ON * . * TO 'student-app'@'localhost';
ALTER USER 'student-app'@'localhost' IDENTIFIED WITH mysql_native_password BY 'student-app';
create database studentapp;
use studentapp;
```

please provide the database location in application.properties in ***spring.datasource.url*** more details 
provided in application.properties of this code.

## Thank you for your time 😃.
