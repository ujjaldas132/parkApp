'''
author: Ujjal Das
github: ujjaldas132
'''

from flask import Flask,jsonify
from flask_cors import CORS
from pickleReader import reader



app=Flask(__name__)
CORS(app)

reader=reader()

@app.route("/")
@app.route("/home")
def hello():
	data=reader.generator()

	print(data)
	return jsonify(data)



if __name__ == '__main__':
    app.run(debug=True)