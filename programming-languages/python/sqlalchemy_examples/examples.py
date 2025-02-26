# sqlalchemy stream_results




# cosa ritorna come risultato di una query?
# come si accede al valore di una colonna?

from sqlalchemy import create_engine, text

DATABASE_URL = "sqlite:///mydatabase.db"
engine = create_engine(DATABASE_URL)

def test_simple_query():
    with engine.connect() as connection:
        result = connection.execute(text("SELECT * FROM users"))
        for row in result:
            print(row[0], row[1], row[2], row[3])

def test_with_streaming():
    with engine.connect() as connection:
        result = connection.execution_options(stream_results=True).execute(text("SELECT * FROM users"))
        for row in result:
            print(row)