package tt

import "fmt"

// countCreatorCommunities 计算创作者社区的数量
func countCreatorCommunities(related []string) int32 {
	n := len(related)
	visited := make([]bool, n) // 创建访问标记数组
	communityCount := 0

	for i := 0; i < n; i++ {
		if !visited[i] {
			communityCount++
			dfs(related, visited, i)
		}
	}

	return int32(communityCount)
}

// dfs 深度优先搜索
func dfs(related []string, visited []bool, creator int) {
	visited[creator] = true
	for i := 0; i < len(related); i++ {
		// 如果 creator 和 i 是连接的并且 i 没有被访问过，则继续递归
		if related[creator][i] == '1' && !visited[i] {
			dfs(related, visited, i)
		}
	}
}

func main() {
	// 示例数据
	related := []string{
		"1100",
		"1110",
		"0110",
		"0001",
	}

	// 输出创作者社区的数量
	fmt.Println(countCreatorCommunities(related))
}
