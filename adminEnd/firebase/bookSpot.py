'''
author: Ujjal Das
github: ujjaldas132
'''
from venv import logger


import firebase_admin
import google
from firebase_admin import credentials
from firebase_admin import firestore
from random import randint
from firebase import credentialsFileLocation
from firebase.getTheQueue.getTheQueue import getQueue


try:
    firebase_admin.get_app()
    logger.info('firebase already intialized.')
except ValueError as e:
    logger.info('firebase not initialized. initialize.')
    cred = credentials.Certificate(credentialsFileLocation)
    firebase_admin.initialize_app(cred)

db = firestore.client()

# def getQueue()

def updateQueue(queue):
    try:
        doc_ref = db.collection(u'carSpotsStatus').document(u'queue')
        doc_ref.set(queue)
        print('your place is booked successfully')
        return True
    except:
        return False




def book(carId:str):
    try:
        queueCloud=getQueue()
        print(queueCloud)
        queueCloud[carId]=True
        # queueCloud.append(carId)

        return updateQueue(queueCloud)
    except:
        print('something went Wrong')
        return False





if __name__ == '__main__':
    print('local executer')
    carId='AS 01 6487'
    book(carId)