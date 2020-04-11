'''
author: Ujjal Das
github: ujjaldas132
'''

from vehicle.vehicle import vehicle
from vehicle.vehicleArrange import arrange

import firebase_admin
import google
from firebase_admin import credentials
from firebase_admin import firestore
from random import randint
from firebase import credentialsFileLocation

cred = credentials.Certificate(credentialsFileLocation)
firebase_admin.initialize_app(cred)

db = firestore.client()


#cars> identification will be the carIDnumber
'''
fullPowerLevel
curPowerLevel
status
ownerMob
carIdNumber
'''

def prepareData(car,status):
    data = {}
    data['fullPowerLevel'] = car.fullPowerLevel
    data['curPowerLevel'] = car.powerLevel
    data['status'] = status
    data['ownerMob'] = car.mobNo
    data['carIdNumber'] = car.id
    return data





def update(vehicleArrangementData:arrange):
    if vehicleArrangementData.vehicles==[]:
        return
    else:
        for car in vehicleArrangementData.vehicles:
            data=prepareData(car,'charging')
            updateInTheCloud(car.id,data)

        for car in vehicleArrangementData.queue:
            data=prepareData(car,'queue')
            updateInTheCloud(car.id, data)






def updateInTheCloud(refNo,data):
    doc_ref = db.collection(u'cars').document(refNo)
    doc_ref.set(data)





if __name__ == '__main__':
    print('checking import')
    v41 = vehicle(1, 1, 20, 2000)
    park=arrange()
    park.addVehicle(v41)
    print(credentialsFileLocation)