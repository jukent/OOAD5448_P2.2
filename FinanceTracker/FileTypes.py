import datetime as dt


class Profile:
    def __init__(self,name,password,directory):
        self.name = name
        self.password = password
        self.directory = directory
        self.accounts = {}
        self.incexpcat = {}
        self.accdates = []
        self.catdates = []
        self.reccuring = {}
        self.groupings = {}
        self.vacations = {}
        self.vaccats = VacationCats()
        self.records = ''

    def New_Account(self,name,Type,color):
        self.accounts[name] = Account(name,Type,color)

    def Delete_Account(self,name):
        del self.accounts[name]

    def Acc_Date_Add(self,date):
        self.accdates.append(date)

    def New_IncExp_Catagory(self,name,Type,Color):
        self.incexpcat[name] = IncExpCatagory(name,Type,Color)   

    def Del_IncExp_Catagory(self,name):
        del self.incexpcat[name]

    def New_Recurring(self,name,value,type1,start):
        self.recurring[name] = RecurringTransaction(name,value,type1,start)

    def Del_Recurring(self,name):
        del self.recurring[name]

    def New_Group(self,name,list1):
        self.groupings[name] = list1

    def Del_Group(self,name):
        del self.groupings[name]

    def New_Vacation(self,name,date,catlist):
        self.vacations[name] = Vacation(self,name,date,catlist)

    def Del_Vacation(self,name):
        del self.vacations[name]

    def Save_Profile(self,directory):
        directory = directory + "/Profile" + self.name + ".txt"
        f = open(directory,'w')
        f.write(self.name + "\n")
        f.write(self.password+ "\n")

        f.write("ACCOUNTS\n")
        AKeys = list(self.accounts.keys())
        f.write(str(len(AKeys))+ "\n")
        for i in AKeys:
            temp1 = self.accounts[i]
            A = temp1.values
            B = list(A.keys())
            f.write(temp1.name + ',' + temp1.type+','+temp1.color + ','+ str(len(B))+ "\n")
            for j in B:
                f.write(str(j)+','+str(A[j])+ "\n")

        f.write("INCOME/EXPENSES"+ "\n")
        IECKeys = list(self.incexpcat.keys())
        f.write(str(len(IECKeys))+ "\n")
        for i in IECKeys:
            temp1 = self.incexpcat[i]
            A = temp1.values
            B = list(A.keys())
            f.write(temp1.name + ',' + temp1.type+','+temp1.color+ ','+ str(len(B))+ "\n")
            for j in B:
                f.write(str(j)+','+str(A[j])+ "\n")

        f.write("RECURRING"+ "\n")
        RECKeys = list(self.reccuring.keys())
        f.write(str(len(RECKeys))+ "\n")
        for i in RECKeys:
            temp1 = self.reccuring[i]
            f.write(temp1.name + ',' + temp1.value+','+temp1.type +','+temp1.startdate+ "\n")

        f.write("GROUPINGS"+ "\n")
        GKeys = list(self.groupings.keys())
        f.write(str(len(GKeys))+ "\n")
        for i in GKeys:
            temp1 = self.groupings[i]
            f.write(temp1.name + ',' +str(len(temp1.cats))+ "\n")
            for j in temp1.cats:
                f.write(str(j)+ "\n")

        f.write("VACATIONCATS"+ "\n")
        string = ''
        for i in self.vaccats.catnames:
            string += str(i) + ','
        string = string[0:-1]
        f.write(string+ "\n")

        f.write("VACATIONS"+ "\n")
        VacKeys = list(self.vacations.keys())
        f.write(str(len(VacKeys))+ "\n")
        for i in VacKeys:
            temp1 = self.vacations[i]
            f.write(temp1.name + ',' + temp1.date+','+str(len(temp1.catlist))+ "\n")
            f.write(temp1.catlist+ "\n")
            for j in list(temp1.values.keys()):
                f.write(str(j)+','+str(temp1.values[j])+ "\n")

        f.write("RECORDS"+ "\n")
        f.write(self.records+ "\n")
        f.write("ENDOFRECORDS"+ "\n")
        f.write("ENDOFFILE"+ "\n")
        f.close()

    def Load_Profile(self,directory,password):
        f = open(directory,'r')

        tempname = self.ReadNoLine(f)
        temppass = self.ReadNoLine(f)
        if temppass == password:
            self.name = tempname
            self.password = temppass
            m = 0
            count = 0

            while m == 0:
                count += 1
                A = self.ReadNoLine(f)
                if A is not None:
                    if A == "ACCOUNTS":
                        Acnum = int(self.ReadNoLine(f))
                        k = 0
                        while k < Acnum:
                            B = self.ReadNoLine(f).split(',')
                            num = int(B[-1])
                            self.New_Account(B[0],B[1],B[2])
                            l = 0
                            while l < num:
                                C = self.ReadNoLine(f).split(',')
                                self.accounts[B[0]].New_Value(C[0],C[1])
                                l += 1
                            k+=1

                    if A == "INCOME/EXPENSES":
                        IECNum = int(self.ReadNoLine(f))
                        k = 0
                        while k < IECNum:
                            B = self.ReadNoLine(f).split(',')
                            num = int(B[-1])
                            self.New_IncExp_Catagory(B[0],B[1],B[2])
                            l = 0
                            while l < num:
                                C = f.readline().split(',')
                                self.incexpcat[B[0]].New_Value(C[0],C[1])
                                l += 1
                            k+=1

                    if A == "RECURRING":
                        RECnum = int(self.ReadNoLine(f))
                        k = 0
                        while k < RECnum:
                            A = f.readline().split(',')
                            self.New_Recurring(A[0],A[1],A[2],A[3])
                            k+= 1

                    if A == "GROUPINGS":
                        Gnum = int(self.ReadNoLine(f))
                        k = 0
                        while k < Gnum:
                            A = f.readline().split(',')
                            l = 0
                            B = []
                            while l < A[-1]:
                                B.append(f.readline())
                                l+= 1
                            self.New_Group(A[0],B)
                            k+= 1


                    if A == "VACATIONCATS":
                        C = self.ReadNoLine(f)
                        E = C.split(',')
                        B = []
                        for k in E:
                            self.vaccats.catnames.append(k) 


                    if A == "VACATIONS":
                        Vnum = int(self.ReadNoLine(f))
                        k = 0
                        while k < Vnum:
                            B = f.readline().split(',')
                            C = f.readline()[1:-1]
                            E = C.split(',')
                            self.New_Vacation(B[0],B[1],E)
                            l = 0
                            while l < B[-1]:
                                G = f.readline()
                                self.vacations[B[0]].values[G[0]] = G[1]
                                l+=1
                            k+=1



                    if A == "RECORDS":
                        i = 0
                        while i == 0:
                            N = self.ReadNoLine(f)
                            if N == "ENDOFRECORDS":
                                i = 1
                                continue
                            else:
                                self.records += N + "\n"

                    if A == "ENDOFFILE":
                        f.close()
                        m = 2
                if A is None:
                    m = 2
        else:
            print("Incorrect Password")

            
    def ReadNoLine(self,f):
        return f.readline().replace("\n","")

        
