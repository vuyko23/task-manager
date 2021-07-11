## Task List App
RESTful application for managing Task list. All request and response in JSON format. To run application you need to configure Tomcat(my version was 9.0.6) and  API Collaboration Tools for test. I used Postman.

## User can:
- Register
- Create Task
- Update Task
- Complete Task
- Find all Tasks
- Find all uncompleted Tasks
- Find all completed Tasks
- Delete Task

## How to test by  API Collaboration Tools:
- To register new User send request to /register using Http.POST method  with body like this {"login":"user","password":"1234","repeatPassword":"1234"}
  Example - https://prnt.sc/1ab5xd4
- Next step you need to enable authentication by choosing authentication tab and set type to basic auth. Then enter your login and password
  Example - https://prnt.sc/1ab85mp
- To create Task send request to /tasks using Http.POST method with body like this {"description":"Need to fix my computer"}
- To get All Tasks send request to /tasks using Http.GET method
- To make Task completed send request to /tasks/complete/{taskId} using Http.POST method, body can be empty
- To update Task send request to /tasks/{taskId} using Http.PUT method with body like this {"description":"Need to fix my computer","completed":true}
- To delete Task send request to /tasks/{taskId} using Http.DELETE, body can be empty
- To get all completed Tasks send request to /tasks/completed using Http.GET
- To get all uncompleted Tasks send request to /tasks/uncompleted using Http.GET

## Technologies
- Spring(Web, Security, MVC)
- Tomcat
- Lombok
- H2 DataBase
- Jackson
- Logger
- Maven
