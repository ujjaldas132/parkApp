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



try:
    firebase_admin.get_app()
    logger.info('firebase already intialized.')
except ValueError as e:
    logger.info('firebase not initialized. initialize.')
    cred = credentials.Certificate(credentialsFileLocation)
    firebase_admin.initialize_app(cred)

db = firestore.client()





def prepareCarData(car,status='check'):
    data = {}
    data['fullPowerLevel'] = car['fullPowerLevel']
    data['curPowerLevel'] = car['curPowerLevel']
    data['status'] = status
    data['ownerMob'] = car['ownerMob']
    data['carIdNumber'] = car['carIdNumber']
    data['expectedRecievingDate']='None'
    data['expectedRecievingTime']='None'
    data['lastParkedDate']='None'
    data['lastParkedTime']='None'

    return data


def createCar(userData:dict):
    # print('to creatWWWe the datat')
    data=prepareCarData(userData)
    # print(data)
    doc_ref = db.collection(u'cars').document(data['carIdNumber'])
    print('to create the datat')
    try:
        doc_ref.set(userData)
        print('updated successfully')
        return True
    except:
        print('something gone wrong')
        return False


def create(userData: dict):
    ''':parameter userData all the data of user


    :return boolean True if action is successful else false
    '''
    doc_ref = db.collection(u'users').document(userData['ownerMob'])
    #createCar(userData)
    try:
        doc_ref.set(userData)
        # print('updated successfully')
        return createCar(userData)
    except:
        print('something gone wrong>>>>1')
        return False
    # also you have to create cars

if __name__ == '__main__':
    data={}
    data['fullPowerLevel'] = 5000
    data['curPowerLevel'] = 1000
    data['status'] = 'queue'
    data['ownerMob'] = '8011433884'
    data['carIdNumber'] = 'AS 01 6487'
    create(data)
