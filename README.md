# cicd-todo-app
A sample app used for learning GitHub actions, CI/CD, and TDD. The app exposes 4 REST endpoints for CRUD operations on Tasks

The CI currently builds and runs the tests when merging or pushing to `master`. If the tests pass Github Actions builds a docker image that is pushed to a dockerhub repository  (https://hub.docker.com/r/bguiang/cicd-todo-app).

**Get Tasks**
----
  Returns list of tasks

* **URL**

  /api/v1/tasks

* **Method:**
  
  `GET`
  
*  **URL Params**

   `N/A`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `[{ name : "Get groceries", id: 1}, { name : "Take out the trash", id: 2}, { name : "Relax", id: 3}]`
 
* **Error Response:**

  * **Code:** 404 <br />
    **Content:** `{ error : "Not Found" }`

**Add Task**
----
  Adds a task to the list. Note: IDs passed in the request body will be ignored.

* **URL**

  /api/v1/tasks

* **Method:**
  
  `POST`

* **Data Params**

  `{name: "task name"}`

* **Success Response:**

  * **Code:** 201<br />
 
* **Error Response:**

  * **Code:** 400 <br />
    **Content:** `{ error : "Bad Request" }`
    
  * **Code:** 404 <br />
    **Content:** `{ error : "Not Found" }`

**Update Task**
----
  Updates an existing task. Note: IDs passed in the request body will be ignored.

* **URL**

  `/api/v1/tasks/:id`

* **Method:**

  `PUT`

*  **URL Params**

   **Required:**
 
   `id=[integer]`
  
* **Data Params**
	**Required:**

  `{name: "task name"}`

* **Success Response:**

  * **Code:** 200 <br />
 
* **Error Response:**

  * **Code:** 400 <br />
    **Content:** `{ error : "Bad Request" }`
    
  * **Code:** 500 <br />
    **Content:** `{ error : "Internal Server Error" }`
    
**Delete Task**
----
  Deletes an existing task

* **URL**

  `/api/v1/tasks/:id`

* **Method:**

  `DELETE`

* **URL Params**

  `id=[Integer]`

* **Success Response:**

  * **Code:** 200 <br />
 
* **Error Response:**
    
  * **Code:** 500 <br />
    **Content:** `{ error : "Internal Server Error" }`
