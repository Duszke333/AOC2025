import System.Environment
import Data.Char (digitToInt)

twoMax :: String -> (Char, Char)
twoMax []     = error "empty string"
twoMax (c:cs) = go ('\0', c) cs
  where
    go (m1,m2) [] = (m1,m2)
    go (m1,m2) (x:xs)
      | x > m1    = go (x,max m1 m2) xs
      | x == m1   = go (x,max m1 m2) xs
      | otherwise = go (m1,m2) xs

getValue :: String -> Integer
getValue s = 
    let (first, second) = twoMax s
    in fromIntegral (digitToInt first * 10 + digitToInt second)

task1result :: [String] -> Integer 
task1result strs = sum $ map (getValue . reverse) strs

task1 filename = do
    content <- readFile filename
    let packs = lines content
    print $ task1result packs

maxNumber :: Int -> String -> String
maxNumber k digits = go [] digits
  where
    go stack [] = take k stack
    go stack (x:xs)
      | not (null stack) && last stack < x && length stack + length xs >= k = go (init stack) (x:xs)
      | length stack < k = go (stack ++ [x]) xs
      | otherwise = go stack xs 

task2result :: [String] -> Integer
task2result = sum . map (read . maxNumber 12)

task2 filename = do
    content <- readFile filename
    let packs = lines content
    print $ task2result packs
    

main = do
    args <- getArgs
    task1 (args !! 0)
    task2 (args !! 0)