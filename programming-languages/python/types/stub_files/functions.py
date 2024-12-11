# in pycharm at the left of the function definition line there's a *, clicking on
# that you can go to the stub file
def sum(n, m):
    return n + m

# notice that the following line gives a type warning
# even though we don't have a type hint for the function
sum("ciao", 1)


def check_non_empty_string(s):
    if not s:
        raise ValueError("Empty string")
    return s


def has_all_vowels(s):
    return all(c in s for c in "aeiou")


nes = check_non_empty_string("ciao")
has_all_vowels(nes)

# we could use nominal typing, this could be useful for example in just checking
# once that the string is not empty
# (I know the example is silly, but it's just to show the concept)
has_all_vowels("mmmh")
