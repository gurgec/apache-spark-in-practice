# Apache Spark in Practice

This application is an example implementation of Apache Spark
The implementation consists of two parts. 
## Read part: 
This part reads a csv file downloaded from kaggle. The data consists of goodreads book data with various language.
The csv file converted to DataFrame and it is grouped according to language_code. 
## Write part: 
For every different class of language codes new csv files are created and saved.    
## Dependencies and Prerequisites

* Java Version: 1.8
* Installed Apache Spark: 2.4.4
* Dependencies:
``` 
<dependency> <!-- Spark dependency -->
    <groupId>org.apache.spark</groupId>
    <artifactId>spark-sql_2.12</artifactId>
    <version>2.4.4</version>
    <scope>provided</scope>
</dependency> 
```

## Usage

```> sbt run```

## Contributing
This is an example project. 

## License
[MIT](https://choosealicense.com/licenses/mit/)