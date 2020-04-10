#import Section
from vehicle.vehicle import vehicle
from vehicle.vehicleArrange import arrange
from timeManagement import timeMangement
from firebase import updateVehicleData,getTheQueue,extractCarDetail
import schedule,time

# idea: spotstatus maintain a dictionary where you are not storing true and false store carIdentification no
# fixme: but how to manage the collision
# idea: another data structure is maintain first check true value again before update check true
# error: still some collision will be there


#idea: if new car comes first send them to queue then extract here
##todo: if car is not pesent previously add to the queue else ignore--> entry
#fixme: still there is a collision in the cloud when two customer going to update the queue at the same time
#todo: have to maintain a datastructure to search effiently which car is present : Dictionary--> CarID is the key


class controller:
    def __init__(self):
        self.vehicleArrangement=arrange()
        self.timeRecorder=timeMangement(self.vehicleArrangement)
        self.CarAvailableStatus=[]

        self.totalNumberOfParkingspot=0
        self.carParkedToCharge=False
        self.extractTheCarAvailableStatus()


    def extractTheCarAvailableStatus(self):
        if self.CarAvailableStatus==[]:
            for keys in self.timeRecorder.spotSpecificStatus.keys():
                self.timeRecorder.spotSpecificStatus[keys]="u"
        self.vehicleArrangement.clearPreviousData()
        #data=getTheCarAvailableStatus()
        #self.CarAvailableStatus=data
        #todo:get the newly added vehicle and add to the park arrangement
        newCarIds=getTheQueue.getTheNewVehicles(self.vehicleArrangement.entry)
        for id in newCarIds:
            self.getAddvehicle(id)

        print(self.CarAvailableStatus)
        self.totalNumberOfParkingspot=len(self.CarAvailableStatus)

        for index in range(self.totalNumberOfParkingspot):
            if not self.CarAvailableStatus[index]:
                self.timeRecorder.spotSpecificStatus[str(index+1)]="n"
                self.getTheDetailOfParkedCar(index+1)
                self.carParkedToCharge=True
        if(self.carParkedToCharge):
            self.timeRecorder.CarAvailableStatus=self.CarAvailableStatus
            self.timeRecorder.charging()
            #todo: when you comeout from the loop update the cloud
            updateVehicleData.update(self.vehicleArrangement)


    def getAddvehicle(self,id:str,attempt=3):
        data=extractCarDetail.extract(id)
        if data==None:
            if attempt:
                self.getAddvehicle(id,attempt-1)
        else:
            fullPowerLevel=data['fullPowerLevel']
            powerLevel=data['curPowerLevel']
            status=data['status']
            mobNo=data['ownerMob']
            id=data['carIdNumber']
            newVeh=vehicle(space=1,id=id,time=120,powerLevel=powerLevel,fullPowerLevel=fullPowerLevel,parkingSpotId=None)
            self.newVehicle(newVeh)

    '''
    def getTheDetailOfParkedCar(self,parkingSpotId):
        data=extractTheDetailOfParkedCar(parkingSpotId)
        # {'space': '5', 'id': 'AS6984', 'time': '12', 'powerLevel': '50'}
        print('data>>>>>>><<<<<<<<',data)
        space=data['space']
        id=data['id']
        time=data['time']
        powerLevel=data['powerLevel']
        parkingSpotId=data['parkingSpaceId']
        self.vehicleArrangement.addVehicle(vehicle(space,id,time,powerLevel,parkingSpotId))
    '''

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
    schedule.every(0.5).minutes.do(obj.extractTheCarAvailableStatus)
    while True:
        schedule.run_pending()
#TODO: availibilty status shouls be in dictionary as some time it can crete prob due to the delay
