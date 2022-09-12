import tkinter as tk
import datetime as dt
from tkinter import RIDGE, Variable, ttk
from tkinter import filedialog
from tkinter import colorchooser
import numpy as np
from sys import platform as OS 
import FileTypes as ft
from matplotlib.figure import Figure as Fig
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import (FigureCanvasTkAgg, 
NavigationToolbar2Tk)


##### UI Elements #####

##class SpreadSheet:
class AccountList:
    def __init__(self,master,Accounts,NewACCCom,DelACCCom,RecCom,prevdate):
        self.master = master
        self.Acc = ttk.Frame(self.master)
        self.RecCom = RecCom
        LengthA = 0
        LengthD = 0
        
        self.prevdate = prevdate

        #Test Values
        self.ACKeys = list(Accounts.keys())
        self.Accs = Accounts

        #Find Most recent date
        tempdates = []
        for i in range(len(self.ACKeys)):
            for j in self.Accs[self.ACKeys[i]].dates:
                if j not in tempdates:
                    tempdates.append(j)
        now = dt.date.today()


        try:
            tempdates.sort(reverse=True)
            newest = tempdates[0]
            if newest < str(now): self.date = tk.StringVar(value = newest)
            if str(prevdate) != newest: self.date = tk.StringVar(value = prevdate)
        except ValueError: self.date = tk.StringVar(value = prevdate)
        except IndexError: self.date = tk.StringVar(value = prevdate)

        ttk.Label(self.Acc, text = "Accounts").grid(row = 0, column = 0,sticky = 'w')

        ttk.Label(self.Acc, text = "Date:").grid(column = 0, row = 1,sticky = 'w')
        ttk.Entry(self.Acc,textvariable=self.date).grid(column = 1, row = 1,sticky = 'w')
        ttk.Button(self.Acc, text = "Record Account Values",command = self.RecordAccounts).grid(column = 2, row = 1)
        
        ttk.Button(self.Acc, text = "New Account",command = NewACCCom).grid(row = 2, column = 0)
        ttk.Button(self.Acc, text = "Delete Account",command = DelACCCom).grid(row = 2, column = 1)
        ttk.Label(self.Acc, text = "Assets").grid(row = 3, column = 0,sticky = 'w')
        ttk.Label(self.Acc, text = "Debts").grid(row = 3, column = 1,sticky = 'w')
            

        self.AccVars = [tk.StringVar(value =  "0") for i in range(len(self.ACKeys))]
        for i in range(len(self.ACKeys)):
            if self.Accs[self.ACKeys[i]].type == 'Asset':
                try: self.AccVars[i] = tk.StringVar(value = self.Accs[self.ACKeys[i]].values[newest])
                except: self.AccVars[i] = tk.StringVar(value = "0")
                ttk.Label(self.Acc, text = self.ACKeys[i]).grid(row = 4+2*LengthA, column = 0,sticky = 'w',columnspan = 1)
                ttk.Entry(self.Acc,textvariable=self.AccVars[i]).grid(row = 5+2*LengthA, column = 0,columnspan = 1)
                LengthA += 2
            if self.Accs[self.ACKeys[i]].type == 'Debt':
                try: self.AccVars[i] = tk.StringVar(value = self.Accs[self.ACKeys[i]].values[newest])
                except: self.AccVars[i] = tk.StringVar(value = "0")
                ttk.Label(self.Acc, text = self.ACKeys[i]).grid(row = 4+2*LengthD, column = 1,sticky = 'w',columnspan = 1)
                ttk.Entry(self.Acc,textvariable=self.AccVars[i]).grid(row = 5+2*LengthD, column = 1,columnspan = 1)
                LengthD += 2
                
    def RecordAccounts(self):
        InputDate = self.date.get()
        for i in range(len(self.ACKeys)):
            Account = self.ACKeys[i]
            Value = self.AccVars[i].get()
            self.RecCom(Account,InputDate,Value)
        
