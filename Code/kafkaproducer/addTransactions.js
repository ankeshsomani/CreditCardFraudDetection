module.exports = function(app, client) {
    var common = require("./commonscripts.js");
    var bodyParser = require('body-parser');
var kafkaproducer=require('./kafkaproducer.js');
    app.use(bodyParser.json()); // to support JSON-encoded bodies
    app.use(bodyParser.urlencoded({ // to support URL-encoded bodies
        extended: true
    }));

    var path = common.addTransactions;
    var insertTransaction = function(transactionData, response, callback) {


        console.log("Inserting a transaction with transaction id as" + transactionData[0].transactionId);
		var msgs=[];
		for(var  x=0; x<transactionData.length;x++){
			var message=transactionData[x].accountId+","+transactionData[x].transactionId+","+transactionData[x].posid+","+transactionData[x].transactionDate+","+transactionData[x].description+","+transactionData[x].amount;
			msgs[x]=message;
		}
		//var msgs=transactionData[0].transactionId;
        kafkaproducer.sendTransactionToKafkaTopic('testing1',msgs);
		
        response.json('"success":');

    };
    app.use(function(req, res, next) {

        // Website you wish to allow to connect
        res.setHeader('Access-Control-Allow-Origin', '*');

        // Request methods you wish to allow
        res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');

        // Request headers you wish to allow
        res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');

        // Set to true if you need the website to include cookies in the requests sent
        // to the API (e.g. in case you use sessions)
        res.setHeader('Access-Control-Allow-Credentials', true);

        // Pass to next layer of middleware
        next();
    });
    app.post(path, function(req, res, next) {
        var transactions = req.body.transactions;
        res.setHeader('Access-Control-Allow-Origin', '*');
        res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        var query = "";
        var validated = false;
        var mandatoryAttributeMessage = "Please send transaction/s to be inserted";
        if ((typeof transactions !== "undefined") && (transactions !== null)) {
            validated = true;
        } else {
            res.send(mandatoryAttributeMessage);
        }
        //};
        if (validated) {
			console.log('hello');
            insertTransaction(transactions, res, function() {
                console.log('I am a callback function');
            });
        }

        req.on('requestTimeout', function(req) {
            console.log('request has expired');
            res.send('request has expired');
            req.abort();
        });

        req.on('responseTimeout', function(res) {
            console.log('response has expired');
            res.send('response has expired');
        });

        //it's usefull to handle request errors to avoid, for example, socket hang up errors on request timeouts 
        req.on('error', function(err) {
            console.log('request error', err);
            res.send(err);
        });

    });
}