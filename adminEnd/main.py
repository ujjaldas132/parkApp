#todo: loginto the plateform
#todo: find the empty space to charge
#todo: update the profile if space found
#todo: can see the status of their car


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
        print("Spot id \t\t CarId \t\t ")
        for index in range(self.noOfSpots):
            if(self.vehiclesOnTheParkingLots[index]):
                print(str(index+1),"\t\t\t empty\n")
            else:
                tMap=self.spotSpecificInfo[str(index+1)]
                print(str(index+1),"\t\t",tMap['id'],"\t\t",tMap['powerLevel'],"\n")


if __name__ == '__main__':
    controller=controller()
    controller.visualise()
    while True:
        command=int(input("enter the command to execute"))
        if command==1:
            controller.visualise()










