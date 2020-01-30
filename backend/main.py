#import Section
from vehicle import vehicle
from vehicleArrange import arrange
from timeManagement import timeMangement
from firebase import getTheCarAvailableStatus,extractTheDetailOfParkedCar


class controller:
    def __init__(self):
        self.vehicleArrangement=arrange()
        self.timeRecorder=timeMangement(self.vehicleArrangement)
        self.CarAvailableStatus=[]
        self.extractTheCarAvailableStatus()
        self.totalNumberOfParkingspot=0
        self.carParkedToCharge=False


    def extractTheCarAvailableStatus(self):
        data=getTheCarAvailableStatus()
        self.CarAvailableStatus=data
        print(self.CarAvailableStatus)
        self.totalNumberOfParkingspot=len(self.CarAvailableStatus)

        for index in range(self.totalNumberOfParkingspot):
            if not self.CarAvailableStatus[index]:
                self.getTheDetailOfParkedCar(index+1)
                self.carParkedToCharge=True
        if(self.carParkedToCharge):
            self.timeRecorder.CarAvailableStatus=self.CarAvailableStatus
            self.timeRecorder.charging()

    def getTheDetailOfParkedCar(self,parkingSpotId):
        data=extractTheDetailOfParkedCar(parkingSpotId)
        # {'space': '5', 'id': 'AS6984', 'time': '12', 'powerLevel': '50'}
        space=data['space']
        id=data['id']
        time=data['time']
        powerLevel=data['powerLevel']
        self.vehicleArrangement.addVehicle(vehicle(space,id,time,powerLevel,parkingSpotId))

    def newVehicle(self,vehicle):
        '''
        # vehicle: it is a vehicle class object
        '''
        self.vehicleArrangement.addVehicle(vehicle)

    def getChargingVehicle(self):
        return self.vehicleArrangement.getMostPriorVehicle()
    def returnVehicle(self,vehicle):
        """
        # here it is assumed that owner come to get their vehicle back after the given time
        not before the given time by the owner at the time of parking
        """
        requiredVehicle=None
        for chargedVehicle in self.vehicleArrangement.chargedVehicles:
            if chargedVehicle.id==vehicle.id:
                requiredVehicle=chargedVehicle
                self.vehicleArrangement.chargedVehicles.remove(chargedVehicle)
        return requiredVehicle







if __name__ == '__main__':
    obj=controller()
#TODO: availibilty status shouls be in dictionary as some time it can crete prob due to the delay
