import json
import csv


def from_json(file_name):
    with open(file_name, "r") as fp:
        di = json.load(fp)
        print(di)


def from_csv(file_csv):
    rows = []
    oly = []
    with open(file_csv) as f:
        reader = csv.reader(f)
        listOfCall = []
        for rows in reader:
            listOfCall.append(call(rows))


def call(rows):
    print(" time= ", rows[1])
    print(" src= ", rows[2])
    print(" dest= ", rows[3])
    print(" index= ", rows[5])
    print()


if __name__ == '__main__':
    # from_json("B1.json")
    from_csv("Calls_a.csv")
