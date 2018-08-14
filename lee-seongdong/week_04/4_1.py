import sys

sys.stdin = open('../input.txt')


def main():
	tc = int(input())
	for t in range(tc):
		a = input().replace('F', '0').replace('M', '1')
		b = input().replace('F', '0').replace('M', '1')
		diff = len(b) - len(a)

		a = int(a, 2)
		b = int(b, 2)
		c = 0

		for _ in range(diff + 1):
			if a & b == 0:
				c += 1
			a = (a << 1)

		print(c)

	return


main()
