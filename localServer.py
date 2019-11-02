from flask import Flask,jsonify

app=Flask(__name__)

posts=[{
	"Name": "Ujjal Das",
	"Car" : "Lamborgini",
	"CarNo": "As 8460",
	"Location": "Guwahati"
}]


@app.route("/")
@app.route("/home")
def hello():
	return jsonify(posts)
	


if __name__ == '__main__':
	app.run(debug=True)
