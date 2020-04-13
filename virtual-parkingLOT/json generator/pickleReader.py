'''
author: Ujjal Das
github: ujjaldas132
'''

import pickle
import os



class reader:
    def __init__(self):
        self.dir = os.path.dirname(__file__)
        self.pickleFileLocaltion='detailPickle/'
        self.chargingCars={}
        self.queueCars={}
        self.carDetails={}
        print(self.dir)

    def getTheFolderLocation(self):
        t=self.dir.split('/')
        t=t[:len(t)-2]
        dir='/'
        for i in range(0,len(t)):
            dir=os.path.join(dir,t[i])
        print(dir)
        self.dir=dir



    def generator(self):
        data={}
        data['queue']=self.getQueue()
        data['charging']=self.getChargingQueue()
        data['fullyCharged']=self.getFullyChargedQueue()
        return data


    def getQueue(self):
        queue_dict={}
        try:
            src=os.path.join(self.dir,self.pickleFileLocaltion, "queue.pickle")
            pickle_in = open(src, "rb")
            queue_dict = pickle.load(pickle_in)

        except:
            print('something went Wrong')

        return queue_dict

    def getChargingQueue(self):
        charging_dict = {}
        src = os.path.join(self.dir, self.pickleFileLocaltion, "charge.pickle")
        print(src,'src>>>>>>>>>>>>')
        pickle_in = open(src, "rb")
        # pickle_in = open(self.pickleFileLocaltion + "charge.pickle", "rb")
        charging_dict = pickle.load(pickle_in)
        print(charging_dict, 'hhah')
        # try:
        #     pickle_in = open(self.pickleFileLocaltion + "charge.pickle", "rb")
        #     charging_dict = pickle.load(pickle_in)
        #     print(charging_dict,'hhah')
        #
        # except:
        #     print('something went Wrong')

        return charging_dict


    def getFullyChargedQueue(self):
        fullyCharged_dict = {}
        try:
            src = os.path.join(self.dir, self.pickleFileLocaltion, "fullyCharged.pickle")
            pickle_in = open(src, "rb")
            # pickle_in = open(self.pickleFileLocaltion + "fullyCharged.pickle", "rb")
            fullyCharged_dict = pickle.load(pickle_in)

        except:
            print('something went Wrong')

        return fullyCharged_dict





if __name__ == '__main__':
    print('local executer')
    reader=reader()
    reader.getTheFolderLocation()
    print(reader.generator())