class IncExpGrid:
    def __init__(self,master,Type,IncExps,NewCat,DeleteCat,RecordVal,prevdate,newrow):
        self.Cats = list(IncExps.keys())
        self.IEGrid = ttk.Frame(master)
        self.newrow = newrow
        self.Type = Type
        self.IncExps = IncExps
        self.RecIncExp = RecordVal
        

        self.prevdate = prevdate

        #Find Most recent date
        tempdates = []
  
        Dates = []
        Cats = []

        
        self.date = tk.StringVar(value = self.prevdate)

        for names in self.Cats:
            if IncExps[names].type == Type:
                Cats.append(names)
                TempDs = list(IncExps[names].values.keys()) #Could append directly to library
                for i in range(len(TempDs)): #And Remove duplicates somehow easily
                    if TempDs[i] not in Dates:
                        Dates.append(TempDs[i]) #List of all catagories and dates

        Dates.sort(reverse=True)
        self.Dates = Dates
        self.Cats = Cats

        ttk.Label(self.IEGrid, text = Type).grid(row = 0, column = 0, sticky = 'w')
        ttk.Button(self.IEGrid, text = "New Entry Date",command = self.New_Date).grid(row = 1, column = 0)
        ttk.Entry(self.IEGrid,textvariable = self.date).grid(row =1 , column = 1)
        ttk.Button(self.IEGrid, text = "Record Values: ",command = self.Record_Incexp).grid(row = 1, column = 4)
        ttk.Button(self.IEGrid, text = "New Catagory",command = lambda: NewCat(self.Type)).grid(row = 1, column = 2)
        ttk.Button(self.IEGrid, text = "Delete Catagory",command = DeleteCat).grid(row = 1, column = 3)

        #Start of main grid of expenses
        h=tk.Scrollbar(self.IEGrid, orient='horizontal')

        RS = 2

        #GridVal[Column][Row]
        self.GridVal = [[tk.StringVar(value = "0") for j in range(len(Dates))] for i in range(len(Cats)+2)]
        Grid = [[ttk.Entry(self.IEGrid,textvariable = self.GridVal[i][j]) for j in range(len(Dates))] for i in range(len(Cats)+2)]

        height = len(Dates)+1
        width = len(Cats)+2


        for j in range(height): #Rows
            Sum = 0
            for i in range(width): #Columns
                if j == 0:
                    if i ==0:
                        ttk.Label(self.IEGrid,text = "Dates").grid(row = RS+j, column = 0)
                    if i == width-1:
                        ttk.Label(self.IEGrid,text = "Total").grid(row = RS+j, column = len(Cats)+1)
                    if len(Cats)!=0 and i != width-1 and i != 0:
                        ttk.Label(self.IEGrid,text = Cats[i-1]).grid(row = RS+j, column = i)
                if j != 0:
                    k = j-1
                    if i == 0:
                        self.GridVal[i][k] = tk.StringVar(value = self.Dates[k])
                        Grid[i][k] = ttk.Entry(self.IEGrid,textvariable =self.GridVal[i][k]).grid(row = RS+j,column = i)
                    if i == width-1:
                        #Sum = sum([float(self.GridVal[i][k].get()) for i in range(1,len(self.GridVal[:][k])-1)])
                        self.GridVal[i][k] = tk.StringVar(value = Sum)
                        Grid[i][k]= ttk.Entry(self.IEGrid,textvariable = self.GridVal[i][k]).grid(row = RS+j,column = i)
                    if len(Cats)!=0 and i != width-1 and i != 0:
                        try: Val = self.IncExps[Cats[i-1]].values[self.Dates[k]]
                        except KeyError: Val = 0
                        self.GridVal[i][k] = tk.StringVar(value = Val)
                        Sum += float(Val)
                        Grid[i][k] = ttk.Entry(self.IEGrid,textvariable = self.GridVal[i][k] ).grid(row = RS+j,column = i)


        #h.config(command=text.xview)     
        #h.pack(side='bottom', fill='x')
    def New_Date(self):
        self.newrow(self.Type,self.date.get())

    def Record_Incexp(self):
        height = len(self.Dates)
        width = len(self.Cats)+1

        date = ''
        cat = ''

        for j in range(height): #Rows
            for i in range(width): #Columns
                if i == 0:
                    date = self.GridVal[i][j].get()
                if len(self.Cats)!=0 and i != 0:
                    cat = self.Cats[i-1]
                    value = self.GridVal[i][j].get()
                    self.RecIncExp(cat,date,value)

