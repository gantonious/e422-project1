NATIVECLASSNAME = BackupDataSorter
LIBNAME = libInsertionSort
LIBSOURCE = insertionsort.c

darwin:
	javac *.java
	javah $(NATIVECLASSNAME)
	gcc -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/darwin -shared -fpic -o $(LIBNAME).dylib $(LIBSOURCE)

linux:
	javac *.java
	javah $(NATIVECLASSNAME)
	gcc -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -shared -fpic -o $(LIBNAME).so $(LIBSOURCE)

package:
	tar -cvzf e422-project1-antoniou.tar.gz *.c *.java Makefile README *.pdf

clean:
	rm -f *.class $(NATIVECLASSNAME).h *.dylib *.so