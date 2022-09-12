from tkinter import * 
from tkinter import ttk
from tkinter import filedialog
from tkinter import colorchooser
import Functions as F

F.Test()
x = F.TestClass("MeoW")
def Test(x):
    print("HI TEST")
    print(str(x))

def P(A):
    pass

#New Window Functions
def NewAccount():
    a = Toplevel(root)
    a.title("New Account Details")
    AType = StringVar(a,"Credit")
    t = ttk.Frame(a)
    ttk.Label(t, text = "Account Name:").grid(row = 0, column = 0)
    ttk.Entry(t).grid(row = 0, column = 1)
    ttk.Label(t, text = "Account Type:").grid(row = 1, column = 0)
    ttk.Radiobutton(t,text = "Credit", variable = AType, value = "Credit", command = lambda:P(AType)).grid(row = 2, column = 0)
    ttk.Radiobutton(t,text = "Debt", variable = AType, value = "Debt", command = lambda:P(AType)).grid(row = 2, column = 1)
    ttk.Button(t, text = "Continue", command = a.destroy).grid(row = 3, column =  2)
    t.grid()

def NewProfile():
    a = Toplevel(root)
    a.title("Profile")
    t = ttk.Frame(a)
    #Name, Location, Password
    ttk.Label(t, text = "Name: ").grid(row = 2, column = 0)
    ttk.Label(t, text = "Password:").grid(row = 3, column = 0)
    ttk.Label(t, text = "Confirm Password:").grid(row = 4, column = 0)
    ttk.Label(t, text = "File Directory:").grid(row = 5, column = 0)
    Name = ttk.Entry(t).grid(row = 2, column = 1)
    Password = ttk.Entry(t).grid(row = 3, column = 1)
    PasswordConfirm = ttk.Entry(t).grid(row = 4, column = 1)
    FileDirectory = ttk.Entry(t).grid(row = 5, column = 1)

    ttk.Button(t, text = "Continue", command = a.destroy).grid(row = 6, column =  2)
    t.grid()

def LoadProfile():
    filename = filedialog.askopenfilename()
    a = Toplevel(root)
    a.title("Profile")
    t = ttk.Frame(a)
    ttk.Label(t, text = "Password").grid(row = 0, column = 0)
    ttk.Entry(t).grid(row = 0, column = 1)
    ttk.Button(t, text = "Continue", command = a.destroy).grid(row = 0, column =  2)
    t.grid()

def NewIncome():
    a = Toplevel(root)
    a.title("New Income Catagory")
    t = ttk.Frame(a)
    
    ttk.Label(t, text = "Catagory Name:").grid(row = 0, column = 0)
    ttk.Entry(t).grid(row = 0, column = 1)

    AType = StringVar(a,"Credit")
    ttk.Label(t, text = "Recurring:").grid(row = 1, column = 0)
    ttk.Radiobutton(t,text = "True", variable = AType, value = "True", command = lambda:P(AType)).grid(row = 2, column = 0)
    ttk.Radiobutton(t,text = "False", variable = AType, value = "False", command = lambda:P(AType)).grid(row = 2, column = 1)
    clr = '#aa0120'
    ttk.Label(t, text = "Color").grid(row = 3, column = 0)
    Button(t,bg = clr,command = lambda: colorchooser.askcolor(initialcolor=clr)).grid(row = 3, column = 1)
    
    
    ttk.Button(t, text = "Continue", command = a.destroy).grid(row = 4, column =  2)
    t.grid()

def NewExpense():
    a = Toplevel(root)
    a.title("New Expense Catagory")
    t = ttk.Frame(a)
    
    ttk.Label(t, text = "Catagory Name:").grid(row = 0, column = 0)
    ttk.Entry(t).grid(row = 0, column = 1)

    AType = StringVar(a,"Credit")
    ttk.Label(t, text = "Recurring:").grid(row = 1, column = 0)
    ttk.Radiobutton(t,text = "True", variable = AType, value = "True", command = lambda:P(AType)).grid(row = 2, column = 0)
    ttk.Radiobutton(t,text = "False", variable = AType, value = "False", command = lambda:P(AType)).grid(row = 2, column = 1)

    clr = '#aa0120'
    ttk.Label(t, text = "Color").grid(row = 3, column = 0)
    Button(t,bg = clr,command = lambda: colorchooser.askcolor(initialcolor=clr)).grid(row = 3, column = 1)
    
    ttk.Button(t, text = "Continue", command = a.destroy).grid(row = 4, column =  2)
    t.grid()
    

    
    

#Test Functions
Accounts = ["Account A", "Account B", "Account C"]
Expenses = ["Expense A","Expense B","Expense C"]
Incomes = ["Income A", "Income B", "Income C"]


root = Tk()
root.title('Personal Finance Tracker')

n = ttk.Notebook(root)
n.grid()
p1 = ttk.Frame(n)
p2 = ttk.Frame(n)
p3 = ttk.Frame(n)
p4 = ttk.Frame(n)
p5 = ttk.Frame(n)
p6 = ttk.Frame(n)
p7 = ttk.Frame(n)

