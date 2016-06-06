import sys

TJsum = 0
TSsum = 0
count = 0

with open("/p5log/searchTimes.txt") as f:
	for line in f:
		numbers = [int(num) for num in line.split()]
		if(len(numbers) == 2):
			TJsum += numbers[0]
			TSsum += numbers[1]
			count += 1
if(count > 0):
	print "TJ Average: ", TJsum/count/1000000.0, "ms"
	print "TS Average: ", TSsum/count/1000000.0, "ms"
else:
	print "File is empty"

#empty file only if 'empty' command line argument given
if(len(sys.argv) == 2):
	if(sys.argv[1] == 'empty'):
		open("/p5log/searchTimes.txt", 'w').close() #empty file after calculating averages
		print "The log file was cleared"
