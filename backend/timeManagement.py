from vehicle.vehicle import vehicle
from vehicle.vehicleArrange import arrange
import time
'''
there are 8 vehicle in the parking lots which has to charge 
most  preferable always get 60% of the total supply and rest will get the rest
sharing will be done again if required like 
'''

class timeMangement:
    def __init__(self,arrangeObj,vehicle=None,rTime=None):
        '''
        

        Parameters
        ----------
        arrangeObj : TYPE List
            DESCRIPTION. list of vehicle objects
        vehicle : TYPE, optional
            DESCRIPTION. The default is None.
        rTime : TYPE, optional
            DESCRIPTION. The default is None.

        Returns
        -------
        None.

        '''
        self.curPowerCapacity=25
        self.arrangeObj=arrangeObj
        self.CarAvailableStatus=None
        self.vehicle=None
        self.powerRequirement=None
        self.finishedChargingIndicator=False
        '''
        self.spotSpecificStatus=getSpecificSpotDetails(None)
        '''
        self.powerArrangement=[0]*8# 8 as at a time we permit 8 car to charge



    def powerDistribution(self):
        '''
        charging one car is not good so decide to charge a number of car
        and distribute the power among them giving more power to the most preference car

        Returns
        -------
        None.

        '''

        self.powerArrangement = [0] * 8
        if self.arrangeObj!=None:
            self.powerArrangement[0]=0.6
            noOfVehicle=self.arrangeObj.noOfCars
            if noOfVehicle:
                share=0.4/noOfVehicle#as the 40% is remaining energy after giving 60% to the most prior vehicle
                for i in range(noOfVehicle):
                    self.powerArrangement[i]+=share

        else:
            self.powerArrangement[0]=1


        return




    def charging(self,timeToStay=4):
        '''
        PARAMETER
        --------------
        it is consider that whenever there is a car to charge we call this function
        :param timeToStay:
        :return:
        '''

        print('mai aya charge karne')
        self.powerDistribution()
        self.vehicle=self.arrangeObj.getMostPriorVehicle()
        if self.vehicle !=None:
            self.powerRequirement=self.vehicle.requiredPower
            #todo: check after everything has done for testing
            #self.spotSpecificStatus[self.vehicle.parkingSpotId]="c"


            #todo: call each vehicle and give the power according to the distribution and update the details

            noOfVehicle = self.arrangeObj.noOfCars
            fullyChargedCarIndex=[]
            for carIndex in range(noOfVehicle):
                car=self.arrangeObj.vehicles[carIndex]
                supplyPower=self.powerArrangement[carIndex]*self.curPowerCapacity

                #car parmeter update
                print(supplyPower, car.requiredPower)
                car.supplyPower(supplyPower)
                print(supplyPower,car.requiredPower)
                # todo: if carrequired power ==0 then remove from the charnging queue add to the done queue
                if car.requiredPower==0:
                    print('ese koi nikalo yaha se charge ho gaya hai')
                    fullyChargedCarIndex.append(carIndex)

            self.arrangeObj.carIsFullyCharged(carIndex=fullyChargedCarIndex)


            # charge the car here
            # add a delayy time based on the poweravailable and power requirement
            # one car at a time


            # self.vehicle.powerLevel=self.vehicle.powerLevel+(timeFactor*self.curPowerCapacity)
            # self.vehicle.requiredPower-=timeFactor*self.curPowerCapacity



            # update information of the car in the cloud
            # I think it is not neccesary for every iteration
            # fixme: we can do it after a fixed time or charging completeion
            #idea: lets create a different data time to store some information to update info at fixed time


            # after charging complete

            #todo: check agin to update the status
            #updateSpecificSpotDetails(None, self.spotSpecificStatus)
            print("charge kar raha hu")
            chargingTime = 1 * (self.vehicle.requiredPower / self.curPowerCapacity)
            timeFactor = 1
            if (1 >= chargingTime):
                timeFactor = chargingTime
            if (timeFactor > timeToStay):
                timeFactor = timeToStay
            # timeToStay -= timeFactor

            print("time required to charge", chargingTime, "next time of charging ", chargingTime - timeFactor)
            time.sleep(timeFactor)
            timeToStay-=1
            print('######################################################################################',timeToStay)
            if(timeToStay==0 or self.arrangeObj.vehicles==[]):
                #todo: update the information in the cloud
                print('print update the data in the cloud\n return back to the main and collect the required data')
            else:
                self.charging(timeToStay)


            # after one count of time
            # TODO:think how to implement this
            # self.powerRequirement-=self.vehicle.powerInletCapacity
            # if 0>=self.powerRequirement:
            #     self.finishedChargingIndicator=True
        return



    # def finishedCharging(self):
    #     if self.timeToCharge:









if __name__ == '__main__':
    v1 = vehicle(1, 1, 100, 2000)
    v2 = vehicle(1, 1, 200, 2000)
    v3 = vehicle(1, 1, 50, 2000)
    v4 = vehicle(1, 1, 30, 2000)
    # v31 = vehicle(1, 1, 0, 20)
    v41 = vehicle(1, 1, 20, 2000)


    park = arrange()
    park.addVehicle(v1)
    park.addVehicle(v2)
    park.addVehicle(v3)
    park.addVehicle(v4)
    # park.addVehicle(v31)
    park.addVehicle(v41)
    park.addVehicle(v41)
    park.addVehicle(v41)
    park.addVehicle(v41)
    park.addVehicle(v41)
    park.addVehicle(v41)

    tm=timeMangement(park)
    tm.powerDistribution()
    print(tm.powerArrangement,sum(tm.powerArrangement))

    t = []
    for x in park.vehicles:
        t.append(x.timeToBeInParkingLot)
    print(t)
    tm.charging()
    # tm.charging()
    # tm.charging()
    # tm.charging()

#todo : change to recursion condition of the charging
#idea: do somthing like constant time so that we can update the cloud at the same time