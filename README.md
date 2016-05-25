# EMR (Electrical Medical Record)

## NOTE

1. Suppose you are in the root directory of the project.

2. Suppose hadoop and related jars are available and runnable on the machine.

## Procedures to compile and run the project

1. _emr.sh_ is a shell script that can run directly to compile and generate jar file. So just run  
**./emr.sh**

2. Run on Hadoop:  
**/usr/local/hadoop/bin/hadoop jar EMR.jar /data /doc**

The word count result should be stored in database.
