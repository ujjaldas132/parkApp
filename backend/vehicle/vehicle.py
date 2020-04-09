
#vehiclespace to powerInletCapacity mapping
powerInletCapacityData={1:2,2:4,3:6,4:10,5:15}
fullPowerLevelData={1:200,2:400,3:600,4:1000,5:1300}

class vehicle:
    def __init__(self,space,id,time,powerLevel,parkingSpotId=None):
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
        self.fullPowerLevel=fullPowerLevelData[self.space]
        self.requiredPower=self.fullPowerLevel-self.powerLevel
        self.powerInletCapacity=powerInletCapacityData[self.space]
        print("Vehicle class is called ")


    def supplyPower(self,supplyPower):
        self.powerLevel+=supplyPower
        if self.powerLevel>self.fullPowerLevel:
            self.powerLevel=self.fullPowerLevel
        self.requiredPower=self.fullPowerLevel-self.powerLevel

