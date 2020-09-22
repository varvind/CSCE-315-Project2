import sys
import csv
maxInt = sys.maxsize

while True:
    # decrease the maxInt value by factor 10 
    # as long as the OverflowError occurs.

    try:
        csv.field_size_limit(maxInt)
        break
    except OverflowError:
        maxInt = int(maxInt/10)



with open("yelp_academic_dataset_checkin.csv") as csvfile :
  csvreader = csv.reader(csvfile)
  lines = list(csvreader)
  for x in range (1, len(lines)) :
    lines[x][1] = "{" + lines[x][1]+ "}"
  writer = csv.writer(open("yelp_academic_dataset_checkin.csv", "wb"))
  writer.writerows(lines)