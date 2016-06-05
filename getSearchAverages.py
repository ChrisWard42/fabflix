TJsum = 0
TSsum = 0
count = 0

with open("/p5log/searchTimes.txt") as f:
	for line in f:
		numbers = [int(num) for num in line.split()]
		TJsum += numbers[0]
		TSsum += numbers[1]
		count += 1
print "TJ Average: ", TJsum/count/1000000.0, "ms"
print "TS Average: ", TSsum/count/1000000.0, "ms"
