FROM blazemeter/taurus

WORKDIR /usr/ your work dir

#Add PERFORMANCE TESTING AUTOMATION JARS
ADD target/load-testing.jar     load-testing.jar
ADD target/load-testing-test.jar load-testing.jar

#ADD REQUIRES LIBRARIES
ADD target/libs     libs

#ADD PROJECT_RELATED XML OR JSON OR JMX FILES
ADD target/test-classes/project.....

# ADD DOCKER RUNNER FILE
ADD  dockerRunner.sh        dockerRunner.sh

#ENTRY POINT
ENTRYPOINT sh dockerRunner.sh