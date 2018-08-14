import re
import sys

sys.stdin = open('../input.txt')

for t in range(int(input())):
	p_str = input().replace('?', '\w{1}').replace('*', '\w*')
	p = re.compile('^' + p_str + '$')

	words = list()
	for _ in range(int(input())):
		words.append(input())

	for word in sorted(filter(lambda x: p.match(x), words)):
		print(word)

