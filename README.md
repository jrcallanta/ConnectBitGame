## Intro
This command-line game was created by myself. Any resemblance to another existing game is completely unintentional.
I found it interesting that flipping bits could connect to seemingly distant integers, and I figured why not
make a game out of it. Similar to that of connect-the-dots, this game is based on following a path that
may not be instantly clear. What makes it more challenging is the possibility of multiple solutions.

## Getting Started
Make sure Java is installed. From the root directory, `cd src && javac Main.class && java Main`.

## The Game
```
[GOAL]: You are given a list of integers,
  all of which are representable by 4 bits.
  Arrange the integers such that any two
  consecutive integers only differ by 1 bit.
  The first and last integers will always be
  in their correct location.

[INSTRUCTIONS]: To submit an answer, simply
  type in a space-separated list of integers
  in any order you think satisfies
  the conditions.

  ex:   Given the following list: 
        [3  4  7  6  5]
        3  -  -  -  5

        The binary representations are
        3 -> 0011   4 -> 0100   5 -> 0101
        6 -> 0110   7 -> 0111

        3 is one bit away from 7, 0011 -> 0111
        7 is one bit away from 6, 0111 -> 0110
        6 is one bit away from 4, 0110 -> 0100
        4 is one bit away from 5, 0100 -> 0101

        Therefore, the solution is [3 7 6 4 5]
```
