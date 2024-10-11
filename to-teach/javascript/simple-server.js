var express = require('express');
var app = express();
app.listen(3001, '0.0.0.0');

app.get('/', function (req, res) {
  res.send('Hello World');
})