n.add(p1, text = "Overview")
n.add(p2, text = "Income")
n.add(p3, text = "Expenses")
n.add(p4, text = "Accounts Over Time")
n.add(p5, text = "Graphs")
n.add(p6, text = "Reccuring Transactions")
n.add(p7, text = "Records")

#Page 1 Overview

ttk.Label(p1, text = "Account Overview").grid(column = 0, row = 0, sticky = 'w')
ttk.Button(p1, text = "New Profile", command = NewProfile).grid(column = 0, row = 1)
ttk.Button(p1, text = "Load Profile", command = LoadProfile).grid(column = 0, row = 2)
ttk.Button(p1, text = "Record Account Values").grid(column = 5, row = 2)
ttk.Button(p1, text = "Save").grid(column = 5, row = 5)
ttk.Label(p1, text = "Date:").grid(column = 4, row = 1)
ttk.Entry(p1).grid(column = 5, row = 1)
Utilization = ttk.Frame(p1)
Acc = ttk.Frame(p1)
ExpChart = ttk.Frame(p1)

#Accounts and such
ttk.Label(Acc, text = "Accounts").grid(row = 0, column = 0,sticky = 'w')
ttk.Button(Acc, text = "New Account", command = NewAccount).grid(row = 0, column = 1)
ttk.Label(Acc, text = "Credits").grid(row = 1, column = 0,sticky = 'w')
ttk.Label(Acc, text = "Debts").grid(row = 1, column = 1,sticky = 'w')
for i in range(len(Accounts)):
    ttk.Label(Acc, text = Accounts[i]).grid(row = 2+2*i, column = 0,sticky = 'w')
    ttk.Entry(Acc).grid(row = 3+2*i, column = 0)

Utilization.grid(column = 0, row = 4, rowspan = 2)
Acc.grid(column = 2, row = 4, rowspan = 2)
ExpChart.grid(column = 4, row = 4, rowspan = 2)

    


#Page 2 Overview
ttk.Label(p2, text = "Income").grid(row = 0, column = 0, sticky = 'w')
ttk.Button(p2, text = "New Entry",command = NewIncome).grid(row = 0, column = 1)
Fr = ttk.Frame(p2)#for Pie Chart
text =Entry(p2).grid(row =1 , column = 0)
                                        
                                          
columns = ["1","2","3","4"]

ExpenseTable = ttk.Treeview(p2,columns = columns)

ExpenseTable.heading("1", text = "Type 1")
ExpenseTable.heading("2", text = "Type 2")
ExpenseTable.heading("3", text = "Type 3")

ExpenseTable.grid(row = 2,column = 0, sticky = 'nsew')
Fr.grid(row = 2, column = 1) 

#Page 3
ttk.Frame(p3).grid(row = 1, column = 1) #for Pie Chart
ttk.Label(p3, text = "Income").grid(row = 0, column = 0)
ttk.Button(p3, text = "New Entry",command = NewExpense).grid(row = 0, column = 1)

IncomeTable = ttk.Treeview(p3,columns = columns)
IncomeTable.heading("1", text = "Type 1")
IncomeTable.heading("2", text = "Type 2")
IncomeTable.heading("3", text = "Type 3")

IncomeTable.grid(row = 1,column = 0, sticky = 'nse')


#Page 4

ttk.Label(p4, text = "Accounts Over Time").grid(row = 0, column = 0)
ttk.Button(p4, text = "Record Current Values").grid(row = 0, column = 1)
ttk.Frame(p4).grid(row = 1, column = 1) #for Pie Chart
                                        
                                          
columns = ["1","2","3","4"]

AccountTable = ttk.Treeview(p4,columns = columns)

AccountTable.heading("1", text = "Type 1")
AccountTable.heading("2", text = "Type 2")
AccountTable.heading("3", text = "Type 3")

AccountTable.grid(row = 1,column = 0, sticky = 'nse')

#Page 5

#Page 6
ttk.Label(p6, text = "Recurring Transactions").grid(row = 0, column = 0, sticky = 'w')
ttk.Button(p6, text = "New Recurring Transaction").grid(row = 1, column = 5)

G = ttk.Frame(p6)
ttk.Label(G,text = "Recurring Income").grid(row = 0, column = 0, sticky = 'w')
ttk.Label(G,text = "Recurring Expenses").grid(row = 0, column = 3, sticky = 'w')
G.grid(row = 1, column = 0, rowspan = 6)

#Page 7
ttk.Label(p7, text = "Records: ").grid(row = 0, column = 0, sticky = 'w')
Text(p7).grid(row = 1, column = 0)

#ttk.Label(frame,text = "HI").grid(column = 0, row = 0)
#ttk.Button(frame,text = "Quit", command = root.destroy).grid(column = 0, row = 1)

root.mainloop()
