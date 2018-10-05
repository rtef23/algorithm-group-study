import sys

sys.stdin = open('../input.txt')

for t in range(int(input())):
	n = int(input())
	seq = [[_, 0] for _ in list(map(int, input().split()))]

	for i in range(n):
		subseq = list(filter(lambda _: _ < seq[i], seq[0:i]))
		if len(subseq) == 0:
			seq[i][1] = 1
		else:
			seq[i][1] = max(subseq, key=lambda _: _[1])[1] + 1

	print(max(seq, key=lambda _: _[1])[1])


