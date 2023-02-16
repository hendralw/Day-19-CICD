# Day-18-AWS & Day-19-CICD

url api
-------------
https://product-service.procurement-capstone.site/api

swagger
-------------
https://product-service.procurement-capstone.site/swagger-ui/index.html

docker hub
-------------
https://hub.docker.com/repository/docker/hendralw/product-service/general

docker image
-------------
hendralw/product-service:latest

-------------

endpoint  | method | request body
------------- | ------------- | -------------
https://product-service.procurement-capstone.site/api/v1/products | POST | {"name":"Pen","category":"Office","stock":10,"user_id":"c1dcaf84-a3c7-11ed-a8fc-0242ac120002","is_deleted":false}
https://product-service.procurement-capstone.site/api/v1/products | GET | -
https://product-service.procurement-capstone.site/api/v1/products/:id | GET | -
https://product-service.procurement-capstone.site/api/v1/products | PATCH | {"id":"5c594c13-f775-4eca-afe7-90ae52cd6242","name":"wahyuuu","category":"Office","stock":10,"user_id":"c1dcaf84-a3c7-11ed-a8fc-0242ac120002","is_deleted":false}

![image](https://user-images.githubusercontent.com/49546149/219458678-9ad35cf0-b9a8-4282-9f17-bacd1824a68f.png)
