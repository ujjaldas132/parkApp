#import Section
from vehicle import vehicle
from vehicleArrange import arrange
from timeManagement import timeMangement


class controller:
    def __init__(self):
        self.vehicleArrangement=arrange()
        self.timeRecorder=timeMangement(self.vehicleArrangement)
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
