from OOP_2021.ex1.src.Building import Building

from OOP_2021.ex1.src.ListOfCallForElevator import ListOfCallForElevator


class SmartElevatorAlgo:

    def __init__(self, building, calls, output):
        self._building = Building(building)
        self._list_of_call = ListOfCallForElevator(calls)
        self._output = output


   # def elevator_assignment(self):
        #for k in self._list_of_call:












