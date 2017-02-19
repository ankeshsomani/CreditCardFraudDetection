
var kafka = require('kafka-node'),
    Producer = kafka.Producer,
    client = new kafka.Client('localhost:2181'),
    producer = new Producer(client);

	payloads = [
        { topic: 'test2', messages: 'This is the First Message I am sending', partition: 0 },
    ];

producer.on('ready', function(){
	producer.send(payloads, function(err, data){
		console.log(data)
	});
});

producer.on('error', function(err){})