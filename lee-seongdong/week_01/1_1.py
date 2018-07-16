# Question1)
# 정수로된 배열이 주어지면, 각 원소가 자신을 뺀 나머지 원소들의 곱셈이 되게하라.
# 단, 나누기 사용 금지, O(n) 시간복잡도
# 예제)
# input: [1, 2, 3, 4, 5]
# left : [1, 1, 2, 6, 24]
# right: [120, 60, 20, 5, 1]
# output: [120, 60, 40, 30, 24]


def solution(input_list):
	input_list_size = len(input_list)

	left = list()
	left.append(1)
	for index in range(input_list_size - 1):
		left.append(left[index] * input_list[index])

	input_list.reverse()

	right = list()
	right.append(1)
	for index in range(input_list_size - 1):
		right.append(right[index] * input_list[index])
	right.reverse()

	output_list = list()
	for index in range(input_list_size):
		output_list.append(left[index] * right[index])

	return output_list


inputList = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
print(solution(inputList))