class CircleGraph:
    def __init__(self,master,Type,Profile,update,prevdate):
        self.master = master
        self.Profile = Profile
        self.Type = Type
        self.Update = update
        self.F = ttk.Frame(self.master)
        self.Date = tk.StringVar(value = prevdate)
        self.DateList = []
        self.Predate = prevdate

        if self.Type == "Expenses":
            IncExps = self.Profile.incexpcat
            Cats = list(self.Profile.incexpcat.keys())

            #Date manipulation for cbox
            for names in Cats:
                if IncExps[names].type == "Expense":
                    TempDs = list(IncExps[names].values.keys()) #Could append directly to library
                    for i in range(len(TempDs)): #And Remove duplicates somehow easily
                        if TempDs[i] not in self.DateList:
                            self.DateList.append(TempDs[i]) #List of all catagories and dates
            
            if not self.DateList:
                self.Date = tk.StringVar(value = self.Predate)
            if self.DateList and self.Date.get() != str(dt.date.today()):
                self.Date = tk.StringVar(value = self.Predate)
            if self.DateList and self.Date.get() == str(dt.date.today()):
                self.Date = tk.StringVar(value = max(self.DateList))
        
            self.DateList.sort(reverse=True)


            CBox = ttk.Combobox(self.F,textvariable = self.Date,validatecommand=self.Update,validate='focusout')
            CBox['values'] = self.DateList
            CBox.current() 

            dato = self.Date.get()
            Values = []
            Labels = []
            Colors = []

            for names in Cats:
                INCEXP = IncExps[names]
                if INCEXP.type == "Expense":
                    Labels.append(names)
                    Colors.append(INCEXP.color)
                    try: Values.append(INCEXP.values[dato])
                    except KeyError: Values.append(0)


            #fig = Fig(figsize = (2, 2),dpi = 100)
            # adding the subplot
            figure, axes = plt.subplots(figsize = (2,2))
            plt.pie(Values,labels = Labels,colors = Colors,startangle = 90,counterclock = False)
            Circ = plt.Circle((0,0), 0.7, color='white')
            plt.title("Expense Pie Chart")
            plt.legend()

 
            axes.set_aspect( 1 )
            axes.add_artist( Circ )
            # creating the Tkinter canvas
            # containing the Matplotlib figure
            canvas = FigureCanvasTkAgg(figure,master = self.F)  
            canvas.draw()
            canvas.get_tk_widget().grid(row = 1,column = 0) # placing the canvas on the Tkinter window
            CBox.grid(row = 0,column = 0)

        if self.Type == "Incomes":
            IncExps = self.Profile.incexpcat
            Cats = list(self.Profile.incexpcat.keys())

            #Date manipulation for cbox
            for names in Cats:
                if IncExps[names].type == "Income":
                    TempDs = list(IncExps[names].values.keys()) #Could append directly to library
                    for i in range(len(TempDs)): #And Remove duplicates somehow easily
                        if TempDs[i] not in self.DateList:
                            self.DateList.append(TempDs[i]) #List of all catagories and dates
            
            if not self.DateList:
                self.Date = tk.StringVar(value = self.Predate)
            if self.DateList and self.Date.get() != str(dt.date.today()):
                self.Date = tk.StringVar(value = self.Predate)
            if self.DateList and self.Date.get() == str(dt.date.today()):
                self.Date = tk.StringVar(value = max(self.DateList))
            
            self.DateList.sort(reverse=True)


            CBox = ttk.Combobox(self.F,textvariable = self.Date,validatecommand=self.Update,validate='focusout')
            CBox['values'] = self.DateList
            CBox.current() 

            dato = self.Date.get()
            Values = []
            Labels = []
            Colors = []

            for names in Cats:
                INCEXP = IncExps[names]
                if INCEXP.type == "Income":
                    Labels.append(names)
                    Colors.append(INCEXP.color)
                    try: Values.append(INCEXP.values[dato])
                    except KeyError: Values.append(0)


            #fig = Fig(figsize = (2, 2),dpi = 100)
            # adding the subplot
            figure, axes = plt.subplots(figsize = (2,2))
            plt.pie(Values,labels = Labels,colors = Colors,startangle = 90,counterclock = False)
            Circ = plt.Circle((0,0), 0.7, color='white')
            plt.title("Incomes Pie Chart")
            plt.legend()

 
            axes.set_aspect( 1 )
            axes.add_artist( Circ )
            # creating the Tkinter canvas
            # containing the Matplotlib figure
            canvas = FigureCanvasTkAgg(figure,master = self.F)  
            canvas.draw()
            canvas.get_tk_widget().grid(row = 1,column = 0) # placing the canvas on the Tkinter window
            CBox.grid(row = 0,column = 0)

        if self.Type == "Savings":
            IncExps = self.Profile.incexpcat
            Cats = list(self.Profile.incexpcat.keys())

            #Date manipulation for cbox
            for names in Cats:
                if IncExps[names].type == "Saving":
                    TempDs = list(IncExps[names].values.keys()) #Could append directly to library
                    for i in range(len(TempDs)): #And Remove duplicates somehow easily
                        if TempDs[i] not in self.DateList:
                            self.DateList.append(TempDs[i]) #List of all catagories and dates
            
            if not self.DateList:
                self.Date = tk.StringVar(value = self.Predate)
            if self.DateList and self.Date.get() != str(dt.date.today()):
                self.Date = tk.StringVar(value = self.Predate)
            if self.DateList and self.Date.get() == str(dt.date.today()):
                self.Date = tk.StringVar(value = max(self.DateList))
            
            self.DateList.sort(reverse=True)


            CBox = ttk.Combobox(self.F,textvariable = self.Date,validatecommand=self.Update,validate='focusout')
            CBox['values'] = self.DateList
            CBox.current() 

            dato = self.Date.get()
            Values = []
            Labels = []
            Colors = []

            for names in Cats:
                INCEXP = IncExps[names]
                if INCEXP.type == "Saving":
                    Labels.append(names)
                    Colors.append(INCEXP.color)
                    try: Values.append(INCEXP.values[dato])
                    except KeyError: Values.append(0)


            #fig = Fig(figsize = (2, 2),dpi = 100)
            # adding the subplot
            figure, axes = plt.subplots(figsize = (2,2))
            plt.pie(Values,labels = Labels,colors = Colors,startangle = 90,counterclock = False)
            Circ = plt.Circle((0,0), 0.7, color='white')
            plt.title("Savings Pie Chart")
            plt.legend()

 
            axes.set_aspect( 1 )
            axes.add_artist( Circ )
            # creating the Tkinter canvas
            # containing the Matplotlib figure
            canvas = FigureCanvasTkAgg(figure,master = self.F)  
            canvas.draw()
            canvas.get_tk_widget().grid(row = 1,column = 0) # placing the canvas on the Tkinter window
            CBox.grid(row = 0,column = 0)

        '''if Type == "Grouping":

        if Type == "Vacation":

        if Type == "Graphs": '''

