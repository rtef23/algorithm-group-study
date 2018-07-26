import sys

sys.stdin = open('../input.txt')

switch = [
	[0, 1, 2],
	[3, 7, 9, 11],
	[4, 10, 14, 15],
	[0, 4, 5, 6, 7],
	[6, 7, 8, 10, 12],
	[0, 2, 14, 15],
	[3, 14, 15],
	[4, 5, 7, 14, 15],
	[1, 2, 3, 4, 5],
	[3, 4, 5, 9, 13]
]
clocks = list()
ans = -1


def forward(button):
	global clocks, switch
	for c in switch[button]:
		clocks[c] = (clocks[c] + 2) % 12 + 1
	return


def is_answer():
	for c in clocks:
		if c != 12:
			return False
	return True


def search(depth, count):
	global clocks, switch, ans

	if depth == 10:
		if is_answer() and (ans == -1 or ans > count):
			ans = count
		return

	for i in range(4):
		forward(depth)
		search(depth + 1, count + ((i + 1) % 4))
	return


def main():
	global clocks, ans

	c = int(input())
	for t in range(c):
		clocks = list(map(int, input().split()))
		ans = -1
		search(0, 0)
		print(ans)

	return


main()
