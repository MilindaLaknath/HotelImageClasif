from keras.models import Sequential, load_model
from keras.layers import Conv2D, Input, InputLayer

# without InputLayer
model1 = Sequential()
model1.add(Conv2D(32, (5, 5), input_shape=(64,64,3)))

model1.save('model4b.10-0.68.hdf5')
model_from_file = load_model('model4b.10-0.68.hdf5')
print("Loaded model 1 from file successful")