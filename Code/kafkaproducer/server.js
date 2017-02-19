var common = require("./commonscripts.js");
var Client = require('node-rest-client').Client;
var client = new Client();
var express = require('express');
var app = express();
var url=common.baseQueryUrl;
require('./addTransactions')(app,client);
var server = app.listen(8081, function () {

  var host = server.address().address
  var port = server.address().port

  console.log("kafkaproducer app listening at http://%s:%s", host, port)

})
