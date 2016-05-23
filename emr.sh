#!/bin/bash

path1=build
path2=com/emr/nlp/dic

if [ ! -d $path1 ] ; then
    mkdir $path1
fi

# java compile

javac -classpath lib/apache-commons-lang.jar:lib/commons-configuration-1.9.jar:lib/commons-logging-1.2.jar:lib/hadoop-core-1.2.1.jar:lib/hadoop-mapreduce-client-core-0.23.1.jar:lib/IKAnalyzer2012_u6.jar:lib/jackson-all-1.9.0.jar:lib/lucene-core-3.6.0.jar:lib/ruta-core-2.3.0.jar:lib/uima-core.jar -d build/ src/com/emr/dbutil/IndexDB.java src/com/emr/nlp/NLP.java src/com/emr/mapred/Index.java src/com/emr/mapred/EMRChain.java src/com/emr/EMRLauncher.java

if [ ! -d $path1/$path2 ] ; then
    mkdir $path1/$path2
fi

# copy dic and conf files

for f in src/$path2/*.dic
do
    cp $f $path1/$path2
done

cp src/IKAnalyzer.cfg.xml $path1

# create jar

jar -cvfm EMR.jar manifest.mf -C build/ .
