import json
import urllib.parse
import urllib.request

index = 0
restaurants = open('../data/csv/restuarants.csv', 'w')
restaurants.write('"id","name","addr"\n')
cuisines = open('../data/csv/cuisines.csv', 'w')
cuisines.write('"id","type"\n')
cui_in_rest = open('../data/csv/cui_in_rest.csv', 'w')
cui_in_rest.write('"cid","rid"\n')
ratings = open('../data/csv/ratings.csv', 'w')
ratings.write('"rid","rating","numVotes"\n')
dishes =open('../data/csv/dishes.csv', 'w')
dishes.write('"id","name","cid"\n')

dishes_in_rest=open('../data/csv/dishes_in_rest.csv', 'w')
dishes_in_rest.write('"did","rid"\n')

cui_count = 0
cui_dict= dict()
for i in range(20):
    with open('../data/page' + str(i) + '.txt') as file:

        for line in file:
            line = line.strip().strip('\n')

        json_object = json.loads(line)

        for b in json_object['businesses']:
            index += 1
            restaurants.write(
                ','.join([str(index) , b['name'] ,"\""+ ', '.join(b['location']["display_address"])+"\"" + '\n']))

            cui = b['categories'][0]['title']

            if cui_dict.get(cui) == None:
                cui_count+=1
                cui_dict[cui] = cui_count

                cuisines.write(','.join([str(cui_count), cui]) + '\n')
            dish = ' '.join([a[0].upper()+a[1:]  for a in b['id'].split('-')[:2]])
            dishes.write(','.join([str(index) ,dish, str(cui_dict[cui])])+ '\n')


            dishes_in_rest.write(str(index) + ',' + str(index)+ '\n')
            cui_in_rest.write(str(cui_dict[cui]) +',' + str(index) + '\n')
            ratings.write(','.join([str(index), str(b['rating']), str(b["review_count"])])+ "\n")







            # print(str(index) + ', ' + b['name'] + ", " + b['categories'][0]['title'] + ", " + str(
            #     b['rating']) + ", " + ', '.join(b['location']["display_address"]))
print(cui_dict)


def get_dish(bid):

    re = urllib.request.Request(url = 'https://api.yelp.com/v3/businesses/' + bid, headers={"Authorization": "Bearer M864mCG359JqoTOj38ejlEA5-HAlsXTOPoSj9kN0XUILwdl_ldd8cBdXpwK2hBi6U6kulwEf3NIPEYQZ3p46r3rdIaSBJfVZFCwTpbdFdGZlzSNR5i8IKe2wiqNnWnYx"})
    response = urllib.request.urlopen(re)
    print(response.read().decode('utf-8', 'ignore'))

if __name__ == '__main__':
    get_dish("heirloom-farmhouse-kitchen-irvine")
