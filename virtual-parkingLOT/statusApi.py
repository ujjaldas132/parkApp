from flask import Flask,jsonify
from flask_cors import CORS
import pickle


app=Flask(__name__)
CORS(app)



# posts= {}
# for i  in range(1,9):
# 	posts[str(i)]="n"
# posts["1"]="f"
# posts["3"]="d"
# posts["5"]="f"
# posts["7"]="n"

@app.route("/")
@app.route("/home")
def hello():
	data={}
	totalNumberOfSpots=8
	for i in range(1,totalNumberOfSpots+1):
		with open('parkingSpotStatusPickles/'+str(i)+'.pickle', 'rb') as handle:
			tdata=pickle.load(handle)
			data[str(i)] = tdata[str(i)]
	return jsonify(data)





class api:
	def __init__(self,numberOfSpots,statusPost):

		self.statusPost=statusPost
		self.app = Flask(__name__)
		CORS(self.app)
		print("loop check 1")
		# self.initStatusPost(numberOfSpots)

		@self.app.route("/")
		@self.app.route("/home")
		def returnData():
			print("loop check 2")
			return self.returnStatusJson()

		self.app.run(debug=True)

	# @self.app.route("/")
	# @self.app.route("/home")
	def returnStatusJson(self):
		return jsonify(self.statusPost)
	def initStatusPost(self,numberOfSpots):
		for i in range(numberOfSpots):
			self.statusPost[str(i+1)] = "f"
	def changeStatusPost(self,newStatusPost):
		self.statusPost=newStatusPost




	


if __name__ == '__main__':

	app.run(debug=True)
