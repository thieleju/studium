class Communication_Channel:
    def __init__(self, type, identifier):
        self.type = type
        self.identifier = identifier
    def __str__(self):
        return f"{self.type}: {self.identifier}"

class Person:
    def __init__(self, id=0, first_name="", last_name="", list_of_communication_channels=[]):
        self.id = id
        self.update_name(first_name, last_name)
        self.update_list_of_communication_channels(list_of_communication_channels)
    def __str__(self):
        id = f"id: {self.id}"
        name = f"name: {self.last_name}, {self.first_name}"
        channels = '\n'.join(map(str, self.list_of_communication_channels))
        return id + '\n' + name + '\n' + channels
    def update_name(self, first_name, last_name):
        self.first_name = first_name
        self.last_name = last_name
    def update_list_of_communication_channels(self, list_of_communication_channels):
        self.list_of_communication_channels = list_of_communication_channels

class Person_Not_Found(Exception):
    pass

from abc import ABC, abstractmethod

class Person_Finder(ABC):
    @abstractmethod
    def find_Person(self, id):
        pass

class Person_Finder_Stub(Person_Finder):
    def find_Person(self, id):
        if (id == 1):
            c1 = Communication_Channel("mobile phone", "0160/1111111")
            c2 = Communication_Channel("e-mail","ute.mustermann@web.de")
            p1 = Person(id, "Ute", "Mustermann", [c1, c2])
        elif (id == 2):
            c1 = Communication_Channel("mobile phone", "0160/2222222")
            p1 = Person(id, "Max", "Beispiel", [c1])
        else:
            raise Person_Not_Found(f"Person with id {id} was not found.")
        return p1

def main():
    import sys
    if len(sys.argv) == 1: #script name is always given as command line parameter
        id = 1
    else:
        id = int(sys.argv[1]) # take first user given command line parameter
    pf = Person_Finder_Stub()
    try:
        found_person = pf.find_Person(id)
    except Person_Not_Found as not_found_message:
        print(not_found_message)
        sys.exit(1)
    print("PERSON DATA:")
    print(found_person)

if __name__ == "__main__":
    main()
