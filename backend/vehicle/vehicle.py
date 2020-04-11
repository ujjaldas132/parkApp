
#vehiclespace to powerInletCapacity mapping


powerInletCapacityData={1:2,2:4,3:6,4:10,5:15}
#todo: remove this and send the data from the cloud itself

class vehicle:
    def __init__(self,space,id,time,powerLevel,fullPowerLevel=3000,parkingSpotId=None):
        """

        :param space: Space Required to parked
        :param id: carNumber
        :param time: timeToBeInParkingLot
        :param powerLevel: currentPowerLevel
        :param parkingSpotId: parkingSpotId none if not allocate then update when it is updated
        """
        self.space=int(space)
        self.id=id
        self.parkingSpotId=parkingSpotId
        self.timeToBeInParkingLot=int(time)
        self.timeToGetCharged=None
        self.powerLevel=int(powerLevel)
        self.fullPowerLevel=fullPowerLevel
        self.requiredPower=self.fullPowerLevel-self.powerLevel
        self.powerInletCapacity=powerInletCapacityData[self.space]
        self.mobNo='9583222425'
        print("Vehicle class is called ")


    def supplyPower(self,supplyPower):
        self.powerLevel+=supplyPower
        if self.powerLevel>self.fullPowerLevel:
            self.powerLevel=self.fullPowerLevel
        self.requiredPower=self.fullPowerLevel-self.powerLevel



if __name__ == '__main__':
    print(checkMod.check)

