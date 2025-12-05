using System;
using System.Collections.Generic;
using System.IO;

class Day5
{
    public struct Range
    {
        public long Lower {get;}
        public long Upper {get;}

        public Range(long lower, long upper)
        {
            Lower = lower;
            Upper = upper;
        }

        public bool IsInRange(long val)
        {
            return Lower <= val && val <= Upper;
        }

        public long CountElements()
        {
            return Upper - Lower + 1;
        }
    }

    static void Main(string[] args)
    {
        Task1(args[0]);
        Task2(args[0]);
    }

    static void Task1(string filename)
    {
        List<string> lines = File.ReadLines(filename).ToList();
        List<Range> ranges = new List<Range>();
        int i;
        for (i = 0; i < lines.Count; i++)
        {
            string line = lines[i];
            if (line == "") break;
            string[] digits = line.Split("-");
            long left = long.Parse(digits[0]);
            long right = long.Parse(digits[1]);
            ranges.Add(new Range(left, right));
        }

        long count = 0;
        for (i = i + 1; i < lines.Count; i++)
        {
            long number = long.Parse(lines[i]);
            foreach (Range rng in ranges)
            {
                if (rng.IsInRange(number))
                {
                    count++;
                    break;
                }
            }
        }
        Console.WriteLine(count);
    }

    static void Task2(string filename)
    {
        List<string> lines = File.ReadLines(filename).ToList();
        List<Range> ranges = new List<Range>();
        int i;
        for (i = 0; i < lines.Count; i++)
        {
            string line = lines[i];
            if (line == "") break;
            string[] digits = line.Split("-");
            long left = long.Parse(digits[0]);
            long right = long.Parse(digits[1]);
            ranges.Add(new Range(left, right));
        }

        var sorted = ranges.OrderBy(r => r.Lower).ToList();
        var merged = new List<Range>();

        Range current = sorted[0];

        foreach (var r in sorted.Skip(1))
        {
            if (r.Lower <= current.Upper)
            {
                current = new Range(current.Lower, Math.Max(current.Upper, r.Upper));
            }
            else
            {
                merged.Add(current);
                current = r;
            }
        }
        merged.Add(current);

        long total = 0;
        foreach (var r in merged)
        {
            total += r.CountElements();
        }
        Console.WriteLine(total);
    }
}