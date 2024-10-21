class Color:
    def __init__(self, r: int, g: int, b: int):
        # 检查r的范围
        if not (0 <= r <= 255):
            raise ValueError(f"Value of r should be between 0 and 255")
        # 检查g的范围
        if not (0 <= g <= 255):
            raise ValueError(f"Value of g should be between 0 and 255")
        # 检查b的范围
        if not (0 <= b <= 255):
            raise ValueError(f"Value of b should be between 0 and 255")

        # 如果值在范围内，初始化颜色值
        self.r = r
        self.g = g
        self.b = b

    def __repr__(self):
        # 返回颜色的十六进制字符串表示
        return f"#{self.r:02X}{self.g:02X}{self.b:02X}".lower()

# 测试用例
try:
    c = Color(10, 100, 1)
    print(c)  # 应该输出: #0a6401
except ValueError as e:
    print(e)

try:
    c = Color(10, -10, 25)
    print(c)
except ValueError as e:
    print(e)  # 应该输出: Value of g should be between 0 and 255
