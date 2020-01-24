
import pickle

class vehicleInfo:
    def __init__(self):
        self.chargedLevel=0
        self.charging=False
    def changeChargeLevel(self,newLevel):
        self.chargedLevel=newLevel
    def changeChargingStatus(self):
        self.charging= not self.charging



class controller:
    def __init__(self):

        self.totalNumberParkingSpot=8

        for i in range(self.totalNumberParkingSpot):
            print("stage one")
            tstatusMap = {}
            tstatusMap[str(i+1)]="n"
            self.writeThePickleFile(i+1,tstatusMap)

        # self.api = api(self.totalNumberParkingSpot,tstatusMap)
        # self.checkavailibiltyArray=[False for i in range(self.totalNumberParkingSpot)]
        # self.cars=[vehicleInfo() for i in range(self.totalNumberParkingSpot)]

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


if __name__ == '__main__':

    controller=controller()
    print("stage two")
    controller.changeTheStatusOfTheSpot(2,"f")

    # @controller.api.app.route("/")
    # @controller.api.app.route("/home")
    # def returnData():
    #     print("loop check 2")
    #     return controller.api.returnStatusJson()


    # controller.api.app.run(debug=True)
    # controller.api.statusPost["1"]="f"
    print("stage one two three")
    print("hogaya")


