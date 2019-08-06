# Cando backend 
## User API

### Create User
To create a new user, do `POST` on this URL:

`/user/create/`

body of this request must contain these props:

1. name
2. email
3. password (must be hashed)
4. imageUrl


### Update User
In order to update user, do `PUT` on this url:

`/user/update/{id}`

`{id}` is userId.

body of this request must contain this props in JSON:

1. name
2. email
3. password


**each empty field assumed as unchanged.**

### Recover Password

To recover the user password, `POST` on this URL:

`/user/recover/{email}`

`{email}` is user's email.

body of this request must contain this props in JSON:

1. email




## Auction API

### Create new auction 
In order to create new Auction for a user, do `POST` on this url:

`/auction/create/{id}`

body of this request must contain these props:

1. title
2. description
3. basePrice
4. categoryId
5. dueDate
6. maxUsers
7. imageUrl

### Get auction details

In order to get an auction details, do `GET` on this url:

`/auction/info/{auctionId}`

`{auctionId}` is the id of the auction.

### Get categories

To get static categories, do `GET` on this URL:

`/auction/categories/`

### Get Homepage auctions

To get homepage cards, do `GET` on this URL:

`/auction/homepage/{id}`

`{id}` is userId.

body of this request must contain this props in JSON:

1. page
2. count
3. sort
4. categoryId
5. search


page is page in pagination request, **zero based**.

count is count of Auctions in response. 

sort can have these values:

1. `TIME`
2. `MOST_BOOKMARKED`

**other values would be ignored.**

categoryId is Id that represented in `/auction/categories/`.

To filter by none, left the `search` field **empty**, else put your filter value.


### Get user-created auctions

To get all user-created auctions, do `GET` on this URL:

`/auction/myauctions/{id}`

`{id}` is userId.

### Get user bookmarked auctions

To get  user bookmarked auction, do `GET` on this URL:

`/auction/mybookmarked/{id}`

`{id}` is userId. 


