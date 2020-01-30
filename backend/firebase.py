import firebase_admin
import google
from firebase_admin import credentials
from firebase_admin import firestore
from random import randint

cred = credentials.Certificate("/home/ujjal/myfiles/B.Tech project/androidApp/firbase Auth/parkapp-2c28c-firebase-adminsdk-8y0q2-3afaa24130.json")
firebase_admin.initialize_app(cred)

db = firestore.client()




def updateTheCarAvailableStatus(carStatusData):
    doc_ref = db.collection(u'carSpotsStatus').document(u'currentStatus')
    doc_ref.set(carStatusData)



def getTheCarAvailableStatus():
    doc_ref = db.collection(u'carSpotsStatus').document(u'currentStatus')

    try:
        doc = doc_ref.get()
        print(u'Document data: {}'.format(doc.to_dict()))
        tdict=doc.to_dict()
        keyset=list(tdict.keys())
        keyset.sort()
        Tlist=[tdict[key]=='True' for key in keyset]
        return Tlist
    except google.cloud.exceptions.NotFound:
        print(u'No such document!')
        return []



def extractTheDetailOfParkedCar(parkingSpotId):
    doc_ref = db.collection(u'detailOfParkedCar').document(str(parkingSpotId))

    try:
        doc = doc_ref.get()
        print(u'Document data: {}'.format(doc.to_dict()))
        tdict = doc.to_dict()

        return tdict
    except google.cloud.exceptions.NotFound:
        print(u'No such document!')
        return {}


def updateTheDetailOfParkedCar(parkingSpotId,data):
    """

    :param parkingSpotId: SpotId of the parking spot
    :param data: {space: Space Required to parked,id: carNumber,time: timeToBeInParkingLot,powerLevel: currentPowerLevel}
    :return: None
    """
    doc_ref = db.collection(u'detailOfParkedCar').document(str(parkingSpotId))
    doc_ref.set(data)




if __name__ == '__main__':
    data={'space': '5','id': 'AS6984','time': '12','powerLevel': '50'}
    for i in range(1,9):
        data['time']=randint(10,30)
        data['powerLevel'] = randint(50, 100)
        updateTheDetailOfParkedCar(i,data)