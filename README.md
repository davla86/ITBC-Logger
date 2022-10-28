# ITBC-Logger
This is application that enables registered users to store information and search for it using various filters.

Technologies (Java oracle 19, MS SQL Server, Spring Boot (2.7.4), OpenAPI)

This is how I imagine the application works.
ClientControllor has following methods:
Get all clients;
Get clients by name;
Get clients by id;
Get clients logs;
Registration clients;
Login clients;
Update clients information.

LogControllor has following methods:
Get all logs;
Get logs by name;
Get logs by id;
Get message of logs;
Creation logs.

HTTP Method: POST
Endpoint URL: /api/clients/register
Request body:
{
    "username": "string",
    "password": "string",
    "email": "string"
}
Responses:
201 - Registered
400 - Bad Request
email must be valid
username at least 3 characters
password at least 8 characters and one letter and one number
409 - Conflict
username already exists
email already exists
Login

HTTP Method: POST
Endpoint URL: /api/clients/login
Request body:
{ 
    "account": "string", // email or username 
    "password": "string" 
} 
Responses:
200 - OK
400 - Bad Request
Email/Username or password incorrect

HTTP Method: POST
Endpoint URL: /api/logs/create
Request body:
{ 
    "message": "string", 
    "logType": 0 
} 
Request headers:
Authorization - token
Responses:
201 - Created
400 - Bad Request
Incorrect logType
401 - Unauthorized
Incorrect token
413 - Payload too large
Message should be less than 1024
