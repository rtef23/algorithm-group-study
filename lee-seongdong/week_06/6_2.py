import sys

sys.stdin = open('../input.txt')

for t in range(int(input())):
	n, m = list(map(int, input().split()))
	seq1 = [[_, 0] for _ in list(map(int, input().split()))]
	seq2 = [[_, 0] for _ in list(map(int, input().split()))]

	for _ in range(max(n, m)):
		for i in range(n):
			subseq = list(filter(lambda _: _ < seq1[i], seq1[0:i] + seq2))
			if len(subseq) == 0:
				seq1[i][1] = 1
			else:
				seq1[i][1] = max(subseq, key=lambda _: _[1])[1] + 1

		for i in range(m):
			subseq = list(filter(lambda _: _ < seq2[i], seq2[0:i] + seq1))
			if len(subseq) == 0:
				seq2[i][1] = 1
			else:
				seq2[i][1] = max(subseq, key=lambda _: _[1])[1] + 1

	print(max(seq1 + seq2, key=lambda _: _[1])[1])