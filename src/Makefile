NATIVECLASSNAME = BackupDataSort
LIBNAME = libInsertionSort
LIBSOURCE = insertionsort.c

darwin:
	javac *.java
	javah $(NATIVECLASSNAME)
	gcc -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/darwin -shared -fpic -o $(LIBNAME).dylib $(LIBSOURCE)

linux:
	javac *.java
	javah $(NATIVECLASSNAME)
	gcc -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -shared -fpic -o $(LIBNAME).so $(LIBSOURCE)/

clean:
	rm -f *.class $(NATIVECLASSNAME).h *.dylib *.so