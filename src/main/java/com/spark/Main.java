package com.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        SparkSession spark = SparkSession.builder().appName("Apache Spark In Practice").getOrCreate();

        String csvFile = "src/main/resources/books.csv";

        Dataset<Row> booksDataSet = spark
                .read()
                .format("csv")
                .option("sep", ";")
                .option("inferSchema", "true")
                .option("header", "true")
                .load(csvFile);
        booksDataSet.show();

        Dataset<Row> distinctLangs= booksDataSet.select("language_code").distinct();
        distinctLangs.show(false);

        List<Row> languageList= distinctLangs.collectAsList();

        for(Row row: languageList)
        {
            String langCode=row.getString(0);
            Dataset<Row> langSpecificBooksDataSet=booksDataSet.filter(booksDataSet.col("language_code").equalTo(langCode));
            langSpecificBooksDataSet
                    .write()
                    .option("mapreduce.fileoutputcommitter.marksuccessfuljobs","false")
                    .option("sep", ";")
                    .option("header", "true")
                    .mode(SaveMode.Overwrite)
                    .csv(langCode+"_books.csv");
        }
        Thread.sleep(100000);

    }
}
