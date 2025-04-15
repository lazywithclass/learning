from decorators.loggable import loggable


@loggable
class Target:

    def __init__(self):
        self.answer = 42

    def add_one(self):
        self.answer += 1
        self.log(f"answer is {self.answer}")

    def get_answer(self):
        return self.answer

if __name__ == "__main__":
    target = Target()
    target.add_one()
    print(target.get_answer())