from flask import Flask,jsonify
from flask_cors import CORS
import pickle
from firbase import getSpecificSpotDetails


app=Flask(__name__)
CORS(app)



@app.route("/")
@app.route("/home")
def hello():
	data={}
	totalNumberOfSpots=8
	for spotId in range(1,totalNumberOfSpots+1):
		tdata=getSpecificSpotDetails(spotId)
		data[str(spotId)] = tdata[str(spotId)]
		# with open('parkingSpotStatusPickles/'+str(i)+'.pickle', 'rb') as handle:
		# 	tdata=pickle.load(handle)
		# 	data[str(i)] = tdata[str(i)]
		# 	print(data)
	return jsonify(data)










	


if __name__ == '__main__':

	app.run(debug=True)
