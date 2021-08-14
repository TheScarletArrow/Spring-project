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
![GET_METHOD](https://user-images.githubusercontent.com/56115778/127783679-0c0990d8-f809-4110-b92e-94e245929898.png)

The link is `localhost:8082/api/locations/` 
After that you will be able to see the responce body.

`GET` method is avaliable with operators:
![image](https://user-images.githubusercontent.com/56115778/127784141-384c1ecb-73c4-4705-b7ab-e694957f9f28.png)
using the link ``localhost:8082/api/operators/``


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
![POST_METHOD](https://user-images.githubusercontent.com/56115778/127783696-4baff80f-9ec0-463b-b676-9ce4f71d029c.png)


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

![PUT_METHOD](https://user-images.githubusercontent.com/56115778/127783710-2c9983f1-be5b-46ad-bf42-2d2d80b9fc08.png)

If you specify wrong id it will get server error.

## DELETE method

There is no body there. You need to specify id to delete. 

![image](https://user-images.githubusercontent.com/56115778/127783719-e0aa7a67-5e9d-4f73-ba6b-9c62b9fb5c93.png)

If this id exists, the response will be like that:

![image](https://user-images.githubusercontent.com/56115778/127783738-aeb6f347-a9b2-4f08-b5ed-95f956138be0.png)

overwise

![image](https://user-images.githubusercontent.com/56115778/127783747-437561ef-2b3e-4e76-a913-c38c5f85a670.png)


## Senging mails

To send mail via API you need to send `POST` request to ``http://localhost:8082/api/v1/mail/`` with the following body

```JSON
{
        "to":"to someone",
        "subject":"API subject",
        "body":"Hello this is body test API",
        "attachment":["F:/00-java.png", "F:/курсач ЭВМ.pdf", "C:/Users/Антон/Pictures/D7BxFvTXsAAtnYo.jpg"]
}
```
`attachment` can be blank or even missed, and no attachment will be sent with mail
![image](https://user-images.githubusercontent.com/56115778/127784187-75b254b2-8993-4461-9b9a-aacc1f1a5199.png)

