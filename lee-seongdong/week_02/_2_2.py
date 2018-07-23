import sys

sys.stdin = open('../input.txt')

h = 0
w = 0
white = 0
board = []
block = [
	[[0, 0], [0, 1], [1, 0]],
	[[0, 0], [0, 1], [1, 1]],
	[[0, 0], [1, 0], [1, 1]],
	[[0, 1], [1, 0], [1, 1]]
]


def cover(r, c, shape):
	global board, block

	for index in range(3):
		board[r + block[shape][index][0]][c + block[shape][index][1]] = '*'

	return


def uncover(r, c, shape):
	global board, block

	for index in range(3):
		board[r + block[shape][index][0]][c + block[shape][index][1]] = '.'

	return


def can_cover(r, c, shape):
	global board, block

	for index in range(3):
		if board[r + block[shape][index][0]][c + block[shape][index][1]] != '.':
			return False

	return True


def print_board():
	global board, h
	for r in range(h):
		print(board[r])
	return


def search():
	global h, w, board, block

	for r in range(h - 1):
		for c in range(w - 1):
			for shape in range(4):
				if can_cover(r, c, shape):
					cover(r, c, shape)
					search()
					uncover(r, c, shape)

	return


def main():
	global h, w, board, white
	tc = int(input())
	for t in range(tc):
		h, w = [int(x) for x in input().split()]
		board = [0 for _ in range(h)]
		for i in range(h):
			board[i] = list(input())

		search()

	return


main()
