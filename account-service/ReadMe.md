## Account Service for Orchestration Saga

This service is a part of the Orchestration Saga

It provides the following end points for account related operations in account-service.

Fetch All accounts from the system.
`http://localhost:8084/getAll`

Search account from the system for given accountID
`http://localhost:8084/get?accountId=3`

Add new account
`curl -d '{"name":"testUser","balance":5000}' -H 'Content-Type: application/json' http://localhost:8084/add`
