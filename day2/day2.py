import sys
import math

def countDigits(n):
    if n == 0: return 1
    return math.floor(math.log10(n)) + 1

def buildNum(digit, numDigits):
    return sum(digit * 10 ** n for n in range(numDigits))

def task1(filename: str):
    with open(filename) as f:
        ranges = f.readline().strip().split(',')

    total = 0

    for rng in ranges:
        lower, upper = rng.split('-')
        lower = int(lower)
        upper = int(upper)
        curr = lower
        if (countDigits(curr) % 2 == 1 and countDigits(curr) != 1):
            curr = 10 ** countDigits(curr)
        hp = int(curr // 10**(countDigits(curr) / 2))
        lp = int(curr % 10**(countDigits(curr) / 2))
        num = 0
        num = hp * 10**countDigits(hp) + hp
        while num <= upper:
            if num >= lower:
                total += num
            hp += 1
            num = hp * 10**countDigits(hp) + hp

    print(total)

def getDivisors(n):
    divisors = []
    for i in range(1, n // 2 + 1):
        if n % i == 0:
            divisors.append(i)
    return divisors

def repeatNum(num: int, targetLen: int):
    digits = countDigits(num)
    reps = targetLen // digits
    shift = 10 ** digits
    return num * ((shift ** reps - 1) // (shift - 1))

def task2(filename: str):
    with open(filename) as f:
        ranges = f.readline().strip().split(',')

    total = 0

    for rng in ranges:
        lower, upper = rng.split('-')
        found = set()
        lowint = int(lower)
        upint = int(upper)
        for span in range(len(lower), len(upper)+1):
            steps = getDivisors(span)
            baseline = max(int(lower), 10 ** (span - 1))
            for step in steps:
                part = baseline // (10 ** (span - step))
                while countDigits(part) <= step:
                    possible = repeatNum(part, span)
                    if lowint <= possible <= upint: 
                        found.add(possible)
                    part += 1
        total += sum(found)
    print(total)


if __name__ == '__main__':
    task1(sys.argv[1])
    task2(sys.argv[1])