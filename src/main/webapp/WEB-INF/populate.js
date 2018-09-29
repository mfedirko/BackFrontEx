var Request = require("request");

Request.post({
    "headers": { "content-type": "application/json" },
    "url": "http://localhost:8080/transaction/new",
    "body": JSON.stringify({
        "firstname": "Nic",
        "lastname": "Raboy"
    })
}, (error, response, body) => {
    if(error) {
        return console.dir(error);
    }
    console.dir(JSON.parse(body));
});
