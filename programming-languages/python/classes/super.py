class Grandparent:
    def __init__(self):
        self.name = "Grandpa"

    def method(self):
        print("Grandparent method")

class Parent(Grandparent):
    def method(self):
        print("Parent method")
        super().method()

class Child(Parent):
    def method(self):
        print("Child method")
        super().method()
        print("Calling Grandpa directly")
        super(Parent, self).method() # calls the method of the grandparent class
        print(self.name) # looks up the attribute of the grandparent class 

# Usage
child = Child()
child.method()
