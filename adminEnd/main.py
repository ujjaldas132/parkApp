'''
author: Ujjal Das
github: ujjaldas132
'''
from firebase import createUser
from firebase import bookSpot
from random import randint

class controller:

    def __init__(self,carId,ownerMob):
        self.carId=carId
        self.ownerMob=ownerMob
        self.fullPowerLevel=randint(3000,5000)
        self.curPowerLevel=randint(1000,2000)

    def create(self):
        data = {}
        data['fullPowerLevel'] = self.fullPowerLevel
        data['curPowerLevel'] = self.curPowerLevel
        data['ownerMob'] = self.ownerMob
        data['carIdNumber'] = self.carId
        createUser.create(data)


    def bookSpot(self):
        bookSpot.book(self.carId)







if __name__ == '__main__':
    print('admin End')
    # controller = controller('AS 02 3657','9999999999')
    # controller = controller('AS 01 3657', '9999999999')
    # controller = controller('AS 03 3657', '9999945999')
    # controller = controller('AS 04 3657', '9999239999')
    # controller = controller('AS 05 3657', '9999923999')
    # controller = controller('AS 06 3657', '9996799999')
    # controller = controller('AS 07 3657', '9912999999')
    # controller = controller('AS 08 3657', '9129999999')
    # controller = controller('AS 09 3657', '1299999999')
    # controller = controller('AS 02 3957', '3499999999')
    # controller = controller('AS 02 3677', '5699999999')
    controller = controller('AS 02 3867', '6799999999')
    controller.create()
    controller.bookSpot()