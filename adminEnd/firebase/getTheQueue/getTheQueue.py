'''
author: Ujjal Das
github: ujjaldas132
'''
import sys, os
# sys.path.append(os.path.abspath(os.path.join('..')))
from venv import logger


import firebase_admin
import google
from firebase_admin import credentials
from firebase_admin import firestore
from random import randint
from firebase import credentialsFileLocation



try:
    firebase_admin.get_app()
    logger.info('firebase already intialized.')
except ValueError as e:
    logger.info('firebase not initialized. initialize.')
    cred = credentials.Certificate(credentialsFileLocation)
    firebase_admin.initialize_app(cred)

db = firestore.client()

'''
all the cars in the parking lot will be 
'''

def getTheNewVehicles(availableVehicle:dict):
    '''

    :parameter availableVehicle: type--> dict keys are the ID of the
    :return: type-> list ... id of all the cars
    '''
    data=getQueue()#expecting a list
    newVehicleIds=[]
    for carId in data:
        if carId not in availableVehicle:
            newVehicleIds.append(carId)
    return newVehicleIds


def getQueue():
    '''
    :param nothing;

    :return list of all the vehicle ids in the queue in the cloud
    '''
    doc_ref = db.collection(u'carSpotsStatus').document(u'queue')

    try:
        doc = doc_ref.get()
        # print('doc',doc.to_dict())
        return doc.to_dict()
    except google.cloud.exceptions.NotFound:
        print(u'No such document!')
        return []







if __name__ == '__main__':
    print('local executer')