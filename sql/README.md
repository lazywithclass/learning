## SQL

I've used [this dataset]( https://www.postgresqltutorial.com/postgresql-getting-started/postgresql-sample-database/)

[Here](https://www.postgresqltutorial.com/postgresql-getting-started/postgresql-sample-database/#dvd-rental-er-model) you can find the ER.

### Emacs instructions

```
M-x sql-postgres - to connect to the running instance
C-c C-c          - to get the result of the last query
```

### Configuration

```sh
./init.sh
```

### Server status

```sh
# start
nohup pg_ctl -D ./data start &

# stop
nohup pg_ctl -D ./data stop &
```

