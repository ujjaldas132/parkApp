from vehicle import vehicle
from vehicleArrange import *
import time


class timeMangement:
    def __init__(self,vehicle=None,rTime=None):
        self.vehicle=vehicle
        self.timeToCharge=rTime
        self.finishedChargingIndicator=False
    def charging(self):
        # after one count of time
        # TODO:think how to implement this
        self.timeToCharge-1
        if self.timeToCharge==0:
            self.finishedChargingIndicator=True


    # def finishedCharging(self):
    #     if self.timeToCharge:






