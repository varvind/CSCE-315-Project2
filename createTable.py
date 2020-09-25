import psycopg2
import os
import csv;
# Connect to 315 database 
# Use an environment variable too access db password
conn = psycopg2.connect("host=csce-315-db.engr.tamu.edu dbname=db908_group18_project2 user=varvind password=arvind00")
cur = conn.cursor()

# Create a test table in the database
with open ("yelp_academic_dataset_business.csv") as csvfile:
  csvreader = csv.reader(csvfile)
  lines = list(csvreader)
  columns = "(business_id,"
  for x in range(1, 12):
    columns += lines[0][x] + ","
  columns += "attribute_id" + ")"
  # createTable = "CREATE TABLE business " + columns
  # cur.execute(createTable)
  for x in range(1, len(lines)):
    command = "INSERT INTO business" + columns + " VALUES ("
    for y in range(0, 12):
      if y == 11:
        lines[x][y] = "{" + lines[x][y] + "}"
      if lines[x][y] == "":
        lines[x][y] = "N/A"
      if "\'" in lines[x][y]:
        lines[x][y] = lines[x][y].replace('\'', '\'\'')
      lines[x][y] = "\'" + lines[x][y] + "\'"
      command += lines[x][y] + ","
    command +=  str(x) + ")"
    print(x)
    cur.execute(command)


# Commit everything to the database
conn.commit() 