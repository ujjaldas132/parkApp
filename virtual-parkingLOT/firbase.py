import firebase_admin
import google
from firebase_admin import credentials
from firebase_admin import firestore

cred = credentials.Certificate("parkapp-2c28c-firebase-adminsdk-8y0q2-3afaa24130.json")
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
        Tlist=[tdict[key]=='True' for key in keyset]
        return Tlist
    except google.cloud.exceptions.NotFound:
        print(u'No such document!')
        return []





def updateSpecificSpotDetails(spotId,detailData):
    doc_ref = db.collection(u'SpecificSpotDetails').document(str(spotId))
    doc_ref.set(detailData)
def getSpecificSpotDetails(spotId):
    doc_ref = db.collection(u'SpecificSpotDetails').document(str(spotId))

    try:
        doc = doc_ref.get()
        print(u'Document data: {}'.format(doc.to_dict()))
        tdict = doc.to_dict()

        return tdict
    except google.cloud.exceptions.NotFound:
        print(u'No such document!')
        return {}





# users_ref = db.collection(u'users')
# docs = users_ref.stream()
#
# for doc in docs:
#     print(u'{} => {}'.format(doc.id, doc.to_dict()))
if __name__ == '__main__':
    x=2
    getTheCarAvailableStatus()
    getSpecificSpotDetails("1")
    # a={'1':'2','2':'3','3':'4'}
    # updateTheCarAvailableStatus(a)