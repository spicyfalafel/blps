all: jar sync-helios

jar:
	./mvnw clean install

sync-helios:
	scp -P 2222 ./target/lab-0.0.1-SNAPSHOT.jar s285574@helios.cs.ifmo.ru:~/blps-lab1.jar
	scp -P 2222 ./Makefile s285574@helios.cs.ifmo.ru:~/Makefile

docker:
	docker-compose -f stack.yml up

run-jar-local:
	java -jar ./target/lab-0.0.1-SNAPSHOT.jar

run-jar-helios:
	export $(< ~/.env)
	java -jar blps-lab1.jar
