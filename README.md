# RESTful ECommerce APIs.

## Functional services

Endpoints

Method	| Path	| Description	| User authenticated	
------------- | ------------------------- | ------------- |:-------------:|
POST	| /user/merchant	| Register new admin account (with admin role)	|  
POST	| /user/client	| Register new client account	(with user role)|   | 
GET	| /userProfile	| Get current logged in user information | × 

### Product Service
Manage products information and inventory
Endpoints

Method	| Path	| Description	| User authenticated	| Role
------------- | ------------------------- | ------------- |:-------------:| :-------------:|
GET	| /list?page={page}&pageSize={pageSize}	| Get all products	|  x | any |
GET	| /list/userId?page={page}&pageSize={pageSize}		| Get products created by merchant	|  x | merchant,admin |
POST| /save	| Create new product| x  | Admin |
PUT	| /save	| Update existing product| x  | Admin |
GET	| /list/available?page={page}&pageSize={pageSize}		| Get available products with inventory > 0 | × | any
GET	| /list/not-available?page={page}&pageSize={pageSize}		| Get available products with inventory = 0 | × | any

### Order Service
Manage products information and inventory
Endpoints

Method	| Path	| Description	| User authenticated	| Role
------------- | ------------------------- | ------------- |:-------------:| :-------------:|
GET	| /list/user?page={page}&pageSize={pageSize}		| Get logged in user orders	|  x | any |
GET	| /{orderId}	| Get order by id	|  x | any |
POST| /save	| Create new order| x  | User |
PUT	| /save	| Update existing order| x  | User |
