# Twitter sentiment analysis

Twitter sentiment analysis tool written in Java with the library LingPipe.

The purpose of this project is not to implement the perfect tweet sentiment
classifier, but it is a study about the speed improvement you can achieve with
Hadoop.

In this README you will find instructions on how to compile and execute the
program. In the `Paper` directory there is a technical paper about the
algorithm and the comparison of the sequential version with the parallel
version.

## Compiling

The project is written with Java 8.

The directory `TwitterSA` is the root of the Java project, it is written with
Maven build tool. If you have the IntelliJ IDE you can directly import the
entire folder `TwitterSA`, otherwise, if you prefer manual building, you can
`cd` to that directory and run

    $ mvn clean package

This command will compile the project, run some tests and create three jar
files in `TwitterSA/target/`. The output of the Maven build are three jar
files:

* `train.jar`: train the model (sequential)
* `classify.jar`: classify tweets (sequential)
* `classify-mr.jar`: classify tweets (parallelized with Hadoop)

## Training

With `train.jar` you can train the model with a dataset containing some tweets
and their corresponding sentiments. The train the model run

    $ java -jar train.jar <dataset> <model-file>

The input dataset must be a CSV file with no header, the first column must
contain the sentiment and the second column the tweet text, for example:

    0,very sad tweet
    0,another very sad tweet
    1,happy tweet!!
    0,yet another sad tweet

As you can see, sentiments are coded with `0 - negative` and `1 - positive`,
with no neutral class.

If you have a Kaggle account there is an easy way to create some datasets to
process with this tool. To download and create the datasets `cd` into the
`datasets` directory and run

    $ ./download-datasets
    $ ./create_datasets.py

These two scripts will download and create a train dataset (`train.csv`) and
a test (`test.txt`) with only the tweet text without any associated
sentiment. The requirements to execute these scripts are:

* A Kaggle account
* Python >= 3.6
* kaggle, scikit-learn, pandas, numpy

Once you have the datasets, you can train the model with

    $ java -jar TwitterSA/target/train.jar datasets/train.csv sentiment.model

This will save the model in the file `sentiment.model`, which can be loaded
by a classifier.

## Sequential classification

Once you have trained a model, you can use it to classify some tweets. The jar
file `classify.jar` accomplishes this task in a sequential way. Its usage is

    $ java -jar classify.jar <model-file> <input-dir>

The `model-file` argument is a file containing the trained model and the
argument `input-dir` must be a directory containing some text files with a
tweet text each line. The file `test.txt` obtained following the instructions
in the previous section is an example of such file.

Suppose you have the trained the model in `sentiment.model` as suggested in
the previous section and you have the directory `datasets/testsets3`:

    $ ls datasets/testsets3
    text01.txt
    text02.txt
    text03.txt

Then to classify the tweets in that directory run

    $ java -jar TwitterSA/target/classify.jar sentiment.model datasets/testsets3
    Total: 4269960
    Negatives: 2033715
    Positives: 2236245

## Parallel classification with Hadoop

With jar file `classify-mr.jar` you can classify tweets with Hadoop using the
MapReduce framework. The usage of this jar file is

    $ hadoop jar TwitterSA/classify-mr.jar <model-file> <input-dir> <output-dir>

You need Hadoop 2.9 installed in your system.

The `model-file` argument is the file containing the trained model, **it must be
located in the user directory of the hdfs file system**. The `input-dir`
argument is a directory containing the tweets and the `output-dir` is where
Hadoop will write its output.

Suppose you have the directory `testsets3` as showed in the previous section,
to run a sentiment analysis with Hadoop MapReduce run

    $ hdfs dfs -copyFromLocal datasets/testsets3
    $ hdfs dfs -copyFromLocal sentiment.model
    $ hadoop jar TwitterSA/target/classify-mr.jar sentiment.model testsets3 output
    ...
    some hadoop output
    ...
    $ hdfs dfs -cat output/*
    negatives   2034411
    positives   2235549
