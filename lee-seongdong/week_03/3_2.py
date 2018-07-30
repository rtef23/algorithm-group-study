import sys

sys.stdin = open('../input.txt')

tree = list()


def swap(start, mid, end):
	global tree
	temp_list = tree[mid + 1:end] + tree[start:mid + 1]
	tree = tree[:start] + temp_list + tree[end:]
	return


def reverse(start):
	global tree

	if tree[start] != 'x':
		return start

	mid = 0
	end = 0
	offset = start
	for i in range(4):
		offset = reverse(offset + 1)

		if i == 1:
			mid = offset

		if i == 3:
			end = offset

	swap(start + 1, mid, end + 1)
	return end


def main():
	global tree
	tc = int(input())
	for t in range(tc):
		tree = input()
		reverse(0)
		print(tree)

	return


main()
