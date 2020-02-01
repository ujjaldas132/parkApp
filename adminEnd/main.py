
#todo: can see the owner detail of the parked car
#todo: can remove the car from the park


import schedule
from firebase import extractTheDetailOfParkedCar,getTheCarAvailableStatus



class controller:
    def __init__(self):
        self.carStatus=[]
        self.vehiclesOnTheParkingLots=[]
        self.noOfSpots=0
        self.spotSpecificInfo={}


        self.extractChargingStatus()


    def extractChargingStatus(self):
        self.spotSpecificInfo = {}
        #firebase extraction function
        self.vehiclesOnTheParkingLots=getTheCarAvailableStatus()
        self.noOfSpots=len(self.vehiclesOnTheParkingLots)
        for parkingSpotId in range(self.noOfSpots):
            if not self.vehiclesOnTheParkingLots[parkingSpotId]:
                tData=self.extractTheSpotSpecificCarDetail(str(parkingSpotId + 1))
                self.spotSpecificInfo[str(parkingSpotId+1)]=tData


    def extractTheSpotSpecificCarDetail(self,SpotId):
        """

        :param SpotId: String > spotId  wheere the car is parked
        :return:
        """
        return extractTheDetailOfParkedCar(SpotId)

    def visualise(self):
        print("SpotId","\t\t",'CarNo',"\t\t",'powerLevel','\t\t','time','\t\t','space',"\n")
        for index in range(self.noOfSpots):
            if(self.vehiclesOnTheParkingLots[index]):
                print(str(index+1),"\n")
            else:
                tMap=self.spotSpecificInfo[str(index+1)]
                print(str(index+1),"\t\t",tMap['id'],"\t\t",tMap['powerLevel'],'\t\t',tMap['time'],'\t\t',tMap['space'],"\n")

    def getOwnerInfo(self,spotid):
        print("not complete")


if __name__ == '__main__':
    controller=controller()
    controller.visualise()
    while True:
        command=int(input("enter the command to execute"))
        if command==1:
            controller.visualise()
        if command==2:
            parkingSpotId=int(input("enter the SpotId"))
            if(parkingSpotId>0 and parkingSpotId+1<controller.noOfSpots):
                controller.getOwnerInfo(parkingSpotId)
            else:
                print("enter an valid parking spot number")










