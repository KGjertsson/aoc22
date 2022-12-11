def solve_first():
    with open("input.txt", "r") as f:
        data_raw = f.readlines()

    data = [operation for line in data_raw for operation in line.strip().split(" ")]
    register_values = [(index + 1, 1) for index in range(0, len(data))]
    for index, line in enumerate(data):
        if line == "noop":
            if index > 0:
                register_values[index] = (register_values[index][0], register_values[index - 1][1])
        elif line == "addx":
            if index > 0:
                register_values[index] = (register_values[index][0], register_values[index - 1][1])
        else:
            register_input = int(line)
            register_key = register_values[index][0]
            register_value_new = register_values[index - 1][1] + register_input
            register_values[index] = (register_key, register_value_new)

    total = 0
    for cycle, register_value in register_values:
        if cycle == 20 or (20 + cycle) % 40 == 0:
            prev_val = register_values[cycle - 2][1]
            print(f"cycle = {cycle}, prev_val = {prev_val}, product = {cycle * prev_val}")
            total += cycle * prev_val

    print(total)


def solve_second():
    with open("input.txt", "r") as f:
        data_raw = f.readlines()

    data = [operation for line in data_raw for operation in line.strip().split(" ")]
    register_values = [(index + 1, 1) for index in range(0, len(data))]
    sprite = [["." for _ in range(40)] for _ in range(6)]
    for index, line in enumerate(data):

        current_value = register_values[index - 1][1]
        current_sprite_range = [current_value - 1, current_value, current_value + 1]

        # example: index = 43
        # row = 1
        # col = 2

        sprite_row = index // 40
        sprite_col = index % 40

        if sprite_col in current_sprite_range:
            sprite[sprite_row][sprite_col] = "#"

        if line == "noop":
            if index > 0:
                register_values[index] = (register_values[index][0], register_values[index - 1][1])
        elif line == "addx":
            if index > 0:
                register_values[index] = (register_values[index][0], register_values[index - 1][1])
        else:
            register_input = int(line)
            register_key = register_values[index][0]
            register_value_new = current_value + register_input
            register_values[index] = (register_key, register_value_new)

    for sprite_row in sprite:
        print("".join(sprite_row))


if __name__ == "__main__":
    # solve_first()
    solve_second()
