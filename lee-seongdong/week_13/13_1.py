import sys

sys.stdin = open('../input.txt')

tc = int(input())

for t in range(tc):
	n = int(input())
	lus = list(map(int, input().split()))
	kor = list(map(int, input().split()))

	lus.sort(reverse=True)
	kor.sort(reverse=True)

	il = 0
	ik = 0
	ans = 0
	while True:
		if il == n:
			break

		if lus[il] <= kor[ik]:
			ans += 1
			il += 1
			ik += 1
		else:
			il += 1

	print(ans)