class Utilization:
    def __init__(self,master,IncExp,update,predate):
        self.master = master
        self.IncExp = IncExp
        self.F = ttk.Frame(self.master)
        self.Date = tk.StringVar()
        self.DateList = []
        self.Income = 0
        self.Expenses = 0
        self.Savings = 0
        self.Update = update
        self.Predate = predate

        Income = 0
        Expenses = 0
        Savings = 0

        ttk.Label(self.F,text = "Utilization").grid(row = 0, column = 0)
        ttk.Label(self.F,text = "Date").grid(row = 1, column = 1)
        ttk.Label(self.F,text = "Income: ").grid(row = 2, column = 1)
        ttk.Label(self.F,text = "Savings: ").grid(row = 3, column = 1)
        ttk.Label(self.F,text = "Expenses: ").grid(row = 4, column = 1)
        ttk.Label(self.F,text = "Remaining Budget: ").grid(row = 5, column = 1)

        Keys = list(IncExp.keys()) #Get Unique Dates and sort them with most recent first
        for i in Keys:
            for j in IncExp[i].dates:
                if j not in self.DateList:
                    self.DateList.append(j)
        self.DateList.sort(reverse=True)

        if not self.DateList:
            self.Date = tk.StringVar(value = self.Predate)
        if self.DateList and self.Date.get() != str(dt.date.today()):
            self.Date = tk.StringVar(value = self.Predate)
        if self.DateList and self.Date.get() == str(dt.date.today()):
            self.Date = tk.StringVar(value = max(self.DateList))
            
        CBox = ttk.Combobox(self.F,textvariable = self.Date,validatecommand=self.Update,validate='focusout')
        CBox['values'] = self.DateList
        CBox.current()

        
        for i in Keys:
            if IncExp[i].type == "Income":
                try: Income += float(IncExp[i].values[self.Date.get()])
                except KeyError: Income += 0
            if IncExp[i].type == "Expense":
                try: Expenses += float(IncExp[i].values[self.Date.get()])
                except KeyError: Expenses += 0
            if IncExp[i].type == "Savings":
                try: Savings += float(IncExp[i].values[self.Date.get()])
                except KeyError: Savings += 0


        Remaining = Income-Expenses-Savings
        if Income ==0 and Remaining == 0:
            Income = 1
            Remaining = 1
        if Income ==0:
            Income =1
        PercentRemaining = 100*Remaining/Income

        Data = np.array([Expenses,Savings,Remaining])
        Labels = ["Expenses","Savings","Remaining"]
        Colors = ["#F0684A","#59D94C","#132591"]

        if Remaining <0:
            Data = np.array([Expenses,Savings])
            Labels = ["Expenses","Savings"]
            Colors = ["#EC210C","#59D94C"]

        if Savings == 0 and Expenses == 0:
            Data = np.array([Remaining])
            Labels = ["Remaining"]
            Colors = ["#132591"]

        figure, axes = plt.subplots(figsize = (2,2))
        plt.pie(Data,labels = Labels,colors = Colors,startangle = 90,counterclock = False)
        Circ = plt.Circle((0,0), 0.7, color='white')
        plt.text(-.3,-.1,str(PercentRemaining)[:5]+"%")
        plt.title("Budget Utilization")
        axes.set_aspect( 1 )
        axes.add_artist( Circ )
        canvas = FigureCanvasTkAgg(figure,master = self.F)  
        canvas.draw()
        canvas.get_tk_widget().grid(row = 1,column = 0,rowspan = 5) # placing the canvas on the Tkinter windo

        Total = Income - Expenses - Savings
        self.Income = tk.StringVar(value = "$"+str(Income))
        self.Expenses = tk.StringVar(value = "$"+str(Expenses))
        self.Savings = tk.StringVar(value = "$"+str(Savings))
        self.Total = tk.StringVar(value = "$"+str(Total))
        
        CBox.grid(row = 1, column = 2)
        ttk.Entry(self.F,textvariable = self.Income).grid(row = 2, column = 2)
        ttk.Entry(self.F,textvariable = self.Expenses).grid(row = 3, column = 2)
        ttk.Entry(self.F,textvariable = self.Savings).grid(row = 4, column = 2)
        ttk.Entry(self.F,textvariable = self.Total).grid(row = 5, column = 2)

