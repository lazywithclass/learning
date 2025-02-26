from sqlalchemy import create_engine, text

DATABASE_URL = "sqlite:///mydatabase.db"
engine = create_engine(DATABASE_URL)

def test_simple_query():
    with engine.connect() as connection:
        result = connection.execute(text("SELECT * FROM users"))
        print(type(result))
        for row in result:
            print(row[0], row[1], row[2], row[3])

def test_simple_query_access_with_name():
    # using _mapping as advised in the docs:
    # https://docs.sqlalchemy.org/en/20/core/connections.html#sqlalchemy.engine.Row
    with engine.connect() as connection:
        result = connection.execution_options(stream_results=True).execute(text("SELECT * FROM users"))
        for row in result:
            print(row._mapping["id"])

def test_with_streaming():
    with engine.connect() as connection:
        result = connection.execution_options(stream_results=True).execute(text("SELECT * FROM users"))
        for row in result:
            print(row[0], row[1], row[2], row[3])