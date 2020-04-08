from vehicle import vehicle
from vehicle.vehicleArrange import *
from firebase import updateTheCarAvailableStatus,updateTheDetailOfParkedCar,getSpecificSpotDetails,updateSpecificSpotDetails
import time
'''
there are 8 vehicle in the parking lots which has to charge 
most  preferable always get 60% of the total supply and rest will get the rest
sharing will be done again if required like 
'''

class timeMangement:
    def __init__(self,arrangeObj,vehicle=None,rTime=None,vehicleArrangement=None):
        self.curPowerCapacity=200
        self.arrangeObj=arrangeObj
        self.CarAvailableStatus=None
        self.vehicle=None
        self.powerRequirement=None
        self.finishedChargingIndicator=False
        self.spotSpecificStatus=getSpecificSpotDetails(None)
        self.vehicleArrangement=vehicleArrangement
        self.powerArrangement=[0]*8# 8 as at a time we permit 8 car to charge



    def powerDistribution(self):
        '''
        charging one car is not good so decide to charge a number of car
        and distribute the power among them giving more power to the most preference car

        Returns
        -------
        None.

        '''
        
        if self.vehicleArrangement!=None:
            self.powerArrangement[0]=0.6
            noOfVehicle=self.vehicleArrangement.noOfCars
            share=0.4/noOfVehicle
            for i in range(noOfVehicle):
                self.powerArrangement[i]+=share

        else:
            self.powerArrangement[0]=1


        return




    def charging(self,timeToStay=4):
        self.vehicle=self.arrangeObj.getMostPriorVehicle()
        if self.vehicle !=None:
            self.powerRequirement=self.vehicle.requiredPower
            self.spotSpecificStatus[self.vehicle.parkingSpotId]="c"

            # charge the car here
            # add a delayy time based on the poweravailable and power requirement
            # one car at a time
            print("charge kar raha hu")
            chargingTime=1*(self.vehicle.requiredPower/self.curPowerCapacity)
            timeFactor=1
            if(1>=chargingTime):
                timeFactor=chargingTime
            if(timeFactor>timeToStay):
                timeFactor=timeToStay
            timeToStay-=timeFactor

            print("time required to charge",chargingTime,"next time of charging ",chargingTime-timeFactor)
            time.sleep(timeFactor)

            self.vehicle.powerLevel=self.vehicle.powerLevel+(timeFactor*self.curPowerCapacity)
            self.vehicle.requiredPower-=timeFactor*self.curPowerCapacity

            # update information of the car in the cloud
            informationDataMap = {}
            informationDataMap['space'] = self.vehicle.space
            informationDataMap['id'] = self.vehicle.id
            informationDataMap['time'] = self.vehicle.timeToBeInParkingLot
            informationDataMap['powerLevel'] = self.vehicle.powerLevel
            informationDataMap['parkingSpaceId'] = self.vehicle.parkingSpotId
            updateTheDetailOfParkedCar(informationDataMap['parkingSpaceId'],informationDataMap)

            # after charging complete
            if(chargingTime-timeFactor==0):
                self.spotSpecificStatus[self.vehicle.parkingSpotId] = "f"
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

            updateSpecificSpotDetails(None, self.spotSpecificStatus)
            if(timeToStay>0):
                self.charging(timeToStay)


            # after one count of time
            # TODO:think how to implement this
            # self.powerRequirement-=self.vehicle.powerInletCapacity
            # if 0>=self.powerRequirement:
            #     self.finishedChargingIndicator=True
        return



    # def finishedCharging(self):
    #     if self.timeToCharge:






