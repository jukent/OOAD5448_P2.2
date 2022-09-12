import tkinter as tk
import datetime as dt
from tkinter import ttk
from tkinter import filedialog
from tkinter import colorchooser



import FileTypes as ft
import Windows as win
import UIElements as ui

class MainGui:

    def __init__(self,master):
        self.master = master
        self.Profile = ft.Profile("Emptyyy","Password","/Users/DavidChaparro/Desktop/Personal Projects/PersonaFinanceProgram")

        ####Initialize Master Frame for all pages#####
        self.frame =  ttk.Notebook(self.master)
        self.p1 = ttk.Frame(self.frame)
        self.p2 = ttk.Frame(self.frame)
        self.p3 = ttk.Frame(self.frame)
        self.p4 = ttk.Frame(self.frame)
        self.p5 = ttk.Frame(self.frame)
        self.p6 = ttk.Frame(self.frame)
        self.p7 = ttk.Frame(self.frame)
        self.p8 = ttk.Frame(self.frame)
        self.p9 = ttk.Frame(self.frame)
        self.p10 = ttk.Frame(self.frame)
        self.frame.add(self.p1, text = "Overview")
        self.frame.add(self.p2, text = "Income")
        self.frame.add(self.p3, text = "Expenses")
        self.frame.add(self.p4, text = "Savings")
        self.frame.add(self.p5, text = "Vacations")
        self.frame.add(self.p6, text = "Groupings")
        self.frame.add(self.p7, text = "Accounts Over Time")
        self.frame.add(self.p8, text = "Graphs")
        self.frame.add(self.p9, text = "Reccuring Transactions")
        self.frame.add(self.p10, text = "Records")

        ##### Page 1: Overview #####
        ttk.Label(self.p1, text = "Account Overview").grid(column = 0, row = 0, sticky = 'w')
        ttk.Label(self.p1, text = "Current Profile: "+self.Profile.name).grid(column = 0, row = 1, sticky = 'w')
        ttk.Button(self.p1, text = "New Profile",command = self.New_Profile).grid(column = 0, row = 2)
        ttk.Button(self.p1, text = "Load Profile",command = self.Load_Profile).grid(column = 0, row = 3)
        ttk.Button(self.p1, text = "Save",command = self.BKSave).grid(column = 1, row = 2)

        self.Acc = ttk.Button(self.p1, text = "Test",command = self.test).grid(column = 1, row = 3)

        self.Utilization = ui.Utilization(self.p1,self.Profile.incexpcat,self.UpdateUtilization,dt.date.today())
        self.ACCLIST = ui.AccountList(self.p1,self.Profile.accounts,self.New_Account,self.Del_Account,self.BKRecAcc,dt.date.today())
        self.MainExpenseGraph = ui.CircleGraph(self.p1,"Expenses",self.Profile,self.UpdateMainExpenseGraph,dt.date.today())
        
        self.Utilization.F.grid(column = 0, row = 4)
        self.ACCLIST.Acc.grid(column = 1, row = 4)
        self.MainExpenseGraph.F.grid(column = 2, row = 4)

        ##### Page 2: Income #####
        self.IncomeTable = ui.IncExpGrid(self.p2,"Income",self.Profile.incexpcat,self.New_IncExp,self.Del_IncExp,self.BKRecIncExp,dt.date.today(),self.BKNewDateIncExp)
        self.IncomeGraph = ui.CircleGraph(self.p2,"Incomes",self.Profile,self.UpdateIncomeGraph,dt.date.today())
        self.IncomeTable.IEGrid.grid(row = 0,column = 0, sticky = 'nsew')
        self.IncomeGraph.F.grid(row = 0, column = 1)

        ##### Page 3: Expenses #####
        self.ExpenseTable = ui.IncExpGrid(self.p3,"Expense",self.Profile.incexpcat,self.New_IncExp,self.Del_IncExp,self.BKRecIncExp,dt.date.today(),self.BKNewDateIncExp)
        self.ExpenseGraph = ui.CircleGraph(self.p3,"Expenses",self.Profile,self.UpdateExpenseGraph,dt.date.today())
        self.ExpenseTable.IEGrid.grid(row = 0,column = 0, sticky = 'nsew')
        self.ExpenseGraph.F.grid(row = 0, column = 1)


        ##### Page 4: Savings #####
        self.SavingsTable = ui.IncExpGrid(self.p4,"Savings",self.Profile.incexpcat,self.New_IncExp,self.Del_IncExp,self.BKRecIncExp,dt.date.today(),self.BKNewDateIncExp)
        self.SavingsGraph = ui.CircleGraph(self.p4,"Savings",self.Profile,self.UpdateSavingsGraph,dt.date.today())
        self.SavingsGraph.F.grid(row = 0, column = 1)
        self.SavingsTable.IEGrid.grid(row = 0,column = 0, sticky = 'nsew')

        #####Page 5: Vacations #####
        self.VacationTable = ui.VacationGrid(self.p5,self.Profile.vacations,self.profile.vaccats,self.BKNewVacCat,self.BKDel_VacCat,self.BKRecVacation,self.BKNewVac,self.BKDel_Vac,predate)
        self.VacationGraph = ui.CircleGraph(self.p5,"Vacation",self.Profile)


        ##### Page 6: Groupings #####
        ttk.Label(self.p6, text = "Recurring Transactions").grid(row = 0, column = 0, sticky = 'w')

        self.G = ttk.Frame(self.p6)
        ttk.Label(self.G,text = "Recurring Income").grid(row = 0, column = 0, sticky = 'w')
        ttk.Label(self.G,text = "Recurring Expenses").grid(row = 0, column = 3, sticky = 'w')
        ttk.Button(self.G, text = "New Recurring Transaction").grid(row = 0, column = 5)

        self.G.grid(row = 1, column = 0, rowspan = 6)

        #####Page 7#####
        ttk.Label(self.p7, text = "Records: ").grid(row = 0, column = 0, sticky = 'w')
        self.RecordSheet = tk.Text(self.p7)

        #####
        self.TimeChart = ttk.Frame(self.p4)
        ttk.Label(self.p4, text = "Accounts Over Time").grid(row = 0, column = 0)
        ttk.Button(self.p4, text = "Record Current Values").grid(row = 0, column = 1)

        self.TimeChart.grid(row = 1, column = 1)


        self.RecordSheet.grid(row = 1, column = 0)
        #.grid all items to make them real
        self.frame.grid()

        


    #####New Window Functions#####
    def New_Profile(self): #CHECK#
        self.NewWindow = tk.Toplevel(self.master)
        self.NewWindow.title("New Profile")
        self.app = win.WinNewProfile(self.NewWindow,self.BKOverwrite_Profile)

    def Load_Profile(self):
        self.NewWindow = tk.Toplevel(self.master)
        self.NewWindow.title("Load Profile")
        self.app = win.WinLoadProfile(self.NewWindow,self.BKOverwrite_Profile)

    def New_Account(self):
        self.NewWindow = tk.Toplevel(self.master)
        self.NewWindow.title("New Account Details")
        self.app = win.WinNewAccount(self.NewWindow,self.BKNew_Acc)

    def Del_Account(self):
        self.NewWindow = tk.Toplevel(self.master)
        self.NewWindow.title("New Account Details")
        self.app = win.WinDelAccount(self.NewWindow,self.Profile.accounts,self.BKDel_Acc)

    def New_IncExp(self,Type):
        self.NewWindow = tk.Toplevel(self.master)
        self.NewWindow.title("New Income/Expense")
        self.app = win.WinNewIncExp(self.NewWindow,self.BKNewIncExp,Type)

    def Del_IncExp(self):
        self.NewWindow = tk.Toplevel(self.master)
        self.NewWindow.title("New Income")
        self.app = win.WinDelIncExp(self.NewWindow,self.Profile.incexpcat,self.BKDel_IncExp)

    #####PassBack Functions#####

    def BKOverwrite_Profile(self,NewProfile):
        self.Profile = NewProfile
        self.UpdateAll()

    def BKNewIncExp(self,name,Type,Color):
        self.Profile.New_IncExp_Catagory(name,Type,Color)
        self.UpdateExpense()
        self.UpdateIncome()
        self.UpdateSavings()
        self.UpdateSavingsGraph()
        self.UpdateIncomeGraph()
        self.UpdateExpenseGraph()

    def BKDel_IncExp(self,name):
        self.Profile.Del_IncExp_Catagory(name)
        self.UpdateExpense()
        self.UpdateIncome()
        self.UpdateSavings()
        self.UpdateSavingsGraph()
        self.UpdateIncomeGraph()
        self.UpdateExpenseGraph()

    def BKNewDateIncExp(self,Type,date):
        List = list(self.Profile.incexpcat.keys())
        for i in List:
            if self.Profile.incexpcat[i].type == Type:
                self.Profile.incexpcat[i].New_Value(date,0)
        self.UpdateExpense()
        self.UpdateIncome()
        self.UpdateSavings()
        self.UpdateSavingsGraph()
        self.UpdateIncomeGraph()
        self.UpdateExpenseGraph()

    def BKRecIncExp(self,cat,date,value):
        self.Profile.incexpcat[cat].New_Value(date,value)
        self.UpdateExpense()
        self.UpdateIncome()
        self.UpdateSavings()
        self.UpdateSavingsGraph()
        self.UpdateIncomeGraph()
        self.UpdateExpenseGraph()

    def BKNew_Acc(self,name,Type,Color):
        self.Profile.New_Account(name,Type,Color)
        self.UpdateAcc()

    def BKDel_Acc(self,name):
        self.Profile.Delete_Account(name)
        self.UpdateAcc()

    def BKRecAcc(self,Account,Date,Value):
        self.Profile.accounts[Account].New_Value(Date,Value)

    def BKSave(self):
        self.Profile.Save_Profile(self.Profile.directory)

    def BKNewVacCat(self):
        self.Profile.
    def BKDel_VacCat(self):
    def BKRecVacation(self):
    def BKNewVac(self):
    def BKDel_Vac(self):


    def test(self):
        print(self.Profile.accounts)


    ### UI Refresher Functions
    def UpdateAcc(self):
        predate = self.ACCLIST.date.get()
        self.ACCLIST.Acc.destroy()
        self.ACCLIST = ui.AccountList(self.p1,self.Profile.accounts,self.New_Account,self.Del_Account,self.BKRecAcc, predate)
        self.ACCLIST.Acc.grid(column = 1, row = 4, rowspan = 2)

    def UpdateIncome(self):
        ID = self.IncomeTable.date.get()
        self.IncomeTable.IEGrid.destroy()
        self.IncomeTable = ui.IncExpGrid(self.p2,"Income",self.Profile.incexpcat,self.New_IncExp,self.Del_IncExp,self.BKRecIncExp,ID,self.BKNewDateIncExp)
        self.IncomeTable.IEGrid.grid(row = 0,column = 0, sticky = 'nsew')

    def UpdateExpense(self):
        ED = self.ExpenseTable.date.get()
        self.ExpenseTable.IEGrid.destroy()
        self.ExpenseTable = ui.IncExpGrid(self.p3,"Expense",self.Profile.incexpcat,self.New_IncExp,self.Del_IncExp,self.BKRecIncExp,ED,self.BKNewDateIncExp)
        self.ExpenseTable.IEGrid.grid(row = 0,column = 0, sticky = 'nsew')

    def UpdateSavings(self):
        ED = self.SavingsTable.date.get()
        self.SavingsTable.IEGrid.destroy()
        self.SavingsTable = ui.IncExpGrid(self.p4,"Savings",self.Profile.incexpcat,self.New_IncExp,self.Del_IncExp,self.BKRecIncExp,ED,self.BKNewDateIncExp)
        self.SavingsTable.IEGrid.grid(row = 0,column = 0, sticky = 'nsew')

    def UpdateUtilization(self):
        predate = self.Utilization.Date.get()
        self.Utilization.F.destroy()
        self.Utilization = ui.Utilization(self.p1,self.Profile.incexpcat,self.UpdateUtilization,predate)
        self.Utilization.F.grid(column = 0, row = 4)

    def UpdateExpenseGraph(self):
        predate = self.ExpenseGraph.Date.get()
        self.ExpenseGraph.F.destroy()
        self.ExpenseGraph = ui.CircleGraph(self.p3,"Expenses",self.Profile,self.UpdateExpenseGraph,predate)
        self.ExpenseGraph.F.grid(row = 0, column = 1)

    def UpdateMainExpenseGraph(self):
        predate = self.MainExpenseGraph.Date.get()
        self.MainExpenseGraph.F.destroy()
        self.MainExpenseGraph = ui.CircleGraph(self.p1,"Expenses",self.Profile,self.UpdateMainExpenseGraph,predate)
        self.MainExpenseGraph.F.grid(column = 2, row = 4)

    def UpdateIncomeGraph(self):
        predate = self.IncomeGraph.Date.get()
        self.IncomeGraph.F.destroy()
        self.IncomeGraph = ui.CircleGraph(self.p2,"Incomes",self.Profile,self.UpdateIncomeGraph,predate)
        self.IncomeGraph.F.grid(row = 0, column = 1)

    def UpdateSavingsGraph(self):
        predate = self.SavingsGraph.Date.get()
        self.SavingsGraph.F.destroy()
        self.SavingsGraph = ui.CircleGraph(self.p4,"Savings",self.Profile,self.UpdateSavingsGraph,predate)
        self.SavingsGraph.F.grid(row = 0, column = 1)


    def UpdateAll(self):
        self.UpdateAcc()
        self.UpdateExpense()
        self.UpdateIncome()
        self.UpdateSavings()
        self.UpdateUtilization()
        self.UpdateSavingsGraph()
        self.UpdateIncomeGraph()
        self.UpdateMainExpenseGraph()
        self.UpdateExpenseGraph()



def main():
    root = tk.Tk()
    app = MainGui(root)
    root.mainloop()

if __name__ == '__main__':
    main()
