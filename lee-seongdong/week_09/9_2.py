import sys

sys.stdin = open('../input.txt')

dp1 = [0 for _ in range(101)]  # 대칭
dp1[1] = 1
dp1[2] = 2
dp1[3] = 1
dp1[4] = 3
dp2 = [0 for _ in range(101)]  # 전체
dp2[1] = 1
dp2[2] = 2
dp2[3] = 3
dp2[4] = 5
dp3 = [0 for _ in range(101)]  # 비대칭
dp3[1] = 0
dp3[2] = 0
dp3[3] = 2
dp3[4] = 2

tc = int(input())
n = list()

for _ in range(tc):
	n.append(int(input()))

max_n = max(n) + 1

for i in range(5, max_n):
	dp1[i] = dp1[i-2] + dp1[i-4]
	dp2[i] = dp2[i-1] + dp2[i-2]
	dp3[i] = (dp2[i] - dp1[i]) % 1000000007

for i in n:
	print(dp3[i])