class RecurringTrans:
    def __init__(self,master,Recurring,NewRecur,StartRecur,StopRecur,DelRecur,SaveRecur):
        self.master = master
        self.F = ttk.Frame(self.master)
        self.RecuList = list(Recurring.keys())
        self.Recu = Recurring

        RS = 4

        ttk.Label(self.F, text = "Recurring Transactions").grid(row = 0, column = 0,sticky = 'w')
        ttk.Button(self.F,text = "New Recurring",command = NewRecur).grid(row = 1, column = 0,sticky = 'w')
        ttk.Button(self.F,text = "Disable Recurring",command = StopRecur).grid(row = 2, column = 1,sticky = 'w')
        ttk.Button(self.F,text = "Enable Recurring",command = StartRecur).grid(row = 1, column = 1,sticky = 'w')
        ttk.Button(self.F,text = "Delete Recurring",command = DelRecur).grid(row = 2, column = 0,sticky = 'w')
        ttk.Button(self.F,text = "Save Recurring",command = SaveRecur).grid(row = 3, column = 1,sticky = 'w')
        ttk.Label(self.F, text = "Recurring Incomes").grid(row = RS, column = 0,sticky = 'w')
        ttk.Label(self.F, text = "Recurring Payments").grid(row = RS, column = 2,sticky = 'w')

        self.VarEntrys = [tk.StringVar() for i in range(len(self.RecuList))]
        RI = 0
        RE = 0
        for I in range(len(self.RecuList)):
            if self.Recu[self.RecuList[I]].type == "Income":
                self.VarEntrys[I] = tk.StringVar(value = self.Recu[self.RecuList[I]].value)
                ttk.Label(self.F,text = str(self.RecuList[I])+": ").grid(row = RS+RI,column = 0)
                ttk.Entry(self.F,textvariable=self.VarEntrys[I]).grid(row = RS+RI,column = 1)
                RI += 1
            if self.Recu[self.RecuList[I]].type == "Expense":
                self.VarEntrys[I] = tk.StringVar(value = self.Recu[self.RecuList[I]].value)
                ttk.Label(self.F,text = str(self.RecuList[I])+": ").grid(row = RS+RE,column = 2)
                ttk.Entry(self.F,textvariable=self.VarEntrys[I]).grid(row = RS+RE,column = 3)
                RE += 1

