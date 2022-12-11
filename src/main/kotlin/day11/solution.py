from tqdm import tqdm
import numpy as np


class Monkey:

    def __init__(self, name, items, operation, modifier, test_value, monkey1, monkey2):
        self.name = name
        self.items = items
        self.operation = operation
        self.modifier = modifier
        self.test_value = test_value
        self.monkey1 = monkey1
        self.monkey2 = monkey2
        self.inspection_number = 0

    def perform_operation(self, worry_level):
        operand = worry_level if self.modifier == "old" else self.modifier

        if self.operation == "multiply":
            return worry_level * operand
        else:
            return worry_level + operand


def _parse_monkey(monkey_raw):
    monkey_lines = monkey_raw.split("\n")
    name = monkey_lines[0].split(" ")[-1].split(":")[0]
    items = [int(item.strip()) for item in monkey_lines[1].split(":")[1].split(",")]
    operation_raw = monkey_lines[2].split("=")[1]
    operation = "multiply" if "*" in operation_raw else "add"
    modifier = operation_raw.split(" ")[-1]
    if modifier != "old":
        modifier = int(modifier)
    test_value = int(monkey_lines[3].split(" ")[-1])
    monkey1 = int(monkey_lines[4].split(" ")[-1])
    monkey2 = int(monkey_lines[5].split(" ")[-1])

    return Monkey(
        name,
        items,
        operation,
        modifier,
        test_value,
        monkey1,
        monkey2
    )


def solve_first():
    with open("input.txt", "r") as f:
        data = f.read()
    monkeys = [_parse_monkey(monkey_raw) for monkey_raw in data.split("\n\n")]

    for _ in tqdm(range(20)):
        for monkey in monkeys:
            for item in monkey.items:
                new_worry_level = monkey.perform_operation(item)
                new_worry_level = new_worry_level // 3
                if new_worry_level % monkey.test_value == 0:
                    monkeys[monkey.monkey1].items.append(new_worry_level)
                else:
                    monkeys[monkey.monkey2].items.append(new_worry_level)
                monkey.inspection_number += 1
            monkey.items = []

    inspections = sorted([m.inspection_number for m in monkeys], reverse=True)[:2]

    print(inspections)
    print(inspections[0] * inspections[1])


def solve_second():
    with open("input.txt", "r") as f:
        data = f.read()
    monkeys = [_parse_monkey(monkey_raw) for monkey_raw in data.split("\n\n")]
    combined_level = np.prod([m.test_value for m in monkeys])

    for _ in tqdm(range(10000)):
        for monkey in monkeys:
            for item in monkey.items:
                new_worry_level = monkey.perform_operation(item)
                new_worry_level = new_worry_level % combined_level
                if new_worry_level % monkey.test_value == 0:
                    monkeys[monkey.monkey1].items.append(new_worry_level)
                else:
                    monkeys[monkey.monkey2].items.append(new_worry_level)
                monkey.inspection_number += 1
            monkey.items = []

    inspections = sorted([m.inspection_number for m in monkeys], reverse=True)
    print(inspections)
    print(inspections[0] * inspections[1])


if __name__ == "__main__":
    # solve_first()
    solve_second()
