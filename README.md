# CreditCardFraudDetection
Real time credit Card fraud Detection

Deployment Guide for Fraud detection POC:-

1. Check if zookeeper is running.
	ps -ef|grep zookeeper
	If not start zookeeper service.

2. check if Redis server/service is running
	ps -ef|grep redis
	
	if not restart it using below command
	sudo service redis_6379 status
	sudo service redis_6379 start
	sudo service redis_6379 stop
	
	set redis password as masteklabs if not set earlier
	root@ip-172-31-26-117:/data/masteklabs/FraudDetectionCode/mysqlredis# redis-cli
	127.0.0.1:6379> CONFIG set requirepass "masteklabs"
	OK
	
	
	https://www.digitalocean.com/community/tutorials/how-to-install-and-use-redis

3. Check if MySQL server/service is running.
	ps -ef|grep mysql
	
	connect to mysql instance using the below command
	mysql -p<<password>>

4. check if hadoop namenode,datanodes are running
	ps -ef|grep hadoop
	jps
	
	cd /usr/local/hadoop/bin/
	Make sure the folders inside /usr/local/hadoop_store/hdfs/datanode/ is deleted.
	rm -rf /usr/local/hadoop_store/hdfs/datanode/
	/usr/local/hadoop/bin/hadoop namenode -format
	/usr/local/hadoop/sbin/start-all.sh

	check using jps
	you should see Namenode,Datanode,nodemanager,resourcemanager,secondarynamenode up and running
	
5. check if Kafka broker is running
	ps -ef|grep kafka
	
	if not start kafka broker as below
	PS1="\[\e]0;KafkaBroker\a\]${debian_chroot:+($debian_chroot)}\u@\h:\w\$ "
	/usr/local/kafka/bin/kafka-server-start.sh -daemon /usr/local/kafka/config/server.properties
	
9.	Run MySQL Redis program to prepopualte the data.	

	if not start the module as below
	PS1="\[\e]0;mysqlredis\a\]${debian_chroot:+($debian_chroot)}\u@\h:\w\$ "
	java -cp /data/masteklabs/FraudDetectionCode/mysqlredis/target/mysqlredis-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.masteklabs.mysqlredis.test.Application
	
	connect to redis using redic-cli and validate the data population.
	
8. Check if transaction generator module is running
	ps -ef|grep server.js
	
	if not start the transaction generator module
	PS1="\[\e]0;TransactionGenerator\a\]${debian_chroot:+($debian_chroot)}\u@\h:\w\$ "
	cd /data/masteklabs/FraudDetectionCode/kafkaproducer/
	nohup node server.js &

7. 	Check if alert module is running
	ps -ef|grep alert

	if not start AlertModule as below
	PS1="\[\e]0;AlertProviderModule\a\]${debian_chroot:+($debian_chroot)}\u@\h:\w\$ "
	java -cp /data/masteklabs/FraudDetectionCode/alertmodule/target/alertmodule-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.masteklabs.app.AlertProvider

10. Check if SparkStreaming module is up and running
	ps -ef|grep spark
	
	if not start the module as below
	PS1="\[\e]0;SparkStreaming\a\]${debian_chroot:+($debian_chroot)}\u@\h:\w\$ "
	cd /usr/local/spark
	

/usr/local/spark/bin/spark-submit --class com.masteklabs.fraudanalytics.Application --master local[5] /data/masteklabs/FraudDetectionCode/FraudDetection/fraudanalytics-prediction/target/fraudanalytics-prediction-0.0.1-SNAPSHOT-jar-with-dependencies.jar

11. check outputs from topics

 sudo /usr/local/kafka/bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic testing3 --from-beginning
