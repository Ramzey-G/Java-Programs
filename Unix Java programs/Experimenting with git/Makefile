# FILE: Makefile
# DESCRIPTION:
# A simple makefile with variables
# This file builds greet.java into an
# executable file
JAVASRC1 = greet.java
JAVASRC2 = greetings.java
FILE1 = greet
FILE2= greetings
SOURCES = README makefile ${JAVASRC}
CLASSES = greet.class greetings.class

runGreet:
	javac -Xlint ${JAVASRC1}
	java ${FILE1}

runGreetings:
	javac -Xlint ${JAVASRC2}
	java ${FILE2}

clean:
	rm ${CLASSES}

.PHONY: clean runGreet runGreetings
