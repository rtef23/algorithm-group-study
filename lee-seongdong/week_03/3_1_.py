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

clock_map = [
	[4, [8, 12]],
	[3, [6]],
	[2, [10]],
	[1, [11]],
	[7, [7]],
	[9, [9, 13]],
	[8, [4, 5]],
	[0, [1]],
	[5, [0, 2]],
	[6, [3, 14, 15]]
]

clocks = list()


def press_button(button):
	global clocks, switch
	for c in switch[button]:
		clocks[c] = (clocks[c] + 2) % 12 + 1
	return


def check(count):
	global clocks, clock_map
	clock_group_list = clock_map[count][1]
	button_press_count = int((4 - clocks[clock_group_list[0]] / 3) % 4)

	for i in range(button_press_count):
		press_button(clock_map[count][0])

	for i in clock_group_list:
		if clocks[i] != 12:
			return -1
	return button_press_count


def search():
	global clocks, switch
	ans = 0
	for i in range(10):
		count = check(i)
		if count != -1:
			ans += count
		else:
			return -1
	return ans


def main():
	global clocks

	c = int(input())
	for t in range(c):
		clocks = list(map(int, input().split()))
		print(search())

	return


main()
