'''
author: Ujjal Das
github: ujjaldas132
'''

import pickle

pickleFileLocaltion='../detailPickle/'

class reader:
    def __init__(self):
        self.chargingCars={}
        self.queueCars={}
        self.carDetails={}


    def getQueue(self):
        queue_dict={}
        try:
            pickle_in = open(pickleFileLocaltion+"queue.pickle", "rb")
            queue_dict = pickle.load(pickle_in)

        except:
            print('something went Wrong')

        return queue_dict

    def getChargingQueue(self):
        charging_dict = {}
        try:
            pickle_in = open(pickleFileLocaltion + "charging.pickle", "rb")
            charging_dict = pickle.load(pickle_in)

        except:
            print('something went Wrong')

        return charging_dict

