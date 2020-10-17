import urllib.request, csv


print('Beginning file download with urllib2...')

with open('links.csv') as csvfile:
    reader = csv.reader(csvfile)
    for row in reader:
        try:
            url = row[1]
            path = 'E:\\Tests\\ImgsGrp\\NilaveliBeach\\'
            file = path+str(row[0])+'.jpg'
            # print(file, url)
            urllib.request.urlretrieve(url, file)
        except:
            print(row[0])
    print('NilaveliBeach Done !')

