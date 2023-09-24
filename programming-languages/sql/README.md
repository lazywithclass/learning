## SQL

I've used these datasets:
 * [postgresqltutorial.com]( https://www.postgresqltutorial.com/postgresql-getting-started/postgresql-sample-database/)
 * IMDB's one which is in this repo

[Here](https://www.postgresqltutorial.com/postgresql-getting-started/postgresql-sample-database/#dvd-rental-er-model) you can find the ER.

### Emacs instructions

```
M-x sql-postgres - to connect to the running instance
C-c C-c          - to get the result of the last query
```

### Configuration

```sh
./init.sh

# or if you want to use imdb
./init-imdb.sh
```

### Server status

```sh
# start
nohup pg_ctl -D ./data start &

# stop
nohup pg_ctl -D ./data stop &
```

