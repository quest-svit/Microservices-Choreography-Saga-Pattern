## Inventory Service for Orchestration Saga

This service is a part of the Orchestration Saga

It provides the following end points for inventory related operations in inventory-service.


Search products from the Inventory for given productID
`http://localhost:8083/get?productId=2`


Fetch All products from the Inventory
`http://localhost:8083/getAll`

Add new Product
`curl -d '{"productId":6,"quantity":200,"productPrice":550}' -H 'Content-Type: application/json' http://localhost:8083/add`



