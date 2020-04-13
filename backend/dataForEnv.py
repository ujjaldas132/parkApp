'''
author: Ujjal Das
github: ujjaldas132
'''
from vehicle.vehicle import vehicle
from vehicle.vehicleArrange import arrange
import pickle




# self.id=id
#         self.parkingSpotId=parkingSpotId
#         self.timeToBeInParkingLot=int(time)
#         self.timeToGetCharged=None
#         self.powerLevel=int(powerLevel)
#         self.fullPowerLevel=fullPowerLevel
#         self.requiredPower=self.fullPowerLevel-self.powerLevel
#         self.powerInletCapacity=powerInletCapacityData[self.space]
#         self.mobNo='9583222425'

#fixme: check whether car data is extracted for queue car as well



class generator:
    def __init__(self,arrangeObj):
        self.live=True
        self.arrangeObj=arrangeObj

    def dictGenrFromVeh(self,veh):
        data={}
        data['id']=veh.id
        data['mobNo'] = veh.mobNo
        data['fullPowerLevel']=veh.fullPowerLevel
        data['powerLevel'] = veh.powerLevel
        data['fullPowerLevel'] = veh.fullPowerLevel


        return data

    def pickleCreator(self,fileName,data:dict):
        pickleFileLocaltion='../detailPickle/'

        pickle_out = open(pickleFileLocaltion+fileName, "wb")
        pickle.dump(data, pickle_out)
        pickle_out.close()



    def generate(self):
        print('generating the pickles')
        self.queue()
        self.charging()
        self.fullyCharged()


    def queue(self):
        queueData={}
        queue=self.arrangeObj.queue
        for car in queue:
            tData=self.dictGenrFromVeh(car)
            queueData[tData['id']]=tData
        self.pickleCreator('queue.pickle',queueData)


    def charging(self):
        chargingData = {}
        charge = self.arrangeObj.vehicles
        for car in charge:
            tData = self.dictGenrFromVeh(car)
            chargingData[tData['id']] = tData
        self.pickleCreator('charge.pickle', chargingData)



    def fullyCharged(self):
        fullyChargedData = {}
        charged = self.arrangeObj.chargedVehicles
        for car in charged:
            tData = self.dictGenrFromVeh(car)
            fullyChargedData[tData['id']] = tData
        self.pickleCreator('fullyCharged.pickle', fullyChargedData)