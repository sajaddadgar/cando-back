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

**use "default.png" for imageUrl property to get the default avatar.**

### Recover Password

To recover the user password, `POST` on this URL:

`/user/recover/{email}`

`{email}` is user's email.

### Login 
To log in, do `POST` on this URL:

`/user/login`

body of this request must contin these props:

1. email
2. password

The password must be hashed cause of security issues. 

After successfull login, the user's token would be returned in JSON, in further described requests client must put this token in the header:

Header: `Authorization: Bearer TOKEN`

### Update User
In order to update user, do `PUT` on this url:

`/user/update/`

body of this request must contain this props in JSON:

1. name
2. email
3. password
4. imageUrl

**each empty field assumed as unchanged.**

### Get user info

To get the user info, do `GET` on this URL:

`/user/info/`

body of the response would contain this fields in JSON:
1. name
2. email
3. imageUrl


## Auction API

### Create new auction 
In order to create new Auction for a user, do `POST` on this url:

`/auction/create/`

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

the response body will contain this fields in JSON:

1. title
2. description
3. categoryId
4. categoryTitle
5. imageUrl
6. dueDate
7. remainedTime
8. maxUsers

### Get categories

To get static categories, do `GET` on this URL:

`/auction/categories/`

the response body will contain array of categories in JSON, each category will have an id and title.

### Get Homepage auctions

To get homepage cards, do `POST` on this URL:

`/auction/homepage/`

body of this request must contain this props in JSON:

1. page
2. count
3. sort
4. categoryId
5. search

page is matter of page in pagination request, **zero based**.

count is count of Auctions in response. 

sort can have these values:

1. `TIME`
2. `MOST_BOOKMARKED`

**other values would be ignored.**

categoryId is Id that represented in `/auction/categories/`.

To filter by none, left the `search` field **empty**, else put your filter value.

the response body will contain array of auction cards, each auction card would have this fields in JSON:

1. id
2. title
3. description
4. basePrice
5. categoryId
6. categoryTitle
7. dueDate
8. maxUsers
9. remainedUsers
10. remainedTime
11. imageUrl
12. bookmarked
13. enabled
14. started


### Get user-created auctions

To get all user-created auctions, do `GET` on this URL:

`/auction/myauctions/`

### Get user bookmarked auctions

To get  user bookmarked auction, do `GET` on this URL:

`/auction/mybookmarked/`


