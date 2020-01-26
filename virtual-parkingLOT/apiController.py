
import pickle

class vehicleInfo:
    def __init__(self,id,foundStatus=False):
        self.chargedLevel=0
        self.charging=False
        self.spotId=id
        self.spotFound=foundStatus
    def changeChargeLevel(self,newLevel):
        self.chargedLevel=newLevel
    def changeChargingStatus(self):
        self.charging= not self.charging



class controller:
    def __init__(self):

        self.totalNumberParkingSpot=8
        self.carsAvailbility=[True for i in range(self.totalNumberParkingSpot)]
        self.carsInfo=[vehicleInfo(i+1,True) for i in range(self.totalNumberParkingSpot)]

        for i in range(self.totalNumberParkingSpot):
            print("stage one")
            tstatusMap = {}
            tstatusMap[str(i+1)]="u"
            self.writeThePickleFile(i+1,tstatusMap)

    def writeThePickleFile(self,id,newData):
        with open('parkingSpotStatusPickles/'+str(id)+'.pickle', 'wb') as handle:
            pickle.dump(newData, handle, protocol=pickle.HIGHEST_PROTOCOL)

    def changeTheStatusOfTheSpot(self,id,status):
        data={}
        with open('parkingSpotStatusPickles/'+str(id)+'.pickle', 'rb') as handle:
            data = pickle.load(handle)
        print(data)
        data[str(id)]=status
        print(data)
        self.writeThePickleFile(id,data)

    def checkSpotIsAvailable(self,id):
        vehiclesSpotCount=self.totalNumberParkingSpot
        if(id < int(vehiclesSpotCount)):
            return self.carsAvailbility[id]

        return False
    def reservedTheSpotForCar(self,id):
        self.carsAvailbility[id] = False
    def returnTheBookedSpotObject(self,id):
        requiredObject=self.carsInfo[id]
        requiredObject.spotFound=True
        return requiredObject

    def findASpotForNewCar(self):
        for id in range(self.totalNumberParkingSpot):
            if(self.checkSpotIsAvailable(int(id))):
                self.reservedTheSpotForCar(int(id))
                return self.returnTheBookedSpotObject(int(id))
        return vehicleInfo(-1)


    def newCarArrived(self):
        newVehicleInfoObject=self.findASpotForNewCar()
        if not newVehicleInfoObject.spotFound:
            print("all spots are booked please be in queue we'll update you soon!")
        else:
            print("you got a spot in the spot no>>  "+str(newVehicleInfoObject.spotId))
        return newVehicleInfoObject

    def releaseTheSpot(self,vehicleInfoObject):
        id=int(vehicleInfoObject.spotId)
        self.carsInfo[id-1]=vehicleInfo(id,True)
        self.carsAvailbility[id-1]=True
        vehicleInfoObject.spotFound=False
        print("the spot is release having the spot id >> "+str(id))






if __name__ == '__main__':

    controller=controller()
    print("stage two")
    #controller.changeTheStatusOfTheSpot(2,"f")
    a=controller.newCarArrived()
    b=controller.newCarArrived()
    c=controller.newCarArrived()
    d=controller.newCarArrived()
    e=controller.newCarArrived()
    controller.releaseTheSpot(a)
    f=controller.newCarArrived()
    g=controller.newCarArrived()
    h=controller.newCarArrived()
    i=controller.newCarArrived()
    j=controller.newCarArrived()
    k=controller.newCarArrived()

    # @controller.api.app.route("/")
    # @controller.api.app.route("/home")
    # def returnData():
    #     print("loop check 2")
    #     return controller.api.returnStatusJson()


    # controller.api.app.run(debug=True)
    # controller.api.statusPost["1"]="f"
    print("stage one two three")
    print("hogaya")


