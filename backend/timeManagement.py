from vehicle import vehicle
from vehicleArrange import *
import time


class timeMangement:
    def __init__(self,arrangeObj,vehicle=None,rTime=None):
        self.arrangeObj=arrangeObj
        self.vehicle=None
        self.powerRequirement=None
        self.finishedChargingIndicator=False
    def charging(self):
        self.vehicle=self.arrangeObj.getMostPriorVehicle()
        if self.vehicle !=None:
            self.powerRequirement=self.vehicle.requiredPower

            # after one count of time
            # TODO:think how to implement this
            self.powerRequirement-=self.vehicle.powerInletCapacity
            if 0>=self.powerRequirement:
                self.finishedChargingIndicator=True
        return



    # def finishedCharging(self):
    #     if self.timeToCharge:






