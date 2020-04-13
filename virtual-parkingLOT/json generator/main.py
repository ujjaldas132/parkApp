'''
author: Ujjal Das
github: ujjaldas132
'''

from flask import Flask,jsonify
from flask_cors import CORS



app=Flask(__name__)
CORS(app)

@app.route("/")
@app.route("/home")
def hello():
	data={}

	print(data)
	return jsonify(data)



if __name__ == '__main__':
    app.run(debug=True)