class Account:
    def __init__(self,name,Type,Color):
        self.name = name
        self.values = {}
        self.type = Type
        self.color = Color
        self.dates = []

    def New_Value(self,date,value):
        self.values[date] = value
        if date not in self.dates:
            self.dates.append(date)

    def Delete_Value(self,date):
        del self.values[date]
        self.dates.remove(date)
 
class IncExpCatagory:
    def __init__(self,name,Type,color):
        self.name = name
        self.values = {}
        self.color = color
        self.type = Type
        self.dates = []

    def New_Value(self,date,value):
        self.values[date] = value
        if date not in self.dates:
            self.dates.append(date)

    def Delete_Value(self,date):
        del self.values[date]
        self.dates.remove(date)
            
class RecurringTransaction:

    def __init__(self,name,value,Type,date):
        self.name = name
        self.value = value
        self.type = Type
        self.Enabled = True
        self.startdate = date

    def Change_Value(self,value):
        self.value = value

class Grouping:
    def __init__(self,name,list1):
        self.name = name
        self.cats = list1

class VacationCats:
    def __init__(self):
        self.catnames = []

    def AddVacCat(self,name):
        self.catnames.append(name)
    
    def DelVacCat(self,name):
        self.catnames.remove(name)

class Vacation:
    def __init__(self,vacname,vacdate,catlist):
        self.name = vacname
        self.date = vacdate
        self.catlist = catlist
        self.values = {}

        temp = 0    
        for i in catlist:
            self.values[i] = 0
            temp += 0
        self.values["Total"] = sum(temp)


    




        
