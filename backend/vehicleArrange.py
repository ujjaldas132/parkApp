from vehicle import vehicle

"""
always the most priority vehicle will charge
all reference will be same
"""

class arrange:
    def __init__(self):
        self.vehicles=[]
        self.mostPriorVehicle=None
        self.chargedVehicles=[]
        self.noOfCars=0

    def getMostPriorVehicle(self):
        if self.mostPriorVehicle!=None:
            return self.mostPriorVehicle
        else:
            return vehicle(None,None,None)
    def ToChargedVehicles(self,index=0):
        self.noOfCars-=1
        if index==0 and self.noOfCars>0:
            self.vehicles[0]=self.vehicles[self.noOfCars]
            self.vehicles=self.vehicles[:self.noOfCars]
            self.downArrange()


        self.assignMostPriorVehicle()


    def assignMostPriorVehicle(self):
        if self.noOfCars>0:
            self.mostPriorVehicle=self.vehicles[0]
        else:
            self.mostPriorVehicle=None
    def addVehicle(self,vehicle):
        self.noOfCars+=1
        if self.mostPriorVehicle==None:
            self.mostPriorVehicle=vehicle
            self.vehicles.append(self.mostPriorVehicle)
        else:
            if vehicle.timeToBeInParkingLot<self.mostPriorVehicle.timeToBeInParkingLot:
                self.vehicles.append(self.mostPriorVehicle.copy)
                self.mostPriorVehicle = vehicle
                self.vehicles[0] = vehicle
            else:
                self.vehicles.append(vehicle)
            self.upArrange(self.noOfCars-1)
    def upArrange(self,index):
        if(index!=0):
            parentIndex=index//2
            childVehicle=self.vehicles[index]
            parentVehicle=self.vehicles[parentIndex]
            if(childVehicle.timeToBeInParkingLot<parentVehicle.timeToBeInParkingLot):
                self.vehicles[parentIndex]=childVehicle
                self.vehicles[index]=parentVehicle
                self.upArrange(parentIndex)
            return
        return

    def downArrange(self):
        if self.noOfCars<1:
            return
        index=0
        while True:
            rightChildIndex=(2*index)+1
            leftChildIndex=(2*index)+2
            if leftChildIndex>=self.noOfCars:
                return
            changeAble=False
            changedToIndex=leftChildIndex
            if rightChildIndex<self.noOfCars and self.vehicles[rightChildIndex].timeToBeInParkingLot<self.vehicles[leftChildIndex].timeToBeInParkingLot:
                changedToIndex=rightChildIndex
            if self.vehicles[changedToIndex].timeToBeInParkingLot<self.vehicles[index].timeToBeInParkingLot:
                changeAble=True


            if changeAble:
                tempVehicle=self.vehicles[changedToIndex]
                self.vehicles[changedToIndex]=self.vehicles[index]
                self.vehicles[index]=tempVehicle
                index=changedToIndex
            else:
                return


if __name__ == '__main__':
    v1=vehicle(1,1,1,20)
    v2=vehicle(1,1,20,20)
    v3=vehicle(1,1,5,20)
    v4=vehicle(1,1,3,20)
    park=arrange()
    park.addVehicle(v1)
    park.addVehicle(v2)
    park.addVehicle(v3)
    park.addVehicle(v4)








