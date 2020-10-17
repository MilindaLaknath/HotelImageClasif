from flask import Flask
from flask import request

import LableClassif.LableClassifier as lc

app = Flask(__name__)


@app.route('/')
def hello():
    return 'Use http://127.0.0.1:8000/lable '


@app.route('/lable')
def getLable():
    image = request.args.get('image')
    print(image)
    lable = lc.predict(image)
    return lable





if __name__ == '__main__':
    port = 8000 #the custom port you want
    app.run(host='127.0.0.1', port=port)