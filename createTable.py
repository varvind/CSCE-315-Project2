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
  columns = "(attributes_id,business_id,"
  for x in range(12, len(lines[0])):
    if "-" in lines[0][x] :
      lines[0][x] = lines[0][x].replace("-", "_")
    if "." in lines[0][x] :
      lines[0][x] = lines[0][x].replace(".", "_")

    if lines[0][x] != "attributes" and lines[0][x] != "hours" :
      if "attributes" in lines[0][x]:
        lines[0][x] = lines[0][x][11:]
      columns += lines[0][x] + ","
  columns = columns[0:-1] + ")"
  for x in range(1, len(lines)):
    command = "INSERT INTO attr" + columns + " VALUES (\'" + str(x) + "\',\'" + lines[x][0] + "\',"
    for y in range(12, len(lines[0])):
      if y != 29 and y != 30:
        if lines[x][y] == "":
          lines[x][y] = "N/A"
        lines[x][y] = "\'" + lines[x][y] + "\'"
        command += lines[x][y] + ","
    command = command[0:-1] + ")"
    cur.execute(command)


# Commit everything to the database
conn.commit() 