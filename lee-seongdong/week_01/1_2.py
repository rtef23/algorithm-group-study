# 0과 1로 만들어진 2D 정수 배열이 있습니다. 0은 장애물이고 1은 도로일때, 두 좌표가 주어지면, 첫번째 좌표에서 두번째 좌표까지 가장 가까운 거리를 구하시오.
# 두 좌표는 모두 도로에서 시작되고 좌, 우, 아래, 위로 움직이며 대각선으로는 움직일 수 없습니다. 만약 갈 수 없다면 -1을 리턴하시오.
# 예제)
# Input:
# {{1, 0, 0, 1, 1, 0},
# {1, 0, 0, 1, 0, 0},
# {1, 1, 1, 1, 0, 0},
# {1, 0, 0, 0, 0, 1},
# {1, 1, 1, 1, 1, 1}}
# Start: (0, 0)
# Finish: (0, 4)
# Output: 8


def solution(map_, start, finish):
	q = list()
	dx = [0, 0, 1, -1]
	dy = [-1, 1, 0, 0]

	len_x = len(map_[0])
	len_y = len(map_)

	q.append({'y': start[0], 'x': start[1], 'step': 0})

	while len(q) != 0:
		x = q[0]['x']
		y = q[0]['y']
		step = q[0]['step']
		map_[y][x] = 2
		q.pop(0)

		if y == finish[0] and x == finish[1]:
			return step

		for i in range(4):
			if x + dx[i] < 0 or y + dy[i] < 0 or x + dx[i] >= len_x or y + dy[i] >= len_y:
				continue

			if map_[y + dy[i]][x + dx[i]] == 1:
				q.append({'y': y + dy[i], 'x': x + dx[i], 'step': step + 1})

	return -1


map__ = [[1, 0, 0, 1, 1, 0],
         [1, 0, 0, 1, 0, 0],
         [1, 1, 1, 1, 0, 0],
         [1, 0, 0, 0, 0, 1],
         [1, 1, 1, 1, 1, 1]]

start_ = [0, 0]
finish_ = [0, 0]

print(solution(map__, start_, finish_))
