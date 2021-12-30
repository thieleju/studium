import math as m


def eratosthenesSieb(n):
    primzahlen = []
    zahlen = list(range(2, n))
    aktuell = 2

    while aktuell < m.sqrt(n):
        for i in range(aktuell, n, aktuell):
            if i in zahlen:
                zahlen.remove(i)

        primzahlen.append(aktuell)
        aktuell = zahlen[0]

    return primzahlen + zahlen


if __name__ == "__main__":
    l = eratosthenesSieb(10000)
    print(len(l), l)
