import sys

sys.stdin = open('../input.txt')


def find(tree, n):
	for i in range(1, n):
		for j in range(i + 1):
			if j == 0:
				tree[i][j] += tree[i - 1][j]
			elif j == i:
				tree[i][j] += tree[i - 1][j - 1]
			else:
				tree[i][j] += max(tree[i - 1][j], tree[i - 1][j - 1])

	return


def main():
	tc = int(input())
	for t in range(tc):
		n = int(input())
		input_tree = list()
		for _ in range(n):
			input_tree.append(list(map(int, input().split())))

		find(input_tree, n)
		print(max(input_tree[n-1]))

	return


main()
