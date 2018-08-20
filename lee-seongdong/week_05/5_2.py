import re
import sys

sys.stdin = open('../input.txt')

for t in range(int(input())):
	p_str = input().replace('?', '\w{1}').replace('*', '\w*')
	p = re.compile('^' + p_str + '$')
	n = int(input())

	for word in sorted(filter(p.match, [input() for _ in range(n)])):
		print(word)

