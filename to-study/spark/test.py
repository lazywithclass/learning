from pyspark.sql import SparkSession

spark = SparkSession.builder.getOrCreate()

spark.sql("CREATE NAMESPACE IF NOT EXISTS s3tablesbucket.movies")
spark.sql("CREATE TABLE IF NOT EXISTS s3tablesbucket.movies.lord_of_the_rings")

df = spark.read.parquet("/home/spark/work/data/test_data.parquet")
df.writeTo("s3tablesbucket.movies.lord_of_the_rings").createOrReplace()
