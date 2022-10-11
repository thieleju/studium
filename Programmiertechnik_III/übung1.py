from dataclasses import dataclass

# Exercise Algorithmenentwurf - Knapsack


@dataclass
class Product:
    '''Product dataclass'''
    name: str
    weight: float
    value: float
    multiplier: float = 1

    def ratio(self) -> float:
        # round to 2 decimal places
        return round(self.value / self.weight, 2)

    def toString(self, extended=False) -> str:
        output = f"{self.name}({self.multiplier}x, {self.weight} kg, {self.value} €, {self.ratio()} €/kg)"
        if extended == True:
            rel_weight = round(self.weight * self.multiplier, 2)
            rel_value = round(self.value * self.multiplier, 2)
            output += f"\t=> rel_weight: {rel_weight} kg, rel_value: {rel_value} €"
        return output


@dataclass
class Bag:
    '''Bag dataclass'''
    name: str
    capacity: float
    products: list[Product]

    def total_weight(self) -> float:
        return round(sum([(p.weight * p.multiplier) for p in self.products]), 2)

    def total_value(self) -> float:
        return round(sum([(p.value * p.multiplier) for p in self.products]), 2)

    def print(self):
        print(f"Bag: {self.name}")
        print(f"- Total weight: {self.total_weight()} kg / {self.capacity} kg")
        print(f"- Total value: {self.total_value()} €")
        print("- Products:")
        for p in self.products:
            print(f"  - {p.toString(extended=True)}")


# default bag capacity in kg
capacity_default = 50
# generate products
products = [Product("O1", 12.34, 123.99),
            Product("O2", 23.45, 600.54),
            Product("O3", 12.78, 90.67),
            Product("O4", 9.34, 34.32)]


def get_bag_1(products, capacity=capacity_default):
    '''Exercise 1'''
    bag = Bag("Fractal Bag", capacity, [])
    temp_products = products.copy()

    # add products to bag until capacity is reached
    while bag.total_weight() < bag.capacity:
        # exit if no products are left
        if len(temp_products) == 0:
            break
        # get product with highest value/weight ratio
        product = max(temp_products, key=lambda x: x.ratio())
        # check if capacity would be reached
        if bag.total_weight() + product.weight >= bag.capacity:
            # add last product with adjusted multiplier
            product.multiplier = round(
                (bag.capacity - bag.total_weight()) / product.weight, 4)
            bag.products.append(product)
            break
        # add product to bag
        bag.products.append(product)
        # remove product from list
        temp_products.remove(product)

    return bag


def get_bag_2(products, capacity=capacity_default):
    '''Exercise 2'''
    bag = Bag("Discrete Bag", capacity, [])
    temp_products = products.copy()

    # add products to bag until capacity is reached
    while bag.total_weight() < bag.capacity:
        # exit if no products are left
        if len(temp_products) == 0:
            break
        # get product with highest value/weight ratio
        product = max(temp_products, key=lambda x: x.ratio())
        # check if capacity would be reached
        if bag.total_weight() + product.weight >= bag.capacity:
            break
        # add product to bag
        bag.products.append(product)
        # remove product from list
        temp_products.remove(product)

    return bag


if __name__ == "__main__":
    # List all products
    print("List of all products:")
    for p in products:
        print(f"- {p.toString()}")

    # Exercise 1
    get_bag_1(products).print()
    # Exercise 2
    get_bag_2(products).print()
    # Exercise 3
    # TODO Exercise 2 = Exercise 3?
