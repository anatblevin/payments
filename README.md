# payments
demo 


Run the mySql docker first:  
    docker start some-mysql
    docker stop some-mysql
    docker rm some-mysql


Verify the docker is running by running "docker ps"

The results should be something like that: 
f311ab2381d0   mysql:latest   "mysqld"   2 weeks ago   Up 3 seconds   3306/tcp   some-mysql 

In  IDE, Run the service 
Build and Run a sSpring Boot Application 
main is: com.example.payments.MyServiceApplication 

In the browser, go to http://localhost:8080/ping

This call, calls highnote API, just to get a response back.  