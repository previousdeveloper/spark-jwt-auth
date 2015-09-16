JSON Web Token Authentication with SparkFramework 
==============

This is a [Spark](http://sparkjava.com/) based authentication server. The goal is to take care of
authentication/registration/ using a REST API.

## Installation
------------
Be sure install **them!**
- **Java 8**
- **Redis**
Redis run on default port
## How do I use it?
Install maven dependency and launch the **server**.

### Register (POST /register)
```java
 Parameters raw (json body)
{
    "username":      The email of the user 
    "password":   The password of the user
}
```

### Token (POST /token)
```java
 Parameters raw (json body)

{
    "username":      The email of the user 
    "password":   The password of the user
}
```

### Protected Message (POST /protected/deneme)
```java
 HEADER

{
    "X-API-TOKEN":      TOKEN 
}
```



