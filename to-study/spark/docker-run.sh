docker run --rm -it \
    -v "$PWD":/home/spark/work \
    --dns=8.8.8.8 \
    --env-file=./.env \
    learning-spark \
    /opt/spark/bin/spark-submit \
    --jars /home/spark/work/jars/aws-java-sdk-bundle-1.12.661.jar,/home/spark/work/jars/bundle-2.29.38.jar,/home/spark/work/jars/caffeine-3.1.8.jar,/home/spark/work/jars/commons-configuration2-2.11.0.jar,/home/spark/work/jars/hadoop-aws-3.3.4.jar,/home/spark/work/jars/iceberg-spark-runtime-3.5_2.12-1.6.1.jar,/home/spark/work/jars/s3-tables-catalog-for-iceberg-0.1.3.jar \
    --conf spark.sql.catalog.s3tablesbucket=org.apache.iceberg.spark.SparkCatalog \
	  --conf spark.sql.catalog.s3tablesbucket.catalog-impl=software.amazon.s3tables.iceberg.S3TablesCatalog \
	  --conf spark.sql.catalog.s3tablesbucket.warehouse=arn:aws:s3tables:eu-central-1:743014983075:bucket/test-table-bucket \
	  --conf spark.sql.extensions=org.apache.iceberg.spark.extensions.IcebergSparkSessionExtensions \
    /home/spark/work/test.py
