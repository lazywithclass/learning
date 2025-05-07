---
cssclasses: 
tags:
  - iceberg
  - spark
---
## Iceberg

[Apache Iceberg Fundamentals: Course by Dremio](https://www.youtube.com/watch?v=MSuT20EqnnM&list=PL-gIUf9e9CCtGr_zYdWieJhiqBG_5qSPa)

It's a table format specification. It's not a storage engine, you don't put data in Iceberg.

Abstracts the physical, expose a logical view.

Offers:

* ACID transactions
* time travel
* partition evolution
* schema evolution
* hidden partitioning
* support parquet

### Metadata

Defines metadata, which allows engines to understand the table and plan queries.

* Catalog - "where is the metadata?"
* Metadata layer
	* Metadata file - schema, partition
	* Manifest lists - snapshots of the table 
* Data layer - data files

#### Catalog

Mapping of the table name to the location of current metadata file. Help to track Iceberg tables and provides locking mechanisms for ACID guarantees.

![Catalog](attachments/Pasted%20image%2020250504134535.png)

#### Metadata file

![Metadata file](attachments/Pasted%20image%2020250504134600.png)

Manifest list represent a group of files, so for example if we have a partition for `month` and we're searching for `December` then it's here where we decide whether to include the files it represents. 

![Manifest list](attachments/Pasted%20image%2020250504134734.png)

At this level we could filter for matching data, for example if I am looking for the first days of `December` this is where that filtering happens.

![Manifest file](attachments/Pasted%20image%2020250504135139.png)

### Effects of an INSERT

![Effects of an INSERT](attachments/Pasted%20image%2020250504135909.png)

## copy-on-write and merge-on-read

Use the first if you do more reads than writes, otherwise the second.

Default is copy-on-write.


## Spark

It's an execution engine. Takes advantage of the format specification to improve query performance.