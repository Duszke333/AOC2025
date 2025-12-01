#!/usr/bin/env ruby


def task1(file_path)
  pos = 50
  cnt = 0
  op = 1
  IO.foreach(file_path) do |line|
    # select operation
    case line.chomp.chars[0]
    when "L"
      op = -1
    when "R"
      op = 1
    end

    # get value
    rot = line.chomp[1..].to_i

    # update position
    pos = (pos + op * rot) % 100
    if pos == 0
      cnt += 1
    end
  end
  cnt
end

def task2(file_path)
  pos = 50
  cnt = 0
  IO.foreach(file_path) do |line|

    # get value
    rot = line.chomp[1..].to_i

    cnt += rot / 100
    steps = rot % 100

    case line.chomp.chars[0]
    when "L"
      nextpos = pos - steps
      if nextpos == 0
        cnt += 1
      elsif nextpos < 0
        if pos != 0
          cnt += 1
        end
        nextpos += 100
      end
    when "R"
      nextpos = pos + steps
      if nextpos >= 100
        cnt += 1
        nextpos -= 100
      end
    end
    pos = nextpos
  end
  cnt
end

if __FILE__ == $0
  file = ARGV[0]
  puts task1(file)
  puts task2(file)
end