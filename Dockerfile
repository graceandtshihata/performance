FROM rappdw/docker-java-python

WORKDIR /usr/share/tag

RUN python3 -m pip install bzt
ADD  files files
ADD  src src
ADD  pom.xml pom.xml
ADD  pre1.yml pre1.yml
ADD  pre2.yml pre2.yml
ADD  participants.yml participants.yml
ADD  scenarios scenarios

RUN export M2_HOME=/usr/share/tag/files/apache-maven-3.6.3
RUN export M2=$M2_HOME/bin
RUN export PATH=$M2:$PATH
