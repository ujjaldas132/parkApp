from vehicle import vehicle
from vehicleArrange import *
from firebase import updateTheCarAvailableStatus
import time


class timeMangement:
    def __init__(self,arrangeObj,vehicle=None,rTime=None):
        self.curPowerCapacity=200
        self.arrangeObj=arrangeObj
        self.CarAvailableStatus=None
        self.vehicle=None
        self.powerRequirement=None
        self.finishedChargingIndicator=False
    def charging(self):
        self.vehicle=self.arrangeObj.getMostPriorVehicle()
        if self.vehicle !=None:
            self.powerRequirement=self.vehicle.requiredPower

            # charge the car here
            # add a delayy time based on the poweravailable and power requirement
            # one car at a time
            print("charge kar raha hu")
            chargingTime=1*(self.vehicle.requiredPower/self.curPowerCapacity)
            timeFactor=1
            if(1>=chargingTime):
                timeFactor=chargingTime

            print("time required to charge",chargingTime,"next time of charging ",chargingTime-timeFactor)
            time.sleep(timeFactor)

            self.vehicle.powerLevel=self.vehicle.powerLevel+(timeFactor*self.curPowerCapacity)
            self.vehicle.requiredPower-=timeFactor*self.curPowerCapacity
            # after charging complete
            if(chargingTime-timeFactor==0):
                self.arrangeObj.removeTheMostPriorVehicle()

                print("charge ho gaya bro")
                #allocate space space False to true
                self.CarAvailableStatus[int(self.vehicle.parkingSpotId)-1]=True
                #TODO:
                # update the status in the Cloud
                newCarStatusData={}
                for i in range(len(self.CarAvailableStatus)):
                    newCarStatusData[str(i+1)]=str(self.CarAvailableStatus[i])
                updateTheCarAvailableStatus(newCarStatusData)
            self.charging()


            # after one count of time
            # TODO:think how to implement this
            # self.powerRequirement-=self.vehicle.powerInletCapacity
            # if 0>=self.powerRequirement:
            #     self.finishedChargingIndicator=True
        return



    # def finishedCharging(self):
    #     if self.timeToCharge:






