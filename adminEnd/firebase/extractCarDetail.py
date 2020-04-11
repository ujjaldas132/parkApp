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




def extract(carID):
    doc_ref = db.collection(u'cars').document(carID)

    try:
        doc = doc_ref.get()
        print(u'Document data: {}'.format(doc.to_dict()))
        tdict = doc.to_dict()

        return tdict
    except google.cloud.exceptions.NotFound:
        print(u'No such document!')
        return None





if __name__ == '__main__':
    print('it is the local executer')