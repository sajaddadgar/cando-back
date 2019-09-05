# Cando backend 
![cando_ultra_compressed](/uploads/e854683c72a7ebac8784aeea0ccbfa0c/cando_ultra_compressed.jpg)
## User API

### Create User
To create a new user, do `POST` on this URL:

`/user/create`

body of this request must contain these props:

1. name
2. email
3. password (must be hashed)
4. imageUrl

**use "default.png" for imageUrl property to get the default avatar.**

### Recover Password

To recover the user password, `POST` on this URL:

`/user/recover/{email}`

`{email}` is the user's email.

### Login 
To log in, do `POST` on this URL:

`/user/login`

body of this request must contain these props:

1. email
2. password

The password must be hashed cause of security issues. 

After successful login, the user's token would be returned in JSON, in succeeding described requests client must put this token in the header:

Header: `Authorization: Bearer TOKEN`

### Update User
In order to update user, do `PUT` on this url:

`/user/update`

body of this request must contain this props in JSON:

1. name
2. email
3. password
4. imageUrl

**each empty field assumed as unchanged.**

### Get user info

To get the user info, do `GET` on this URL:

`/user/info`

body of the response would contain these fields in JSON:
1. name
2. email
3. imageUrl


## Auction API

### Create new auction 
To create new Auction for a user, do `POST` on this URL:

`/auction/create`

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

the response body will contain these fields in JSON:

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

`/auction/categories`

the response body will contain an array of categories in JSON, each category will have an id and title.

### Get Homepage auctions

To get homepage cards, do `POST` on this URL:

`/auction/homepage`

body of this request must contain this props in JSON:

1. page
2. count
3. sort
4. categoryId
5. search

the page is a subject of the page in pagination request, **zero-based**.

count is count of Auctions in response. 

sort can have these values:

1. `TIME`
2. `MOST_BOOKMARKED`

**other values would be ignored.**

categoryId is Id that represented in `/auction/categories/`.

To filter by none, left the `search` field **empty**, else put your filter value.

the response body will contain an array of auction cards, each auction card would have this fields in JSON:


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

`/auction/myauctions`

To get  user bookmarked auction, do `GET` on this URL:

`/auction/mybookmarked`

### Bookmark an auction

To bookmark and auction, do `POST` on this URL:

`/auction/bookamark/{id}`

`{id}` is the auction id. 

## Image API
### Upload new avatar
To upload a new avatar, do `POST` on this URL:

`image/upload/avatar`

Afterward, the image new name would be returned in the response in JSON. 
**To change the user's avatar, the client must send an update request to the server and change the ImageUrl field to the new name**

### Download the avatar
To download the avatar, do `GET` on this URL:

`image/download/avatar/{name}`

`{name}` must be a file name with its format. 

### Upload new auction banner
To upload a new auction banner, do `POST` on this URL:

`image/upload/banner`

Afterward, the image new name would be returned in the response in JSON. 
**To change the auction banner, the client must send an update request to the server and change the ImageUrl field to the new name**

### Download the banner
To download the banner, do `GET` on this URL:

`image/download/banner/{name}`

`{name}` must be a file name with its format.


## Exceptions
Each error would be returned in JSON, the body would contain these props:

1. detail
2. code
3. stackTrace
4. suppressedExceptions

meanwhile, a unique customized HTML status code will be returned in HTML header.

### Html error codes interpretation

|Html error code | Name | interpretation | 
| :---:         | :---:    |  :---:    | 
| 400     | WrongArgumantException  | bad argument sent to server
| 409       | EmailAlreadyExistException   | a user with email exists on database
| 412       | ValidationException   | signup email not confirmed
| 404       | FileNotExistException   | requested file not exists on server
| 403       | DisabledException   | requested token for authentication is disabled
| 406       | BadCredentialsException   | credentials params are not proper
| 401       | ExpiredJwtException   | user's token is expired
| 404       | UsernameNotFoundException   | email not found in database
| 503       | MessagingException   | an error occured while sending email


## Websocket 
The auction's running would be cast on Websockets LIVE, to achieve this goal, you must do the following requests.

### Connect to websocket 

do `CONNECT` on `/websocket`, Header's token must not be forgotten and also put your exact token without Bearer prefix on the `PASSCODE`; the Stomp's request would be in this way:

```javascript
function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, "TOKEN",  function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
    });
}
```

### Subscribe on Auction State

do `SUBSCRIBE` on `/topic/state/{id}` to get the auction's state realtime, after subscribe and within each change that is made on auction, a new Message should be received by the client,
props of message are these field in JSON. 

1. auctionId
2. started
3. activeUserCount
4. finished
5. full

> if "finished" field becomes true, other fields except auctionId will become invalid.

> "full" and "activeUserCount" fields are only valid when an auction is running.


```javascript
function subscribe() {
    stompClient.subscribe('/topic/state/{id}', function (auctionState) {
        showAuctionState(auctionState);
    });
}
```

### Subscribe on Auction Run

do `SUBSCRIBE` on `/topic/auction/{id}` to be one of the bidders in the auction, to overcome race conditions an error message would be returned if Auction capacity becomes full before the subscription
after subscription, on each new bid and second, a new message would be sent to the client on the `/user/queue/reply`, the client must listen and subscribe on this URL too, this message would have this props in JSON.

1. remainedSeconds
2. maxBid
3. activeUserCount
4. finished

each bid greater than last max bid would be replaced before auction end, although remainedSeconds would be returned to default countdown time (30 seconds).

```javascript
function subscribe() {
    stompClient.subscribe('/topic/auction/{id}');

    stompClient.subscribe('/user/queue/reply', function (auctionStatus) {
        showAuctionStatus(JSON.parse(auctionStatus.body).maxBid , JSON.parse(auctionStatus.body).activeUserCount);
    });
```


### Bid
To bid, do `SEND` on the `/app/bid/{id}` with JSON as a message with this property. 

1. bid

```javascript
function sendBid() {
    stompClient.send("/app/bid/{id}", {}, JSON.stringify({'bid': $("#bid").val()}));
}
```

### Unsubscribe from Auction 

do `UNSUBSCRIBE` on the `/topic/auction/{id}` to leave the auction, **Note that any accidental or intentional leaving without unsubscription might have side effects.**

```javascript
function unsubscribe() {
    stompClient.unsubscribe('/topic/auction/2');
}
```
