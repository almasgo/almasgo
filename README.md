Almasgo is an open-source first full text search engine with analytics. It is based on Java, MySQL and ElasticSearch.

## Getting Started
After service is up and running, these are the flow to start using Almasgo:
1. Register an Account
2. Add New Engine
3. Add Contents
4. Start /search -ing

### What is Engine, anyway ?
Basically engine will contain your contents. You are allowed to add multiple engines, however contents are isolated in a single engine.
In the future, engine will provide configuration to the search (score sensitivity, content schema, etc).

Engine has type sandbox and production, so by having multiple engines that also one way for managing multiple environments.

Note: docker image is still work in progress

## Register an Account
Register an account can be done through a POST Request to `/dashboard/v1/user` and provide JSON Body

```json
{
    "name" : "test",
    "email" : "abcd@domain.com",
    "password" : "12345678"
}
```

You will get response similiar to this:

```json
{
    "id": 1,
    "name": "test",
    "email": "abcd@domain.com"
}
```

After that, you should login to get JWT Token for next request.

Login can be done through `/dashboard/v1/auth` by providing email and password via JSON Body.

```json
{
    "email" : "abcd@domain.com",
    "password" : "12345678"
}
```

You will get response similiar to this:

```json
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWdfOiJ0ZXN0MkBnbWFpbC5jb20iLCJleHAiOjE2MDgwNDg4ODQsImlhdCI6MTYwODAzMDg4NH0.QCTMyv34c_hMRpIzdw8K2OcNyGCS6zK8soirnHOFxNNz_4HchR5hcxSdV76eFic3oah9VsftpBOojz6PIFbDQg"
}
```

## Add New Engine

Add an engine can be done through `/dashboard/v1/engine` 

```json
{
    "name" : "Awesome App",
    "type" : "sandbox"
}
```

Also put token (from /auth) as Authorization header.

`Authorization: Bearer tokenxxx`

If it successful, you will get similiar response to this:

```json
{
    "id": 5,
    "name": "Awesome App",
    "type": "sandbox",
    "apiKey": "90cf4eaeb31c8c4476926938be6be2e4681aa90c9520161195f383590b414841"
}
```

Engine id and apiKey will be used later in managing content and performing search.


## REST API
WIP
