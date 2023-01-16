# loans
Simple Loan Simulation

## Introduction
This api is a simple loan simulation that exposes endpoints for doing the following tasks:
1) Create MSIDN contact;
2) Request loan using the contactid created
3) Repay loan using the loan id and contact id
4) Simulate sending messages to the loan users upon repayment and upon disbursement

## 1 Description:
- Summarized request paths and description for contact creation

| Method | Path             | Description                                            |
|--------|------------------|--------------------------------------------------------|
| GET    | /contacts        | Returns a paginated list of all the requested contacts |
| POST   | /contacts/create | Adds new contact                                       |
| GET    | /contacts/{id}   | Retrieves the full details of a single contact              |

- Summarized request paths and description for loan request and repayment

| Method | Path           | Description                                         |
|--------|----------------|-----------------------------------------------------|
| GET    | /loan          | Returns a paginated list of all the requested loans |
| POST   | /loan/disburse | Request loan                                        |
| GET    | /loan/{id}     | Retrieves the full details of a single loan         |
| POST   | /loan/repay       | Repay a loan                                        |
- Note
1) You can configure the database credentials in the properties file together with the loan clearance 
period
2) You can configure the sftp server credentials  in the properties config file

# Tools Used
1.Spring boot Java 17
2. Mysql 8
 The sql file is found inside the resources folder
# Postman Collection
https://interstellar-sunset-5393.postman.co/workspace/8735688b-cfe3-4586-966e-98d7e3c65c74/collection/4932219-1f993397-efb8-4409-b2bc-45b60f288cc6?action=share&creator=4932219





