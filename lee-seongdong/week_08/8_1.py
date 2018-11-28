import sys

sys.stdin = open('../input.txt')


i = list()
tc = int(input())
dp = [_ for _ in range(101)]
dp[0] = 0
dp[1] = 1
dp[2] = 2

for _ in range(tc):
	i.append(int(input()))

max_i = max(i) + 1

for n in range(3, max_i):
	dp[n] = (dp[n-1] + dp[n-2]) % 1000000007

for n in i:
	print(dp[n])
