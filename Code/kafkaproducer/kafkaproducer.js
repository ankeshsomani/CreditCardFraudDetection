var kafka = require('kafka-node'),
    Producer = kafka.Producer,
    client = new kafka.Client('localhost:2181'),
    producer = new Producer(client);
exports.sendTransactionToKafkaTopic = function(topicname, msgs) {
    payloads = [{
        topic: topicname,
        messages: msgs,
        partition: 0
    } ];

   // producer.on('ready', function() {
	//console.log('i m here '+payloads[0].messages.length);
	//console.log('i m also here '+payloads[0].messages[1]);
      producer.send(payloads, function(err, data) {
			if(!err){
				console.log(data);
			}
			else{
				console.log('some error here '+err);
			}
            
        });


    producer.on('error', function(err) {
        console.log('Error while sending data to kafka' + err);
    });
}