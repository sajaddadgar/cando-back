# Cando backend 
## User API

### Create User
In order to create new user, do `POST` on this url:

`/user/create/`

body of this request must contains this props:

1. name
2. email
3. password (must be hashed)
4. image


### Update User
In order to update user, do `PUT` on this url:
`/user/update/{id}`

`{id}` is userId.

body of this request must contains this props in JSON:

1. name
2. email
3. password


**each empty field assumed as unchanged.**

### Recover Password

In order to recover user password, `POST` on this url:
`/user/recover/{id}`

`{id}` is userId.

body of this request must contains this props in JSON:

1. email

### Get Homepage auctions

In order to get homepage cards, do `GET` on this url:
`/user/homepage/{id}`

`{id}` is userId.

body of this request must contains this props in JSON:

1. page
2. count
3. sort
4. categoryId
5. search


page is page in pagination request, **zero based**.

count is count of Auctions in response. 

sort can has these values:

1. `TIME`
2. `MOST_BOOKMARKED`

**other values would be ignored.**

categoryId is Id that represented in `/auction/categories/`.

In order to filter by none, left the `search` field **empty**, else put your filter value.

### Get user created auctions

In order to get all user created auctions, do `GET` on this url:
`/user/myauctions/{id}`

`{id}` is userId.

### Get user bookmarked auctions

In order to get  user bookmarked auction, do `GET` on this url:
`/user/mybookmarked/{id}`

`{id}` is userId. 

## Auction API

### Create new auction 
In order to create new Auction for a user, do `POST` on this url:
`/auction/create/{id}`

body of this request must contains this props:

1. title
2. description
3. basePrice
4. categoryId
5. dueDate
6. maxUsers
7. image

### Get auction details

In order to get an auction details, do `GET` on this url:
`/auction/{auctionId}`

`{auctionId}` is id of the auction.

### Get categories

In order to get static categories, do `GET` on this url:
`/auction/categories/`



