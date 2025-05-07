
## Open points

* data skew?
* se effettivamente in molti hanno lo scenario in cui mettono `userid` come partizione come evitano di avere una moltitudine di piccoli file? 

## Richiesta

$$
\begin{align}
&\ \lambda(id\_paziente,\ id\_recovery,\ \underline{year,\ tag\_file}) \newline
&\ \gamma(data\_in,\ data\_out) \newline
\end{align}
$$

Dato questo schema si vuole filtrare per tutti i campi, senza che questi diventino partizioni.


## Analysis


### Not on partition key 

Execution plan for a query not on the partition key

```language-sql
EXPLAIN (FORMAT GRAPHVIZ)
SELECT file_tag FROM test.elsa_acceptance_test
WHERE patient_id = '8caae001-497f-4a86-a403-e22918627868' 
```

```
Query Plan
digraph distributed_plan {
subgraph cluster_0 {
label = "SOURCE"
plannode_1[label="{Output[file_tag]}", style="rounded, filled", shape=record, fillcolor=white];
plannode_2[label="{Project}", style="rounded, filled", shape=record, fillcolor=bisque];
plannode_3[label="{Filter|(\"patient_id\" = VARCHAR '8caae001-497f-4a86-a403-e22918627868')}", style="rounded, filled", shape=record, fillcolor=yellow];
plannode_4[label="{TableScan[awsdatacatalog/s3tablescatalog/test-table-bucket:test.elsa_acceptance_test$data@543024708523231429]}", style="rounded, filled", shape=record, fillcolor=deepskyblue];
}
plannode_1 -> plannode_2;
plannode_2 -> plannode_3;
plannode_3 -> plannode_4;
}
```

### On partition key

```language-sql
EXPLAIN (FORMAT GRAPHVIZ)
SELECT file_tag FROM test.elsa_acceptance_test
WHERE file_tag = 'STUDY' and doc_date = CAST('2024-07-16 00:00:00.000000 UTC' as TIMESTAMP)
```

```
Query Plan
digraph distributed_plan {
subgraph cluster_0 {
label = "SOURCE"
plannode_1[label="{Output[file_tag]}", style="rounded, filled", shape=record, fillcolor=white];
plannode_2[label="{Project}", style="rounded, filled", shape=record, fillcolor=bisque];
plannode_3[label="{Filter|(\"doc_date\" = TIMESTAMP '2024-07-16 00:00:00.000000 UTC')}", style="rounded, filled", shape=record, fillcolor=yellow];
plannode_4[label="{TableScan[awsdatacatalog/s3tablescatalog/test-table-bucket:test.elsa_acceptance_test$data@543024708523231429 constraint on [file_tag]]}", style="rounded, filled", shape=record, fillcolor=deepskyblue];
}
plannode_1 -> plannode_2;
plannode_2 -> plannode_3;
plannode_3 -> plannode_4;
}
```

Differently from above this second run has a `constraint on [file_tag]`. No pruning happens. So a full table scan is performed.