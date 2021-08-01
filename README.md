#### "# Spring-project"

My project for combining all Spring features in one app.

There are 3 users with different roles, each has different permission. To access REST API you first need to log in. "admin" has the most permissions, so to test you should use this.

First, send the `POST` request to `localhost:8082/login` containing the body:
```JSON
{
"username" : "admin",
"password" : "admin"
}
```
After that, go to `HEADERS` section and grab Authorization token. This token starts with Bearer ... To implement the token, you can send `GET`, `POST`, `PUT` or `DELETE` requests.

## GET request
First, in Headers section of request you need to add new value

____ Key_________  | Value 

Authorization __ Bearer {token}
![GET_METHOD](https://user-images.githubusercontent.com/56115778/124316069-ac1e6480-db7d-11eb-9f22-c9ca168c0df4.png)

The link is `localhost:8082/management/api/locations/` also the `localhost:8082/api/locations/` works, but it needs to be fixed in some circumstances

After that you will be able to see the responce body.

## Post request

To implement Post method, you need to set body of request. The body consists of three elements:

```JSON
{
"longitude" : 0,
"latitude" : 0,
"typeid" : 0
}
```
0 are set by default
![POST_METHOD](https://user-images.githubusercontent.com/56115778/124316439-45e61180-db7e-11eb-9ac6-3f50829f27dd.png)


`http://localhost:8082/management/api/locations/`

## PUT request

To implement `PUT`, set the link as in `GET` and `POST` methods, but request body will slightly differ

```JSON
{
"id" : 0,
"longitude" : 0,
"latitude" : 0,
"typeid" : 0
}
```

![PUT_METHOD](https://user-images.githubusercontent.com/56115778/124316712-b2f9a700-db7e-11eb-88c7-3f94536a32a8.png)
If you specify wrong id it will get server error.

## DELETE method

There is no body there. You need to specify id to delete. Like in `PUT` method, if there is no such id, it will give you server error. I will think of dealing with that later

![изображение](https://user-images.githubusercontent.com/56115778/124317806-6c0cb100-db80-11eb-8a13-858b7bc7e349.png)

