# Hadoop Lab Practicals

This repository contains Hadoop MapReduce practical programs implemented in Java using Hadoop 3.3.6.

The project is designed for academic learning and practical implementation of distributed computing concepts using the Hadoop framework.

---

# Programs Included

## 1. Word Count Application

Counts occurrences of each word from a text input file using Hadoop MapReduce.

## 2. Log File Processing

Processes system log files and counts occurrences of log types such as:

- INFO
- ERROR
- WARNING

## 3. Weather Data Analysis

Processes weather datasets and calculates:

- Average Temperature
- Average Dew Point
- Average Wind Speed

---

# Technologies Used

- Java 17
- Hadoop 3.3.6
- GitHub Codespaces
- Linux Environment

---

# Project Structure

```text
hadoop_lab/
│
├── WordCount.java
├── LogProcessor.java
├── WeatherAnalysis.java
│
├── input/
├── loginput/
├── weatherinput/
│
├── README.md
└── .gitignore
```

# Setup & Run Guide

Move to project folder:

```bash
cd /workspaces/hadoop_lab
```

Set environment variables:

```bash
export JAVA_HOME=/usr/local/sdkman/candidates/java/current

export HADOOP_HOME=/workspaces/hadoop_lab/hadoop-3.3.6

export PATH=$PATH:$JAVA_HOME/bin:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
```

Verify setup:

```bash
java -version
hadoop version
```

---

# WordCount

## Clean

```bash
rm -rf classes output
rm -f wordcount.jar
```

## Compile & Run

```bash
mkdir classes

javac -classpath "$(hadoop classpath)" -d classes WordCount.java

jar -cvf wordcount.jar -C classes/ .

hadoop jar wordcount.jar WordCount input output

cat output/part-r-00000
```

---

# LogProcessor

## Clean

```bash
rm -rf classes logoutput
rm -f logprocessor.jar
```

## Compile & Run

```bash
mkdir classes

javac -classpath "$(hadoop classpath)" -d classes LogProcessor.java

jar -cvf logprocessor.jar -C classes/ .

hadoop jar logprocessor.jar LogProcessor loginput logoutput

cat logoutput/part-r-00000
```

---

# WeatherAnalysis

## Clean

```bash
rm -rf classes weatheroutput
rm -f weather.jar
```

## Compile & Run

```bash
mkdir classes

javac -classpath "$(hadoop classpath)" -d classes WeatherAnalysis.java

jar -cvf weather.jar -C classes/ .

hadoop jar weather.jar WeatherAnalysis weatherinput weatheroutput

cat weatheroutput/part-r-00000
```

# Important Notes

- Always remove old output folders before rerunning Hadoop jobs.
- Hadoop does not overwrite existing output directories.
- This project uses Hadoop local standalone mode inside GitHub Codespaces.

---

# Key Hadoop Concepts

- HDFS
- Mapper
- Reducer
- Combiner
- Shuffle and Sort
- Distributed Processing
- Fault Tolerance

---

# Author

Anuj  
Computer Engineering Student  
Savitribai Phule Pune University (SPPU)

---

# License

This repository is intended for educational and academic purposes.
