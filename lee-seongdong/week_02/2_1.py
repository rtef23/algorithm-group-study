import sys

sys.stdin = open('../input.txt', 'r')

m = 0
n = 0
pair_list = []
student_list = []


def dfs(pair_index, depth):
	global n, m, pair_list, student_list

	if depth == int(n / 2):
		return 1

	count = 0
	for i in range(pair_index, m):
		if student_list[pair_list[i][0]] == 0 and student_list[pair_list[i][1]] == 0:
			student_list[pair_list[i][0]] = 1
			student_list[pair_list[i][1]] = 1
			count += dfs(i + 1, depth + 1)
			student_list[pair_list[i][0]] = 0
			student_list[pair_list[i][1]] = 0

	return count


def main():
	global n, m, pair_list, student_list

	tc = int(input())
	for t in range(tc):
		n, m = [int(x) for x in input().split()]
		input_list = [int(x) for x in input().split()]
		student_list = [0 for _ in range(n)]
		pair_list = [[0 for _ in range(2)] for _ in range(m)]
		for i in range(m):
			pair_list[i][0] = input_list[i * 2]
			pair_list[i][1] = input_list[i * 2 + 1]

		print(dfs(0, 0))


main()
