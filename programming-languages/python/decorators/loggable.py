def loggable(cls):

    # Define a log method to add to the class
    def log(self, msg):
        print(msg)

    # Add the log method to the class
    cls.log = log

    # Return the modified class
    return cls