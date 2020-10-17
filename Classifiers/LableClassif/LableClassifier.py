import numpy as np
from keras.preprocessing.image import ImageDataGenerator, img_to_array, load_img
from keras.models import Sequential
from keras.layers import Dropout, Flatten, Dense
from keras import applications
from keras.utils.np_utils import to_categorical
from keras import backend as K
import matplotlib.pyplot as plt
import math
import cv2

from flask import Flask
from flask import request

top_model_weights_path = 'bottleneck_fc_model.h5'


app = Flask(__name__)

@app.route('/lable')
def predict():
    image_path = request.args.get('image')

    # load the class_indices saved in the earlier step
    class_dictionary = np.load('class_indices.npy').item()

    num_classes = len(class_dictionary)

    # add the path to your test image below
    # image_path = '/home/milinda/Documents/RUSL/Research/Projects/ML/bottleneck/LatestImgs/NilaveliBeach/NilaveliBeachHotel13.jpg'

    orig = cv2.imread(image_path)

    # print("[INFO] loading and preprocessing image...")
    image = load_img(image_path, target_size=(224, 224))
    image = img_to_array(image)

    # important! otherwise the predictions will be '0'
    image = image / 255

    image = np.expand_dims(image, axis=0)

    # build the VGG16 network
    model = applications.VGG16(include_top=False, weights='imagenet')

    # get the bottleneck prediction from the pre-trained VGG16 model
    bottleneck_prediction = model.predict(image)

    # build top model
    model = Sequential()
    model.add(Flatten(input_shape=bottleneck_prediction.shape[1:]))
    model.add(Dense(256, activation='relu'))
    model.add(Dropout(0.7))
    model.add(Dense(num_classes, activation='sigmoid'))

    model.load_weights(top_model_weights_path)

    # use the bottleneck prediction on the top model to get the final
    # classification
    class_predicted = model.predict_classes(bottleneck_prediction)

    probabilities = model.predict_proba(bottleneck_prediction)

    inID = class_predicted[0]

    inv_map = {v: k for k, v in class_dictionary.items()}

    label = inv_map[inID]

    # print(class_predicted)
    # print(probabilities)
    # print(inv_map)

    # get the prediction label
    # print("Image ID: {}, Label: {}".format(inID, label))
    K.clear_session()
    return label

    # display the predictions with the image
    # cv2.putText(orig, "Predicted: {}".format(label), (10, 30),
    #             cv2.FONT_HERSHEY_PLAIN, 1.5, (43, 99, 255), 2)

    # cv2.imshow("Classification", orig)
    # cv2.waitKey(0)
    # cv2.destroyAllWindows()


# predict()


if __name__ == '__main__':
    port = 8000 #the custom port you want
    app.run(host='127.0.0.1', port=port)

