package tt

// MOD 为常量，避免溢出
const MOD int32 = 1000000007

// countGoodArrays 计算符合条件的数组数量
func countGoodArrays(arr []int32) int32 {
	n := int32(len(arr))
	minVal := int32(^uint32(0) >> 1) // 最大的 int32 值 (模拟 Integer.MAX_VALUE)
	maxVal := -minVal                // 最小的 int32 值 (模拟 Integer.MIN_VALUE)

	// 找到非零的最小值和最大值
	for _, num := range arr {
		if num != 0 {
			if num < minVal {
				minVal = num
			}
			if num > maxVal {
				maxVal = num
			}
		}
	}

	// 如果所有元素都是 0 或者值的范围过大，返回 0
	if minVal == int32(^uint32(0)>>1) {
		return 0
	}
	if maxVal-minVal > n-1 {
		return 0
	}

	// 计算整体最小值和最大值
	overallMin := minVal - (n - 1)
	overallMax := maxVal + (n - 1)
	offset := -overallMin
	dpSize := overallMax - overallMin + 1

	// 创建 DP 数组
	previous := make([]int32, dpSize)
	current := make([]int32, dpSize)

	// 初始化第一项
	if arr[0] == 0 {
		for i := int32(0); i < dpSize; i++ {
			previous[i] = 1
		}
	} else {
		mappedVal := arr[0] + offset
		if mappedVal >= 0 && mappedVal < dpSize {
			previous[mappedVal] = 1
		} else {
			return 0
		}
	}

	// 从第二项开始进行动态规划
	for i := int32(1); i < n; i++ {
		// 重置 current 数组
		for j := int32(0); j < dpSize; j++ {
			current[j] = 0
		}

		if arr[i] == 0 {
			// 如果当前值为 0，允许前一个值 +1, 0, -1
			for v := int32(0); v < dpSize; v++ {
				if previous[v] > 0 {
					for change := int32(-1); change <= 1; change++ {
						newV := v + change
						if newV >= 0 && newV < dpSize {
							current[newV] = (current[newV] + previous[v]) % MOD
						}
					}
				}
			}
		} else {
			// 如果当前值非零，处理其映射值
			currentValue := arr[i] + offset
			if currentValue < 0 || currentValue >= dpSize {
				return 0
			}
			total := int32(0)
			for _, prev := range []int32{currentValue - 1, currentValue, currentValue + 1} {
				if prev >= 0 && prev < dpSize {
					total = (total + previous[prev]) % MOD
				}
			}
			current[currentValue] = total
		}

		// 交换 previous 和 current
		previous, current = current, previous
	}

	// 计算最终结果
	result := int32(0)
	if arr[n-1] == 0 {
		for _, val := range previous {
			result = (result + val) % MOD
		}
	} else {
		mappedVal := arr[n-1] + offset
		if mappedVal >= 0 && mappedVal < dpSize {
			result = previous[mappedVal] % MOD
		} else {
			return 0
		}
	}

	return result
}