class VacationGrid:
    def __init__(self,master,Vacations,NewCat,DeleteCat,RecordVal,prevdate,newrow):
        self.Cats = list(IncExps.keys())
        self.IEGrid = ttk.Frame(master)
        self.newrow = newrow
        self.Type = Type
        self.IncExps = IncExps
        self.RecIncExp = RecordVal
        
        self.prevdate = prevdate

        tempdates = []
  
        Dates = []
        Cats = []

        
        self.date = tk.StringVar(value = self.prevdate)

        for names in self.Cats:
            if IncExps[names].type == Type:
                Cats.append(names)
                TempDs = list(IncExps[names].values.keys()) #Could append directly to library
                for i in range(len(TempDs)): #And Remove duplicates somehow easily
                    if TempDs[i] not in Dates:
                        Dates.append(TempDs[i]) #List of all catagories and dates

        Dates.sort(reverse=True)
        self.Dates = Dates
        self.Cats = Cats

        ttk.Label(self.IEGrid, text = Type).grid(row = 0, column = 0, sticky = 'w')
        ttk.Button(self.IEGrid, text = "New Entry Date",command = self.New_Date).grid(row = 1, column = 0)
        ttk.Entry(self.IEGrid,textvariable = self.date).grid(row =1 , column = 1)
        ttk.Button(self.IEGrid, text = "Record Values: ",command = self.Record_Incexp).grid(row = 1, column = 4)
        ttk.Button(self.IEGrid, text = "New Catagory",command = lambda: NewCat(self.Type)).grid(row = 1, column = 2)
        ttk.Button(self.IEGrid, text = "Delete Catagory",command = DeleteCat).grid(row = 1, column = 3)

        #Start of main grid of expenses
        h=tk.Scrollbar(self.IEGrid, orient='horizontal')

        RS = 2

        #GridVal[Column][Row]
        self.GridVal = [[tk.StringVar(value = "0") for j in range(len(Dates))] for i in range(len(Cats)+2)]
        Grid = [[ttk.Entry(self.IEGrid,textvariable = self.GridVal[i][j]) for j in range(len(Dates))] for i in range(len(Cats)+2)]

        height = len(Dates)+1
        width = len(Cats)+2


        for j in range(height): #Rows
            Sum = 0
            for i in range(width): #Columns
                if j == 0:
                    if i ==0:
                        ttk.Label(self.IEGrid,text = "Dates").grid(row = RS+j, column = 0)
                    if i == width-1:
                        ttk.Label(self.IEGrid,text = "Total").grid(row = RS+j, column = len(Cats)+1)
                    if len(Cats)!=0 and i != width-1 and i != 0:
                        ttk.Label(self.IEGrid,text = Cats[i-1]).grid(row = RS+j, column = i)
                if j != 0:
                    k = j-1
                    if i == 0:
                        self.GridVal[i][k] = tk.StringVar(value = self.Dates[k])
                        Grid[i][k] = ttk.Entry(self.IEGrid,textvariable =self.GridVal[i][k]).grid(row = RS+j,column = i)
                    if i == width-1:
                        #Sum = sum([float(self.GridVal[i][k].get()) for i in range(1,len(self.GridVal[:][k])-1)])
                        self.GridVal[i][k] = tk.StringVar(value = Sum)
                        Grid[i][k]= ttk.Entry(self.IEGrid,textvariable = self.GridVal[i][k]).grid(row = RS+j,column = i)
                    if len(Cats)!=0 and i != width-1 and i != 0:
                        try: Val = self.IncExps[Cats[i-1]].values[self.Dates[k]]
                        except KeyError: Val = 0
                        self.GridVal[i][k] = tk.StringVar(value = Val)
                        Sum += float(Val)
                        Grid[i][k] = ttk.Entry(self.IEGrid,textvariable = self.GridVal[i][k] ).grid(row = RS+j,column = i)


        #h.config(command=text.xview)     
        #h.pack(side='bottom', fill='x')
    def New_Date(self):
        self.newrow(self.Type,self.date.get())

    def Record_Incexp(self):
        height = len(self.Dates)
        width = len(self.Cats)+1

        date = ''
        cat = ''

        for j in range(height): #Rows
            for i in range(width): #Columns
                if i == 0:
                    date = self.GridVal[i][j].get()
                if len(self.Cats)!=0 and i != 0:
                    cat = self.Cats[i-1]
                    value = self.GridVal[i][j].get()
                    self.RecIncExp(cat,date,value)

        

        


'''
class Groupings:

class Recurring:'''

        