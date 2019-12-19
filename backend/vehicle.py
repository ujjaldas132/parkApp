
#vehiclespace to powerInletCapacity mapping
powerInletCapacityData={1:2,2:4,3:6,4:10}
fullPowerLevelData={1:200,2:400,3:600,4:1000}

class vehicle:
    def __init__(self,space,id,time,powerLevel):
        self.space=space
        self.id=id
        self.timeToBeInParkingLot=time
        self.timeToGetCharged=None
        self.powerLevel=powerLevel
        self.fullPowerLevel=fullPowerLevelData[space]
        self.requiredPower=self.fullPowerLevel-self.powerLevel
        self.powerInletCapacity=powerInletCapacityData[space]
        print("Vehicle class is called ")

