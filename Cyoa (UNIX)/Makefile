
# Build a class and do unit tests
JAVASRC = Cyoa.java
CLASSES = Cyoa.class
NAME = Cyoa

all: ${CLASSES}

${CLASSES}: ${JAVASRC}
	javac -Xlint ${JAVASRC}

clean:
	rm ${CLASSES}
	rm -f *.class

test: ${CLASSES}
	java Cyoa demo.adventure

.PHONY: clean all test
