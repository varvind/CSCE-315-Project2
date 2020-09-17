import psycopg2
import os

# Connect to 315 database 
# Use an environment variable too access db password
conn = psycopg2.connect("host=csce-315-db.engr.tamu.edu dbname=db908_group18_project2 user=varvind password=arvind00")
cur = conn.cursor()

# Create a test table in the database
cur.execute("""
    CREATE TABLE business(
    business_id text PRIMARY KEY,
    name text,
    location int,
    stars float,
    review_count int,
    business_type text[],
    hours json
)
""")

cur.execute("""
    CREATE TABLE customer(
    customer_id text PRIMARY KEY,
    name text,
    review_count int,
    average_stars int
)
""")

# Commit everything to the database
conn.commit() 