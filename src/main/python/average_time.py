file = open("time3.log")

total_tj = total_ts = 0
for line in file:
    tj, ts = [int(i) for i in line.strip().strip("\n").split()]
    total_tj += tj
    total_ts += ts
print(total_ts / 795 / 1000000000, total_tj / 795 / 1000000000)

if __name__ == "__main__":
    pass
