import datetime as dt

def Test():
    print("HI")

class TestClass:
    def __init__(self,text):
        print("HI")
        self.text = text
        print(self.text)

class Profile:
    def __init__(self, name, password):
        self.name = name
        self.password = password
        self.date_started =  dt.today()

class Expense:
    def __init__(self,name):
        self.catagory = name
        self.currentvalue = 0
        self.pastvalues = {}
        self.recurring = "False"

    def New_Value(self,date,expense):
        self.pastvalues[date] = expense

    def Delete_Value(self,date):
        del self.pastvalues[date]

    def Change_Value(self,date,expense):
        if date in self.pastvalues:
            self.pastvalues[date] = expense

class Income:
    def __init__(self,name):
        self.catagory = name
        self.currentvalue = 0
        self.pastvalues = {}
        self.recurring = "False"

    def New_Value(self,date,income):
        self.pastvalues[date] = income

    def Delete_Value(self,date):
        del self.pastvalues[date]

    def Change_Value(self,date,income):
        if date in self.pastvalues:
            self.pastvalues[date] = income

class Account:
    def __init__(self,name):
        self.name = name
        self.type = "Credit"
        self.startdate = dt.date
        self.currentvalue = 0
        self.pastvalues = {}

    def New_Value(self,date,value):
        self.pastvalues[date] = value

    def Delete_Value(self,date):
        del self.pastvalues[date]

    def Change_Value(self,date,value):
        if date in self.pastvalues:
            self.pastvalues[date] = value

class Dates:
    def __init__(self):
        self.dates = []

    def New_Date(self,date):
        self.dates.append(date)

#New Profile Function

#Save Profile to File

#Load Profile Function

#Record Values

#New Account

#Delete Account

#New Expense

#Delete Expense

#New Income

#Delete Income

#New Recurring Transaction

#Delete Recurring Transaction

#Save Records



if __name__ == '__main__':
    print("HI:")
    
