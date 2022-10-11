from dataclasses import dataclass

@dataclass
class Product:
    o: int
    weight: float
    value: float

# generate products
products = [Product(1, 12.34, 123.99),
            Product(2, 23.45, 600.54),
            Product(3, 12.78, 90.67),
            Product(4, 9.34, 34.32)]

capacity = 41

def packBag1(capacity):
    global products
    bag = []
    while capacity > 0:
        # find product with highest value/weight ratio
        bestProduct = None
        bestRatio = 0
        for product in products:
            ratio = product.value / product.weight
            if ratio > bestRatio:
                bestProduct = product
                bestRatio = ratio

        if(bestProduct == None):
            break

        # add product to bag if it fits
        if bestProduct.weight <= capacity:
            bag.append(bestProduct)
            capacity -= bestProduct.weight

        # remove product from list
        products.remove(bestProduct)
    return bag
    
if __name__ == "__main__":
    bag1 = packBag1(capacity)
    print("Bag contains:")
    for product in bag1:
        print(product)
    print("Value: "+str(sum([product.value for product in bag1])))
    print("Weight: "+str(sum([product.weight for product in bag1])))