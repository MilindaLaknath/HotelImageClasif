import tensorflow as tf
from keras.models import load_model, Model
from keras import backend as K
from keras.applications import vgg16
from keras.preprocessing.image import load_img
from keras.preprocessing.image import img_to_array
from keras.applications.imagenet_utils import decode_predictions
from keras.layers import Dense, Dropout, Activation, Flatten, Input
from keras.layers import Convolution2D, MaxPooling2D, ZeroPadding2D, GlobalAveragePooling2D, AveragePooling2D
from keras.applications import InceptionV3
from keras.regularizers import l2


import numpy as np
import matplotlib.pyplot as plt

sess = tf.Session()
K.set_session(sess)

model = load_model('model4b.10-0.68.hdf5')

# def create_model(num_classes, dropout, shape):
#     base_model = ResNet50(
#         weights='imagenet',
#         include_top=False,
#         input_tensor=Input(shape=shape))

#     x = base_model.output
#     x = GlobalAveragePooling2D()(x)
#     x = Dropout(dropout)(x)
#     predictions = Dense(num_classes, activation='softmax')(x)

#     model_final = Model(inputs=base_model.input, outputs=predictions)
#     return model_final

# def create_model(num_classes, dropout, shape):
#     base_model = InceptionV3(
#         weights='imagenet', 
#         include_top=False, 
#         input_tensor=Input(shape=(299, 299, 3)))
#     x = base_model.output
#     x = AveragePooling2D(pool_size=(8, 8))(x)
#     x = Dropout(.4)(x)
#     x = Flatten()(x)
#     predictions = Dense(num_classes, init='glorot_uniform', W_regularizer=l2(.0005), activation='softmax')(x)

#     model = Model(input=base_model.input, output=predictions)
#     return model


# shape = (28, 28, 3)
# weights_path = 'model4b.10-0.68.hdf5'
# model = create_model(101, 0, shape)
# model.load_weights(weights_path)

image = "/home/milinda/Documents/RUSL/Research/Projects/ML/HotelModel/Images/Images/Colombo/cinnamon_lakeside/CinnamonLakeside279.jpg"

original = load_img(image, target_size=(224, 224))

numpy_image = img_to_array(original)

image_batch = np.expand_dims(numpy_image, axis=0)

processed_image = vgg16.preprocess_input(image_batch.copy())

predictions = model.predict(processed_image)

label = decode_predictions(predictions)

print(label)

