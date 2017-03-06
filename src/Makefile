NATIVECLASSNAME = BackupDataSort
LIBNAME = libInsertionSort.dylib
LIBSOURCE = insertionsort.c

all:
	javac *.java
	javah $(NATIVECLASSNAME)
	gcc -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/darwin -shared -fpic -o $(LIBNAME) $(LIBSOURCE)
	export LD_LIBRARY_PATH=$(LD_LIBRARY_PATH):.

clean:
	rm -f *.class $(NATIVECLASSNAME).h $(LIBNAME)