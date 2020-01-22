from flask import Flask,jsonify
from flask_cors import CORS

app=Flask(__name__)
CORS(app)

posts={}
for i  in range(1,9):
	posts[str(i)]="n"
posts["1"]="n"

@app.route("/")
@app.route("/home")
def hello():
	return jsonify(posts)
	


if __name__ == '__main__':
	app.run(debug=True)
